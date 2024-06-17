package com.ps.DAO;

import com.ps.models.LeaseContract;
import com.ps.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SalesContractDAO {
    private BasicDataSource dataSource;

    public SalesContractDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public int createSale(SalesContract salesContract) {
        int generatedId = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO sales_contracts(id, VIN, date, customer_name, customer_email, is_SOLD," +
                                " total_price, monthly_payment, sales_tax, processing_fee, recording_fee, is_financed)" +
                                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, salesContract.getId());
            preparedStatement.setInt(2, salesContract.getVin());
            preparedStatement.setString(3, salesContract.getDateOfContract());
            preparedStatement.setString(4, salesContract.getCustomerName());
            preparedStatement.setString(5, salesContract.getCustomerEmail());
            preparedStatement.setBoolean(6, salesContract.getVehicleSold());
            preparedStatement.setFloat(7, salesContract.getTotalPrice());
            preparedStatement.setFloat(8, salesContract.getMonthlyPayment());
            preparedStatement.setFloat(9, salesContract.getSalesTaxAmount());
            preparedStatement.setFloat(10, salesContract.getProcessingFee());
            preparedStatement.setFloat(11, salesContract.getRecordingFee());
            preparedStatement.setBoolean(12, salesContract.isFinanced());


            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    generatedId = keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}
