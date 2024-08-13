import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JobLogger {
    private static JobLogger instance;

    static {
        try {
            instance = new JobLogger();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileWriter logFile;

    private JobLogger() throws IOException {
        logFile = new FileWriter("log.txt");
    }

    public static JobLogger getInstance() {
        return instance;
    }

    public synchronized void write(Job j, String action) {
        Date date_obj = new Date();
        DateFormat date_format_obj = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String nextLine = "[" + date_format_obj.format(date_obj) + "]: " + j.id + " " + action + "\n";
        try {
            logFile.write(nextLine);
            logFile.flush();
        } catch (IOException e) {
            System.out.println("An error occured while logging");
        }
    }
}
