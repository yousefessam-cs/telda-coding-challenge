import java.util.Map;
import java.util.Scanner;

public class Main {

    static final JobScheduler jobScheduler = new JobScheduler();
    static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        jobScheduler.start();
        boolean exit = false;
        while (!exit) {
            mainMenu();
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    addJob();
                    break;
                case 2:
                    deleteJob();
                    break;
                case 3:
                    jobScheduler.close();
                    exit = true;
                    break;
            }
        }
    }

    static void addJob() {
        System.out.println("Enter expected interval time and frequency time in seconds");
        long expectedInterval = scan.nextLong() * 1000;
        long frequencyTime = scan.nextLong() * 1000;
        jobScheduler.addJob(new TestingJob(expectedInterval, frequencyTime));
    }
    static void deleteJob() {
        System.out.println("Enter job id to delete");
        long jobId = scan.nextLong();
        jobScheduler.deleteJob(jobId);
    }
    static void mainMenu() {
        System.out.println("1- Add Job");
        System.out.println("2- Delete Job");
        System.out.println("3- Exit");
    }
}