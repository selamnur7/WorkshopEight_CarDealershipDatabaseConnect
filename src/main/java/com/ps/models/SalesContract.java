package com.ps.models;

public class SalesContract extends Contract {
    private float salesTaxAmount = .05f;
    private int processingFee = 295;
    private int recordingFee = 100;
    private boolean isFinanced;

    public SalesContract(int id, int vin, String dateOfContract, String customerName, String customerEmail, Boolean vehicleSold, float totalPrice, float monthlyPayment, Vehicle vehicle) {
        super(id, vin, dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment, vehicle);
    }


    @Override
    public float getTotalPrice() {
        float totalPrice;
        if (this.totalPrice < 10_000) {
            return totalPrice = (this.totalPrice + (this.totalPrice * salesTaxAmount) + recordingFee + processingFee);
        } else {
            return totalPrice = (this.totalPrice + (this.totalPrice * salesTaxAmount) + recordingFee + 495);
        }
    }

    @Override
    public float getMonthlyPayment() {
        float totalPrice;
        if (this.totalPrice < 10_000 && isFinanced) {
            System.out.println("Monthly Payment for 24 months");
            return totalPrice = (getTotalPrice() * 0.0525f);
        } else if (this.totalPrice >= 10_000 && isFinanced) {
            System.out.println("Monthly Payment for 48 months");
            return totalPrice = (getTotalPrice() * 0.0425f);
        } else {
            return totalPrice = this.totalPrice;
        }
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public float getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(float salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }
}
