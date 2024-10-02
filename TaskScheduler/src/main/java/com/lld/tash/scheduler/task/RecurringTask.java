package com.lld.tash.scheduler.task;

import com.lld.tash.scheduler.scheduler.TaskScheduler;
import java.util.concurrent.TimeUnit;

public class RecurringTask implements Task {
    private final String name;
    private final long interval;
    private final TimeUnit timeUnit;
    private final TaskScheduler scheduler;

    public RecurringTask(String name, long interval, TimeUnit timeUnit, TaskScheduler scheduler) {
        this.name = name;
        this.interval = interval;
        this.timeUnit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override
    public void execute() {
        System.out.println("Executing recurring task: " + name);
        // Re-enqueue the recurring task
        scheduler.scheduleRecurringTask(this, interval, timeUnit);
    }
}