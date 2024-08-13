import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class JobScheduler extends Thread {
    private boolean openThread;
    private PriorityQueue<Job> jobQueue;
    private HashSet<Job> deletedJobs;
    private HashMap<Long, Job> workingJobs;

    public JobScheduler() {
        jobQueue = new PriorityQueue<Job>();
        deletedJobs = new HashSet<Job>();
        workingJobs = new HashMap<>();
        openThread = true;
    }

    public synchronized void addJob(Job j) {
        j.nextExecution = System.currentTimeMillis();
        workingJobs.put(j.id, j);
        jobQueue.add(j);
        notify();
    }

    public void deleteJob(Long jobId) {
        Job removedJob = workingJobs.get(jobId);
        deletedJobs.add(removedJob);
        workingJobs.remove(jobId);
    }

    public synchronized void close() {
        openThread = false;
        notify();
    }

    @Override
    public void run() {
        while (true) {
            if (jobQueue.isEmpty()) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (!openThread) {
                break;
            }
            Job nextJob = jobQueue.remove();
            if (deletedJobs.contains(nextJob)) {
                deletedJobs.remove(nextJob);
                continue;
            }
            if (nextJob.nextExecution > System.currentTimeMillis()) {
                try {
                    sleep(nextJob.nextExecution - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            new Thread(nextJob).start();
            nextJob.nextExecution = System.currentTimeMillis() + nextJob.frequencyTime;
            jobQueue.add(nextJob);
        }
    }
}
