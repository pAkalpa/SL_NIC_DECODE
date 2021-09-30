package me.pasindu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        int year = Integer.parseInt(yearString) + 1900;

        String dayOfYearString = nicNumber.substring(2,5);
        int dayOfYear = Integer.parseInt(dayOfYearString);

        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR,year);
        calender.set(Calendar.DAY_OF_YEAR,dayOfYear-1);
        Calendar calenderNow = Calendar.getInstance();


        Date date = calender.getTime();
        Date dateToday = calenderNow.getTime();
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String dayString = formatter.format(date);

        long differenceDate = dateToday.getTime() - date.getTime();

        TimeUnit time = TimeUnit.DAYS;
        long differenceYears = time.convert(differenceDate, TimeUnit.MILLISECONDS);

//        System.out.println("Year: " + year + "\nDay Of Year: " + dayOfYear + "\n" + dayString);
        System.out.println(differenceYears/365);
    }
}
