public class TestingJob extends Job{
    public TestingJob(long expectedIntervalTime, long frequencyTime) {
        super(expectedIntervalTime, frequencyTime);
    }

    public void execute() {
        JobLogger log = JobLogger.getInstance();
        log.write(this, "started");
        try {
            Thread.sleep(this.expectedIntervalTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.write(this, "ended");
    }
}
