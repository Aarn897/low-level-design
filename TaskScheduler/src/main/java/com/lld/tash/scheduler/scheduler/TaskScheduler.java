package com.lld.tash.scheduler.scheduler;

import com.lld.tash.scheduler.task.PriorityTask;
import com.lld.tash.scheduler.task.Task;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    private final PriorityBlockingQueue<PriorityTask> priorityTaskQueue;
    private final BlockingQueue<Task> simpleTaskQueue;
    private final ScheduledExecutorService executor;
    private final int maxRetries;

    public TaskScheduler(int initialThreads, int maxRetries) {
        this.priorityTaskQueue = new PriorityBlockingQueue<>();
        this.simpleTaskQueue = new LinkedBlockingQueue<>();
        this.executor = Executors.newScheduledThreadPool(initialThreads);
        this.maxRetries = maxRetries;
        startWorkerThreads();
    }

    // Dynamically start worker threads
    private void startWorkerThreads() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            executor.submit(this::processTasks);
        }
    }

    // Process tasks from both queues
    private void processTasks() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Polling from the priority queue or the regular task queue
                Task task = !priorityTaskQueue.isEmpty() ? priorityTaskQueue.take() : simpleTaskQueue.take();
                retryTask(task, 0); // Retry from 0 on first attempt
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                break;
            }
        }
    }

    // Retry mechanism
    private void retryTask(Task task, int retryCount) {
        try {
            task.execute();
        } catch (Exception e) {
            if (retryCount < maxRetries) {
                System.out.println("Retrying task, attempt: " + (retryCount + 1));
                retryTask(task, retryCount + 1);
            } else {
                System.out.println("Task failed after max retries.");
            }
        }
    }

    // Adding tasks
    public void addTask(Task task) {
        if (task instanceof PriorityTask) {
            priorityTaskQueue.offer((PriorityTask) task);
        } else {
            simpleTaskQueue.offer(task);
        }
    }

    // Schedule recurring tasks
    public void scheduleRecurringTask(Task task, long delay, TimeUnit timeUnit) {
        executor.schedule(() -> addTask(task), delay, timeUnit);
    }

    // Shutdown the scheduler
    public void shutdown() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
