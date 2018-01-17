import java.io.*;

public class Reminder implements Serializable {
    // reminder's day
    private Date date;

    // reminder's content
    private String content;

    /**
     * Default C'tor
     * @param date
     * @param content
     */
    public Reminder(Date date, String content) {
        this.date = date;
        this.content = content;
    }

    /**
     * String C'tor
     * @param string
     * @throws InvalidDateException
     * @throws InvalidReminderException
     */
    public Reminder(String string) throws InvalidDateException, InvalidReminderException {
        // expect: date-content
        String[] data = string.split("-");
        if (data.length == 0) {
            throw new InvalidReminderException("Corrupted Reminder string structure");
        }

        // Date C'tor may throw InvalidDateException
        this.date = new Date(data[0]);

        if (data.length > 1) {
            this.content = data[1];
        }
    }

    /**
     * @param date to be set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param content to be set
     */
    public void setContent(String content) {
        this.content = new String(content);
    }

    /**
     * @return the reminder's date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

}