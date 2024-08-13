public abstract class Job implements Comparable<Job>, Runnable {
    protected long expectedIntervalTime;
    protected long frequencyTime;
    protected long nextExecution;
    protected long id;

    private static int counterId = 0;

    public Job(long expectedIntervalTime, long frequencyTime) {
        this.expectedIntervalTime = expectedIntervalTime;
        this.frequencyTime = frequencyTime;
        id = counterId++;
    }

    public abstract void execute();

    @Override
    public int compareTo(Job j) {
        if (this.nextExecution > j.nextExecution)
            return 1;
        else if (this.nextExecution < j.nextExecution)
            return -1;
        return 0;
    }

    @Override
    public void run() {
        this.execute();
    }
}
