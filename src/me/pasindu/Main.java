package me.pasindu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConsoleUI();
    }

    private static void ConsoleUI () {
        int inputLength;
        String nicNumber;
        do {
            System.out.print("Enter Your Sri Lankan NIC Number (New 12 Digit or Old 10 Digit): ");
            Scanner input = new Scanner(System.in);
            nicNumber = input.next().toUpperCase();
            inputLength = nicNumber.length();
            if (!(nicNumber.equals("Q")) && !(inputLength == 10 || inputLength == 12)) {
                System.out.println("Invalid NIC No! Try Again.");
            }

            TwelveDigitDecoder decoder_12 = new TwelveDigitDecoder(inputLength, nicNumber);
            TwelveDigitDecoder decoder_10 = new TenDigitDecoder(inputLength, nicNumber);

            switch (inputLength) {
                case 10 -> decoder_10.nicDecode();
                case 12 -> decoder_12.nicDecode();
                default -> {
                }
            }

        } while (!(nicNumber.equals("Q")));

    }
}
