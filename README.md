# Job-Scheduler

This project simulates an in-process crons scheduler implemented in Java.
Java threads will be used to apply concurrency for jobs.

## Main Classes

### The program has 3 main classes which are Job, Job Scheduler and Job Logger.

- Job: an abstract class which has some data about job for scheduling and pure function for execution which will be implemented by derived classes.
- Job Scheduler: it's main purpose is to schedule the jobs executions.
- Job Logger: simple logger class that logs when the job starts and ends.

## Scheduling Concept

The scheduling algorithm depends on sorting the Jobs by their next execution time in the priority queue.
When the current time is the same as the job on top (nearest execution time), the job will be removed from the queue and executed.
After executing the Job, it will compute its next execution time (using frequency time) and add it again to the queue.

## Adding Job

Adding a Job creates a new job after taking input from the client, then it will be added to the scheduler.
The value of next execution time for the first time will be the same time the job is added.

## Deleting Job

Removing jobs from priority queue instantly might be hard, so a hashset is used to mark the job to be deleted in the future.
When the job on the top of the queue is marked as deleted, it will be discarded and not executed anymore.

## Main Data structures

- Priority Queue: it is used to have the jobs sorted by their execution time in sufficient complexity [Log(N) for each operation].
- Hashmap: it is used to register the current working jobs in the scheduler.
- Hashset: it is used to help implement the deletion of a job from the scheduler.

## Logger
Created a singleton class with one instance to log when a job starts or ends.

## Future Work
Usually all the jobs are not the same, some are more important than the others.
Adding priority to the jobs in the future will make the scheduler more efficient.
The priority can be decided statically by the client or dynamically by the amount of time the job has waited (prevents starvation).
