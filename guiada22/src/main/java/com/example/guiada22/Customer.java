package com.example.guiada22;

public class Customer {
    public int customerNumber;
    public String customerName;
    public String contactFirstName;
    public String contactLastName;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String state;
    public String country;
    public String postalCode;
    public String phone;
    public float creditLimit;
    public int salesRepEmployeeNumber;
    public Customer() {}
    public Customer(int customerNumber, String customerName, String contactFirstName,
                    String contactLastName, String addressLine1, String addressLine2, String city,
                    String state, String country, String postalCode, String phone, float creditLimit,
                    int salesRepEmployeeNumber) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }
}