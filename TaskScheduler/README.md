# Design a task scheduler 

## Requirements
1. Handles multiple types of tasks: simple, priority, and recurring.
2. Supports priority-based execution, allowing tasks with different priority levels to be executed in the correct order.
3. Executes tasks concurrently and dynamically adjusts the number of worker threads based on system resources.
4. Implements a retry mechanism for failed tasks, ensuring tasks are re-attempted up to a configurable limit.