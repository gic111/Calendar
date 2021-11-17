package com.company;

import java.util.Scanner;

public class Calendar {
    // First day of 1800 year is equal 3 ( Wednesday )
    private static final int YEAR = 1800;
    private static final int FIRST_DAY_IN_1800  = 3;

    public static void test() {
        programWork();
    }

    public static void programWork(){

        boolean programWork = true;
        menu();
        while (programWork == true) {
            System.out.println("\nInput your choice ( to show menu press 3 ) : ");
            int choice = validNumber(1,4);
            switch (choice) {
                case 1:
                    System.out.println("Input your year between 1800 -  3000: ");
                    int year = validNumber(1800,3000);
                    System.out.println("Input your month ( 1 - 12 ): ");
                    int month = validNumber(1,12);
                    System.out.println();
                    printMonth(year, month);
                    break;
                case 2:
                    System.out.println("Input your year between 1800 - 3000 : ");
                    year = validNumber(1800,3000);
                    printFullYear(year);
                    break;
                case 3:
                    menu();
                    break;
                case 4:
                    programWork = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private static int validNumber(int start, int end) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.nextLine();
        while(number < start || number > end) {
            System.out.println("You must input valid number.");
            number = scanner.nextInt();
            scanner.nextLine();
        }
        return number;
    }


    private static void printMonth(int year, int month) {
        printTitleMoth(year, month);
        calendarBody(year,month);
    }

    private static void printFullYear(int year) {
        for(int i = 1 ; i <= 12; i++) {
            printTitleMoth(year, i);
            calendarBody(year, i);
        }
    }

    private static void printTitleMoth(int year, int month){
        System.out.println("_______________________________");
        System.out.print("\t\t\t" + getMonthName(month));
        System.out.print("\t\t" + year);
        System.out.println();
        System.out.println("_______________________________");
        System.out.println("\tN\tP\tW\tŚ\tC\tP\tS");


    }

    private static String getMonthName(int month){
        switch (month) {
            case 1: return "Styczeń";
            case 2: return "Luty";
            case 3: return "Marzec";
            case 4: return "Kwiecień";
            case 5: return "Maj";
            case 6: return "Czerwiec";
            case 7: return "Lipiec";
            case 8: return "Sierpień";
            case 9: return "Wrzesień";
            case 10: return "Październik";
            case 11: return "Listopad";
            case 12: return "Grudzień";
            default: return "";
        }
    }

    private static void calendarBody(int year, int month){
        for(int i = 0; i < getStartedDay(year, month); i++){
            System.out.print("\t");
        }
        for(int i = 1 ; i <= getMonthDay(year,month); i++){
            System.out.print("\t" + i);
            if((i + getStartedDay(year, month)) % 7 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }

    private static void menu() {
        System.out.println("Menu: ");
        System.out.println("\t 1.Print month");  // option to print only  one month in chosen year
        System.out.println("\t 2.Print year");   // option to print every month in chosen year
        System.out.println("\t 3.Menu");
        System.out.println("\t 4.Exit");
    }


    // return day of Month when year is leap or not.
    private static int getMonthDay(int year, int month){
        switch (month) {
            case 1,3,5,7,8,10,12: return 31;    // for month yanuary , march etc. return 31 day
            case 4,6,9,11 : return 30;          // for month april , june etc. return 30
            case 2:
                if(leapYear(year) == true){    // if year is leap it return 29 , if not return 28
                    return 29;
                }
                else{
                    return 28;
                }
            default:                          // invalid data return -1
                return -1;
        }
    }

    private static int getStartedDay(int year) {
        return (getSubtractDay(year) + FIRST_DAY_IN_2021) % 7;
    }

    private static int getStartedDay(int year, int month) {
        return (getSubtractDay(year,month) + FIRST_DAY_IN_2021) % 7;
    }

    private static int getSubtractDay(int year) {
        int sum = 0;
        if(year >= YEAR) {
            for(int i = year; i > YEAR; i--) {
                if(leapYear(i) == true) {
                    sum += 366;
                }
                else{
                    sum += 365;
                }
            }

        }
        return sum;
    }

    private static int getSubtractDay(int year, int month) {
        int sum = 0;
            if(year >= YEAR) {
                for(int i = year; i > YEAR; i--) {
                    if(leapYear(i) == true) {
                        sum += 366;
                    }
                    else{
                        sum += 365;
                    }
                }
                for(int i = 1 ; i < month; i++) {
                    sum += getMonthDay(year, i);
                }
            }
            else{
                return -1;
            }
        return sum;
    }

    private static boolean leapYear(int year) {
        if(year % 400 == 0 || year % 4 == 0 && year % 100 != 0){
            return true;
        }
        return false;
    }



}
