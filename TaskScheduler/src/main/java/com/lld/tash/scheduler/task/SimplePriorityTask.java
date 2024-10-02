package com.lld.tash.scheduler.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SimplePriorityTask implements PriorityTask {
    private final String name;
    private final int priority;

    @Override
    public void execute() {
        log.info("Executing priority task: {} with priority: {}", name, priority);
    }

    @Override
    public int getPriority() {
        return priority;
    }
}