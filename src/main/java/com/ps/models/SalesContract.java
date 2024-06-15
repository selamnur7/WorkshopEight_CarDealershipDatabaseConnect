package com.ps.models;

public class SalesContract extends Contract {
    private float salesTaxAmount = .05f;
    private int processingFee = 295;
    private int recordingFee = 100;
    private boolean isFinanced = true;
    public SalesContract(String dateOfContract, String customerName, String customerEmail, String vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }

    public SalesContract(String dateOfContract, String customerName, String customerEmail, String vehicleSold, float salesTaxAmount, int processingFee, int recordingFee) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.processingFee = processingFee;
        this.recordingFee = recordingFee;
    }

    @Override
    public float getTotalPrice() {
        if (this.totalPrice < 10_000) {
            return (this.totalPrice + (this.totalPrice * salesTaxAmount) + recordingFee + processingFee);
        } else {
            return (this.totalPrice + (this.totalPrice * salesTaxAmount) + recordingFee + 495);
        }
    }

    @Override
    public float getMonthlyPayment() {
        if (this.totalPrice < 10_000 && isFinanced) {
            System.out.println("Monthly Payment for 24 months");
            return (getTotalPrice() * 0.0525f);
        } else if (this.totalPrice >= 10_000 && isFinanced) {
            System.out.println("Monthly Payment for 48 months");
            return (getTotalPrice() * 0.0425f);
        }
        return totalPrice;
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
