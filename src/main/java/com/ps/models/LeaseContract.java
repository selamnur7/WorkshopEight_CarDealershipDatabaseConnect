package com.ps.models;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LeaseContract extends Contract{
    private BasicDataSource dataSource;

    private float endingValue;
    private float leaseFee;

    public LeaseContract(int id, int vin, String dateOfContract, String customerName, String customerEmail, Boolean vehicleSold, float totalPrice, float monthlyPayment, Vehicle vehicle) {
        super(id, vin, dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment, vehicle);
    }



    public float getTotalPrice() {
        float totalPrice = (endingValue + leaseFee);
        return totalPrice;
    }

    @Override
    public float getMonthlyPayment() {
        float monthlyPayment = (getTotalPrice() * .04f);
        return monthlyPayment;
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
