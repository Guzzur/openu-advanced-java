import java.io.*;

public class Date implements Serializable {
    // maximum days in month
    public final static int MAX_DAYS_IN_MONTH = 31;
    // maximum months in year
    public final static int MAX_MONTHS_IN_YEAR = 12;

    // date fields
    private int day;
    private int month;
    private int year;

    /**
     * C'tor
     * @param year
     * @param month
     * @param day
     * @throws InvalidDateException
     */
    public Date(int day, int month, int year) throws InvalidDateException {
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
    }

    /**
     * Copy C'tor
     * @param other date to copy from
     * @throws InvalidDateException
     */
    public Date(Date other) throws InvalidDateException{
        this.setDay(other.day);
        this.setMonth(other.month);
        this.setYear(other.year);
    }

    /**
     * String parse C'tor
     * @param str representation of the Date
     * @throws InvalidDateException
     */
    public Date(String str) throws InvalidDateException {
        // expect: day/month/year
        String[] date = str.split("/");

        if (date.length != 3)
            throw new InvalidDateException("Corrupted Date string structure");

        try {
            setDay(Integer.parseInt(date[0]));
            setMonth(Integer.parseInt(date[1]));
            setYear(Integer.parseInt(date[2]));
        }
        catch (NumberFormatException e) {
            throw new InvalidDateException("Illegal chars in Date string");
        }
    }

    /**
     * @param other Data to compare with
     * @return true if the Dates are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date)other;
        return otherDate.getDay() == this.getDay() &&
               otherDate.getMonth() == this.getMonth() &&
               otherDate.getDay() == this.getDay();
    }

    /**
     * @return Hashed string representation
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * @param day to be set
     * @throws InvalidDateException
     */
    public void setDay(int day) throws InvalidDateException {
        if (day < 1 || day > MAX_DAYS_IN_MONTH) {
            throw new InvalidDateException("Invalid day");
        }
        this.day = day;
    }

    /**
     * @param month to be set
     * @throws InvalidDateException
     */
    public void setMonth(int month) throws InvalidDateException {
        if (month < 1 || month > MAX_MONTHS_IN_YEAR) {
            throw new InvalidDateException("Invalid month");
        }
        this.month = month;
    }

    /**
     * @param year to be set
     */
    public void setYear(int year) {
        this.year = year;
    }


    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return String representation of Date
     */
    public String toString() {
        return String.format(getDay() + "/" + getMonth() + "/" + getYear());
    }
}
