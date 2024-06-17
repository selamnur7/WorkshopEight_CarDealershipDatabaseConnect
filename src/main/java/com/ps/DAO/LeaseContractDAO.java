package com.ps.DAO;

import com.ps.models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LeaseContractDAO {
    private BasicDataSource dataSource;

    public LeaseContractDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public int createLease(LeaseContract leaseContract) {
        int generatedId = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO sales_contracts(id, VIN, date, customer_name, customer_email, is_SOLD," +
                                " total_price, monthly_payment, ending_value, lease_fee)" +
                                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, leaseContract.getId());
            preparedStatement.setInt(2, leaseContract.getVin());
            preparedStatement.setString(3, leaseContract.getDateOfContract());
            preparedStatement.setString(4, leaseContract.getCustomerName());
            preparedStatement.setString(5, leaseContract.getCustomerEmail());
            preparedStatement.setBoolean(6, leaseContract.getVehicleSold());
            preparedStatement.setFloat(7, leaseContract.getTotalPrice());
            preparedStatement.setFloat(8, leaseContract.getMonthlyPayment());
            preparedStatement.setFloat(9, leaseContract.getEndingValue());
            preparedStatement.setFloat(10, leaseContract.getLeaseFee());




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
