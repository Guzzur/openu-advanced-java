import java.io.*;
import java.util.*;
import java.util.Map.*;
import java.nio.file.*;

public class ReminderStorage {
    // path to the reminders data-file
    private String filePath;

    // the reminders' map
    private Map<Date, String> reminders;

    /**
     * C'tor
     * @param filePath to work with
     */
    public ReminderStorage(String filePath) {
        this.filePath = filePath;
        this.reminders = new HashMap<>();

        try {
            File f = new File(filePath);

            if (f.exists()) {
                this.load();
            }
        }
        catch (NullPointerException e) {
            System.out.println("Invalid file path: " + e.getMessage() + ", exiting...");
            System.exit(1);
        }
    }

    /**
     * Loads reminders from file
     */
    private void load() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(this.filePath)));

            // read whole file until EOF
            while (true) {
                Reminder rmd = (Reminder)inputStream.readObject();
                this.reminders.put(rmd.getDate(), rmd.getContent());
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("Reminder class problem: " + e.getMessage());
            System.exit(1);
        }
        catch (EOFException e) {
            System.out.println("Successfully loaded the reminders from file");
        }
        catch (NullPointerException | IOException e) {
            System.err.println("Something wrong with the data-file: " + e.getMessage() + ", exiting...");
            System.exit(1);
        }
        finally {
            // close file
            close(inputStream);
        }
    }

    /**
     * Saves reminders to file
     */
    private void save() {
        ObjectOutputStream outputStream = null;
        try {
            FileOutputStream fStream = new FileOutputStream(filePath);
            outputStream = new ObjectOutputStream(fStream);

            // take al reminders from map and save'em in file
            for (Entry<Date, String> entry : reminders.entrySet()) {
                Reminder rmd = new Reminder(entry.getKey(), entry.getValue());
                outputStream.writeObject(rmd);
            }
        }
        catch (IOException e) {
            System.out.println("Failed to save reminders: " + e.getMessage() + ", exiting...");
            System.exit(1);
        }
        finally {
            close(outputStream);
        }
    }

    /**
     * @param date to load reminder by
     * @return dated reminder's content
     */
    public String loadReminder(Date date) {
        if (this.reminders.containsKey(date)) {
            return this.reminders.get(date);
        }
        return "";
    }

    /**
     * @param reminder to save in map and file
     */
    public void saveReminder(Reminder reminder) {
        this.reminders.put(reminder.getDate(), reminder.getContent());
        this.save();
    }

    /**
     * @param cloaseable object to close
     */
    private void close(Closeable cloaseable) {
        if (cloaseable != null) {
            try {
                cloaseable.close();
            }
            catch (IOException e) {
                System.out.println("Failed to close file: " + filePath);
            }
        }
    }
}
