package me.pasindu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TenDigitDecoder extends TwelveDigitDecoder {
    private final String nicNumber;
    private final int nicLength;

    /**
     * @param nicLength NIC number length
     * @param nicNumber NIC number as String
     */
    public TenDigitDecoder(int nicLength, String nicNumber) {
        super(nicLength, nicNumber);
        this.nicNumber = nicNumber;
        this.nicLength = nicLength;
    }

    /**
     * This Method Decodes 10 Digit NIC Number
     */
    @Override
    public void nicDecode() {
//        First 2 Digits means Birth Year
        String yearString = nicNumber.substring(0,2);
        int year = Integer.parseInt(yearString) + 1900;

//        Digit 3 to 5 means Birth Month + Birth Day + Gender
        String dayOfYearString = nicNumber.substring(2,5);
        int dayOfYear = Integer.parseInt(dayOfYearString);

//        Digit 6 to 9 means serial number of the issued day
        String serialNumber = nicNumber.substring(5,9);

        Calendar calender = Calendar.getInstance(); // Create Calendar Instance for Decode Birthday
        Calendar calenderNow = Calendar.getInstance(); // Create calendar Instance for get today Date
        calender.set(Calendar.YEAR,year); // Set Decoded Year as Year

        String gender;
        if (dayOfYear < 500) {
            gender = "Male";
            calender.set(Calendar.DAY_OF_YEAR,dayOfYear-1); // Set Day Of Year as NIC digits 3 to 5
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

        String issueData = nicNumber.substring(5, 9);

//        NIC Last Digit is showing Vote eligibility. V == Eligible for Vote : X == Not Eligible for Vote
        boolean votable = nicNumber.substring(nicLength-1).equals("V");

        String eligibility = (votable)? "Eligible" : "not Eligible";

        System.out.println("\nNIC No: " + nicNumber + "\nDate Of Birth: " + dayString + "\nGender: " + gender + "\nSerial Number: " + serialNumber + "\nThis Person is " + eligibility + " for Voting." + "\nThis Person is " + differenceYears/365 + " Years Old.\n\n");
    }
}
