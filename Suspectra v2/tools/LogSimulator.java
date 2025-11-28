import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogSimulator {
    private static void writeEntry(String email, String ip, String mac, String status) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String entry = String.format("%s | email=%s | ip=%s | mac=%s | status=%s",
                timestamp,
                (email == null || email.isEmpty()) ? "(empty)" : email,
                (ip == null || ip.isEmpty()) ? "(unknown)" : ip,
                (mac == null || mac.isEmpty()) ? "(unknown)" : mac,
                status);

        String workingLog = System.getProperty("user.dir") + System.getProperty("file.separator") + "login_audit.log";
        String repoLog = "D:" + System.getProperty("file.separator") + "Suspectra" + System.getProperty("file.separator") + "Suspectra v2" + System.getProperty("file.separator") + "login_audit.log";

        try {
            try (FileWriter fw = new FileWriter(workingLog, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(entry);
            }
            System.out.println("[Simulator] Wrote to working-log: " + workingLog);
        } catch (Exception e) {
            System.err.println("[Simulator] Failed to write working-log: " + e.getMessage());
        }

        try {
            try (FileWriter fw = new FileWriter(repoLog, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(entry);
            }
            System.out.println("[Simulator] Wrote to repo-log: " + repoLog);
        } catch (Exception e) {
            System.err.println("[Simulator] Failed to write repo-log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        writeEntry("analyst@example.com", "192.168.1.42", "00-11-22-33-44-55", "Success");
        writeEntry("wrong@ex.com", "192.168.1.42", "00-11-22-33-44-55", "InvalidCredentials");
        writeEntry("", "192.168.1.42", "00-11-22-33-44-55", "Error");
        System.out.println("Simulator finished writing sample entries.");
    }
}
