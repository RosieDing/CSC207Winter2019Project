package ATM.BankSystem;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Date {
    private static Date date;
    private int periodDay;
    private int periodMonth;
    private int periodYear;


    private void Date() {
    }

    public static Date getDate() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }

    public LocalDate getSystemCurrentTime() {
        Period p = Period.of(periodYear, periodMonth, periodDay);
        LocalDate d = LocalDate.now();
        return d.plus(p);
    }

    public void setDateOfCreation(int day, int month, int year) {
        LocalDate actualDateOfCreation = LocalDate.now();
        LocalDate creationDate = LocalDate.of(year, month, day);
        Period period = Period.between(actualDateOfCreation, creationDate);
        periodDay = period.getDays();
        periodMonth = period.getMonths();
        periodYear = period.getYears();
    }

    @Override
    public String toString() {
        return getSystemCurrentTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


}
