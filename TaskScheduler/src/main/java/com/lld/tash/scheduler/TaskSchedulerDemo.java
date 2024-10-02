package com.lld.tash.scheduler;

import com.lld.tash.scheduler.task.RecurringTask;
import com.lld.tash.scheduler.task.SimplePriorityTask;
import com.lld.tash.scheduler.task.SimpleTask;
import com.lld.tash.scheduler.scheduler.TaskScheduler;
import java.util.concurrent.TimeUnit;

public class TaskSchedulerDemo {
    public static void main(String[] args) throws InterruptedException {
        TaskScheduler scheduler = new TaskScheduler(5, 3); // 5 worker threads, max 3 retries for failed tasks

        scheduler.addTask(new SimplePriorityTask("Priority Task 1", 2));
        scheduler.addTask(new SimplePriorityTask("Priority Task 2", 1));
        scheduler.addTask(new SimpleTask("Simple Task 1"));

        scheduler.scheduleRecurringTask(new RecurringTask("Recurring Task 1", 2, TimeUnit.SECONDS, scheduler), 2, TimeUnit.SECONDS);

        // Wait for some tasks to execute
        Thread.sleep(10000);

        scheduler.shutdown();

    }
}