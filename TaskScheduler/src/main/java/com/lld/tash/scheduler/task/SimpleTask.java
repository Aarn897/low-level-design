package com.lld.tash.scheduler.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SimpleTask implements Task {
    private final String name;

    @Override
    public void execute() {
        log.info("Executing simple task: {}", name);
    }
}