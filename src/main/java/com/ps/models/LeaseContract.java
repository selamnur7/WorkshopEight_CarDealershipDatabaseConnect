package com.ps.models;

public class LeaseContract extends Contract{
    private float endingValue;
    private float leaseFee;
    public LeaseContract(String dateOfContract, String customerName, String customerEmail, String vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, String vehicleSold, float endingValue, float leaseFee) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.endingValue = .5f * this.totalPrice;
        this.leaseFee = .07f * this.totalPrice;
    }

    public float getTotalPrice() {
        return (endingValue + leaseFee);
    }

    @Override
    public float getMonthlyPayment() {
        return (getTotalPrice() * .04f);
    }

    public float getEndingValue() {
        return endingValue;
    }

    public void setEndingValue(float endingValue) {
        this.endingValue = endingValue;
    }

    public float getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(float leaseFee) {
        this.leaseFee = leaseFee;
    }
}
