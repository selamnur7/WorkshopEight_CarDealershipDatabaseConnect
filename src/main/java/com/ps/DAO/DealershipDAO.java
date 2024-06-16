package com.ps.DAO;

import com.ps.models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {
    private BasicDataSource dataSource;

    public DealershipDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dealerships");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Dealership dealership = generateDealershipsFromRS(resultSet);
                dealerships.add(dealership);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return dealerships;
        }
    }



    public Dealership getOneDealership(String name) {
        Dealership dealership = null;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM dealerships WHERE name = ?"
                );
        ) {
            preparedStatement.setString(1, name);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    dealership = generateDealershipsFromRS(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dealership;
    }
    public int createDealership(Dealership dealership) {
        int generatedId = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO dealerships(dealership_id, name, address, phone) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, dealership.getId());
            preparedStatement.setString(2, dealership.getName());
            preparedStatement.setString(3, dealership.getAddress());
            preparedStatement.setString(4, dealership.getPhone());
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

    public Dealership generateDealershipsFromRS(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("dealership_id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");


        return new Dealership(
                id,
                name,
                address,
                phone
        );
    }
}
