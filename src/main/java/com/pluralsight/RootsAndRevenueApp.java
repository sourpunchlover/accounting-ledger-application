package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RootsAndRevenueApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        starterScreen(scanner);
        homeScreen(scanner); //passing so it can be used in homeScreen method

    }
    //Customized starter screen
    public static void starterScreen(Scanner scanner) {
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String pink = "\u001B[35m";
        String reset = "\u001B[0m";

        System.out.println(green + "\n🌱 🌿 🪴 🌱 🌿 🪴 🌱 🌿 🪴 🌱 🌿 🪴 🌱 🌿 🪴" + reset);

        // typewriter effect on title
        String title = "\n  R O O T S  &  R E V E N U E\n";
        try {
            for (char c : title.toCharArray()) {
                System.out.print(yellow + c + reset);
                Thread.sleep(80);
            }

            // subtitle
            System.out.println(blue + "\n      Your Garden Business Tracker 🪴" + reset);
            System.out.println(pink + "     Grow Your Business, Track Your Roots" + reset);
            System.out.println(green + "\n🌱 🌿 🪴 🌱 🌿 🪴 🌱 🌿 🪴 🌱 🌿 🪴 🌱 🌿 🪴\n" + reset);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print(yellow + "Press Enter to start growing your revenue... 🌱 " + reset);
        scanner.nextLine();
    }
    public static void homeScreen(Scanner scanner) {

        while (true) {
            System.out.println("What would you like to do today? ");
            System.out.println("\tD) Add Deposit");
            System.out.println("\tP) Make Payment(Debit)");
            System.out.println("\tL) Ledger");
            System.out.println("\tX) Exit");
            System.out.print("Enter your selection: ");
            String userOption = scanner.nextLine().toUpperCase();

            switch (userOption) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    makePayment(scanner);
                    break;
                case "L":
                    displayLedgerScreen(scanner);
                    break;
                case "X":
                    //exit system
                    System.out.println("Thanks for using Roots And Revenue!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option entered. Try again.");
                    System.out.println("\n\n");
            }//closing curly for switch

        }//closing curly for while


    }//closing curly for homeScreen method

    public static void addDeposit(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter deposit amount (numbers only, no $): ");
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

    }//closing curly for addDeposit method

    public static void makePayment(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter payment amount (numbers only, no $): ");
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
            buffWriter.write("\n" + transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + String.format("%.2f", -transaction.getAmount()));


            buffWriter.close();
            System.out.println("Payment saved successfully! \uD83C\uDF31"); //confirmation message
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Problem with IO");

        }//closing curly for catch

    }//closing curly for makePayment

    //Ledger needs file & buffered reader
    public static void displayLedgerScreen(Scanner scanner) {
        ArrayList<Transaction> transList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader buffReader = new BufferedReader(fileReader);
            buffReader.readLine();

            String line;
            while((line = buffReader.readLine()) != null) {
                String[] splitData = line.split(Pattern.quote("|"));
                String date = splitData[0];
                String time = splitData[1];
                String description = splitData[2];
                String vendor = splitData[3];
                double amount = Double.parseDouble(splitData[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transList.add(transaction);
            }//closing curly for while
            buffReader.close();
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("Problem with IO");
        }//closing curly for catch

        //Ledger Menu
        while (true) {
            System.out.println("Which would you like to view? ");
            System.out.println("\tA) All");
            System.out.println("\tD) Deposits");
            System.out.println("\tP) Payments");
            System.out.println("\tR) Reports");
            System.out.println("\tH) Home");
            System.out.print("Enter your selection: ");
            String userOption = scanner.nextLine().toUpperCase();

            switch (userOption) {
                case "A":
                    for (int i = transList.size() - 1; i >= 0; i--) {//counting down to display new entries first
                        Transaction t = transList.get(i);
                        System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                }
                    break;
                case "D":
                    for (int i = transList.size() - 1; i >= 0; i--) {
                        Transaction t = transList.get(i);
                        if (t.getAmount() > 0) {
                            System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "P":
                    for (int i = transList.size() - 1; i >= 0; i--) {
                        Transaction t = transList.get(i);
                        if (t.getAmount() < 0) {
                            System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "R":
                    displayReportsScreen(scanner, transList);
                    break;
                case "H":
                    //go back to the home page();
                    return;
                default:
                    System.out.println("Invalid option entered. Try again.");
                    System.out.println("\n");
            }//closing curly for switch

        }//closing curly for while


    }//closing curly for displayLedgerScreen

    //Reports screen
    public static void displayReportsScreen(Scanner scanner, ArrayList<Transaction> transList) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Which would you like to run? ");
            System.out.println("\t1) Month To Date");
            System.out.println("\t2) Previous Month");
            System.out.println("\t3) Year To Date");
            System.out.println("\t4) Previous Year");
            System.out.println("\t5) Search by Vendor");
            System.out.println("\t0) Back");
            System.out.print("Enter your selection: ");
            String userOption = scanner.nextLine().toUpperCase();

            switch (userOption) {
                case "1"://Month To Date means period from start of month to today.
                    for (int i = transList.size() - 1; i >= 0; i--) {
                        Transaction t = transList.get(i);
                        LocalDate transDate = LocalDate.parse(t.getDate(), dateFormatter);
                        if (transDate.getMonthValue() == today.getMonthValue() && transDate.getYear() == today.getYear()) {
                            System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "2":
                    for (int i = transList.size() - 1; i >= 0; i--) {
                        Transaction t = transList.get(i);
                        LocalDate transDate = LocalDate.parse(t.getDate(), dateFormatter);
                        if (transDate.getMonthValue() == today.minusMonths(1).getMonthValue() && transDate.getYear() == today.minusMonths(1).getYear()) {
                            System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }

                    }
                    break;
                case "3"://Year To Date means first day of the current year up to the current date.
                    for (int i = transList.size() - 1; i >= 0; i--) {
                        Transaction t = transList.get(i);
                        LocalDate transDate = LocalDate.parse(t.getDate(), dateFormatter);
                        if (transDate.getYear() == today.getYear()) {
                            System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "4":
                    for (int i = transList.size() - 1; i >= 0; i--) {
                        Transaction t = transList.get(i);
                        LocalDate transDate = LocalDate.parse(t.getDate(), dateFormatter);
                        if (transDate.getYear() == today.minusYears(1).getYear()) {
                            System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "5":
                    //search by vendor
                    System.out.println("Enter vendor name: ");
                    String venName = scanner.nextLine();
                    for (int i = transList.size() - 1; i >= 0; i--) {
                        Transaction t = transList.get(i);
                        if (venName.equalsIgnoreCase(t.getVendor())){
                            System.out.printf("date: %s | time: %s | description: %s | vendor: %s | amount: %.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "0":
                    //go back to the Ledger page
                    return;
                default:
                    System.out.println("Invalid option entered. Try again.");
                    System.out.println("\n");
            }//closing curly for switch

        }//closing curly for while

    }//closing curly for displayReportsScreen
}
