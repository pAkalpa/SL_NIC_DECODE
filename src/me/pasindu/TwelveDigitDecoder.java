package me.pasindu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TwelveDigitDecoder {
    private final int nicLength;
    private final String nicNumber;

    public TwelveDigitDecoder(int nicLength, String nicNumber) {
        this.nicLength = nicLength;
        this.nicNumber = nicNumber;
    }

    public void nicDecode() {
//        First 2 Digits means Birth Year
        String yearString = nicNumber.substring(0,4);
        int year = Integer.parseInt(yearString);

//        Digit 3 to 5 means Birth Month + Birth Day + Gender
        String dayOfYearString = nicNumber.substring(4,7);
        int dayOfYear = Integer.parseInt(dayOfYearString);

//        Digit 6 to 9 means serial number of the issued day
        String serialNumber = nicNumber.substring(7,nicLength-1);

        Calendar calender = Calendar.getInstance(); // Create Calendar Instance for Decode Birthday
        Calendar calenderNow = Calendar.getInstance(); // Create calendar Instance for get today Date
        calender.set(Calendar.YEAR,year); // Set Decoded Year as Year

        String gender;
        if (dayOfYear < 500) {
            gender = "Male";
            calender.set(Calendar.DAY_OF_YEAR,dayOfYear); // Set Day Of Year as NIC digits 3 to 5
        } else {
            gender = "Female";
            int femaleDOY = dayOfYear - 500; // For females dayOfYear subtract 500
            calender.set(Calendar.DAY_OF_YEAR,femaleDOY-1); // Set Day Of Year as 500 - Day Of Year for Females
        }

        Date date = calender.getTime(); // Get Day of Birth
        Date dateToday = calenderNow.getTime(); // Get today Date
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy"); // Create DateFormat Object
        String dayString = formatter.format(date); // format date using created object

        long differenceDate = dateToday.getTime() - date.getTime(); // Subtract Today Date from Date of Birth to Calculate Years
        TimeUnit time = TimeUnit.DAYS; // Create TimeUnit object and assign DAY as time unit
        long differenceYears = time.convert(differenceDate, TimeUnit.MILLISECONDS); // Convert into Days

        System.out.println("\nNIC No: " + nicNumber + "\nDate Of Birth: " + dayString + "\nGender: " + gender + "\nSerial Number: " + serialNumber + "\nThis Person is " + differenceYears/365 + " Years Old.\n\n");
    }
}
