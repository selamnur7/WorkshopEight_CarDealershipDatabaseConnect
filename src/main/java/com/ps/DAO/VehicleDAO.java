package com.ps.DAO;

import com.ps.models.Dealership;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private BasicDataSource dataSource;

    public VehicleDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Vehicle vehicle = generateVehicleFromRS(resultSet);
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return vehicles;
        }
    }
    public List<Vehicle> vehiclesByPrice(int minPrice, int maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?"
                );
        ) {
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = generateVehicleFromRS(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> vehiclesByYears(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?"
                );
        ) {
            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = generateVehicleFromRS(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> vehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE vehiclemake = ? AND vehiclemodel = ?"
                );
        ) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = generateVehicleFromRS(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> vehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE color = ?"
                );
        ) {
            preparedStatement.setString(1, color);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = generateVehicleFromRS(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> vehiclesByOdometer(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?"
                );
        ) {
            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = generateVehicleFromRS(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> vehiclesByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE vehicletype = ?"
                );
        ) {
            preparedStatement.setString(1, type);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = generateVehicleFromRS(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public int createVehicle(Vehicle vehicle) {
        int generatedId = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO vehicles(VIN, year, vehiclemake, vehiclemodel, vehicletype, color, SOLD, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getVehicleType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setBoolean(7, vehicle.getSold());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setInt(9, vehicle.getPrice());

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

    public void deleteVehicle(int vin){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM vehicles WHERE VIN = ?"
                );
        ){
            preparedStatement.setInt(1, vin);
            preparedStatement.executeUpdate();
        } catch (SQLException sql){
            sql.printStackTrace();
        }
    }

    public Vehicle generateVehicleFromRS(ResultSet resultSet) throws SQLException {
        int vin = resultSet.getInt("VIN");
        int year = resultSet.getInt("year");
        String vehicleMake = resultSet.getString("vehiclemake");
        String vehicleModel = resultSet.getString("vehiclemodel");
        String vehicleType = resultSet.getString("vehicletype");
        String color = resultSet.getString("color");
        Boolean sold = resultSet.getBoolean("SOLD");
        int odometer = resultSet.getInt("odometer");
        int price = resultSet.getInt("price");




        return new Vehicle(
                vin,
                year,
                vehicleMake,
                vehicleModel,
                vehicleType,
                color,
                sold,
                odometer,
                price
        );
    }
}
