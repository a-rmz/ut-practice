
package iteso.mx.ut_practice;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.StringTokenizer;

class Date {

    private static final int TYPE_DAY = 0;
    private static final int TYPE_MONTH = 1;
    private static final int TYPE_YEAR = 2;

    private String date;

    public Date() {

    }

    public Date(String date) {
        setDate(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected String calculateAge() {
        String dateArr[];

        // Use regex to validate
        String datePattern = "\\d{2}\\/\\d{2}\\/\\d{4}";

        if(date.matches(datePattern)) {
            dateArr = date.split("/");

            if(isValidDate(
                    Integer.parseInt(dateArr[0]),
                    Integer.parseInt(dateArr[1]),
                    Integer.parseInt(dateArr[2]))
                    ) {

                LocalDate birthDay = LocalDate.of(
                        Integer.parseInt(dateArr[2]),
                        Integer.parseInt(dateArr[1]),
                        Integer.parseInt(dateArr[0]));
                LocalDate today = LocalDate.now();

                if( !Period.between(birthDay, today).isNegative() ) {
                    return Period.between(birthDay, today).getYears() + " years, " +
                            Period.between(birthDay, today).getMonths() + " months and " +
                            Period.between(birthDay, today).getDays() + " days";
                }
            }
        }
        return "Please enter a date with a valid format";
    }

    protected boolean isValidDate(int day, int month, int year) {
        boolean dateIsValid = true;
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            dateIsValid = false;
        }
        return dateIsValid;
    }

/*
    public static void main(String[] args) {
        String inputDate = JOptionPane.showInputDialog("Please enter a date");

        JFrame frame = new JFrame("Date Calculator");
        JOptionPane.showMessageDialog(frame, calculateAge(inputDate));
    }
*/
}