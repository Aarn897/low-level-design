package com.lld.tash.scheduler.task;

public class SimplePriorityTask implements PriorityTask {
    private final String name;
    private final int priority;

    public SimplePriorityTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public void execute() {
        System.out.println("Executing priority task: " + name + " with priority: " + priority);
    }

    @Override
    public int getPriority() {
        return priority;
    }
}