package com.lld.tash.scheduler.task;

public interface PriorityTask extends Task, Comparable<PriorityTask> {
    int getPriority();

    @Override
    default int compareTo(PriorityTask other) {
        return Integer.compare(this.getPriority(), other.getPriority()); // Lower value = higher priority
    }
}