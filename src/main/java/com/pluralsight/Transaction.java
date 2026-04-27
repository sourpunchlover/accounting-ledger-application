package com.pluralsight;

public class Transaction {
    //Making all data except for amount String for now. Will convert to LocalDate & LocalTime if needed.
    private String date, time, description, vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //Only adding getters because you normally don't change a transaction after it is created.
    //Since setters update values, they're not needed in this case.
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
}
