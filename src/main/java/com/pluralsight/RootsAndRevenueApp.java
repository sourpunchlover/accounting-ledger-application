package com.pluralsight;

import java.util.Scanner;

public class RootsAndRevenueApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome To Roots and Revenue!");
        homeScreen(scanner); //passing so it can be used in homeScreen method

    }
    public static void homeScreen(Scanner scanner) {

        while (true) {
            System.out.println("Select from the following options: ");
            System.out.println("\tD) Add Deposit");
            System.out.println("\tP) Make Payment(Debit)");
            System.out.println("\tL) Display Ledger Screen");
            System.out.println("\tX) Exit");
            System.out.println("Enter your selection: ");
            String userOption = scanner.nextLine().toUpperCase();//

            switch (userOption) {
                case "D":
                    //addDeposit();
                    break;
                case "P":
                    //makePayment();
                    break;
                case "L":
                    //displayLedger();
                    break;
                case "X":
                    //exit system
                    System.out.println("Thanks for using Roots And Revenue!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option entered (press Enter to continue)");
                    System.out.println("\n\n");
            }//closing curly for switch

        }//closing curly for while


    }//closing curly for homeScreen method
}
