package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            String userOption = scanner.nextLine().toUpperCase();

            switch (userOption) {
                case "D":
                    addDepositScreen(scanner);
                    break;
                case "P":
                    //makePaymentScreen();
                    break;
                case "L":
                    //displayLedgerScreen();
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

    public static void addDepositScreen(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        //using to auto gen date & time & to keep in String format
        LocalDate date = LocalDate.now();//gets current date
        LocalTime time = LocalTime.now();//gets current time
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = date.format(dateFormatter);
        String formattedTime = time.format(timeFormatter);

        Transaction transaction = new Transaction(formattedDate,formattedTime,description,vendor,amount);

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);
            buffWriter.write("\n" + transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + String.format("%.2f", transaction.getAmount()));


            buffWriter.close();
            System.out.println("Deposit saved successfully! \uD83C\uDF31"); //confirmation message
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Problem with IO");

        }//closing curly for catch

    }//closing curly for addDepositScreen method
}
