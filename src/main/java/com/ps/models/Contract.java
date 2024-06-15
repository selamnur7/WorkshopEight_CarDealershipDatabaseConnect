package com.ps.models;
public abstract class Contract {
    private String dateOfContract;
    private String customerName;
    private String customerEmail;
    private String vehicleSold;
    protected float totalPrice;
    protected float monthlyPayment;
    protected Vehicle vehicle;


    public Contract(String dateOfContract, String customerName, String customerEmail, String vehicleSold) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }
    public abstract float getTotalPrice();
    public abstract float getMonthlyPayment();


    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(String vehicleSold) {
        this.vehicleSold = vehicleSold;
    }



}
