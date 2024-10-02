package com.lld.tash.scheduler.task;

import com.lld.tash.scheduler.scheduler.TaskScheduler;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class RecurringTask implements Task {
    private final String name;
    private final long interval;
    private final TimeUnit timeUnit;
    private final TaskScheduler scheduler;

    @Override
    public void execute() {
        log.info("Executing recurring task: {}", name);
        // Re-enqueue the recurring task
        scheduler.scheduleRecurringTask(this, interval, timeUnit);
    }
}