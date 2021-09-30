package me.pasindu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TenDigitDecoder extends TwelveDigitDecoder {
    private final int nicLength;
    private final String nicNumber;
    private boolean votable;
    private String gender;
    private String month;
    private int day;
    private int issueData;

    public TenDigitDecoder(int nicLength, String nicNumber) {
        super(nicLength, nicNumber);
        this.nicLength = nicLength;
        this.nicNumber = nicNumber;
    }

    @Override
    public void nicDecode() {
        String yearString = nicNumber.substring(0,2);
        int year = Integer.parseInt(yearString);

        String dayOfYearString = nicNumber.substring(2,5);
        int dayOfYear = Integer.parseInt(dayOfYearString);

        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR,year);
        calender.set(Calendar.DAY_OF_YEAR,dayOfYear);

        Date date = calender.getTime();
        DateFormat formatter = new SimpleDateFormat("MMMM dd");
        String dayString = formatter.format(date);

        System.out.println("Year: " + year + "\nDay Of Year: " + dayOfYear + "\n" + dayString);

    }
}
