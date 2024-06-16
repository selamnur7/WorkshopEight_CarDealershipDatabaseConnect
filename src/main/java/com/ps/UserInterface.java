package com.ps;

import com.ps.DAO.DealershipDAO;
import com.ps.models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static DealershipDAO dealershipDAO;
    private static Scanner scanner = new Scanner(System.in);

    public static void display(String[] args) {
        init(args);

        int mainMenuCommand;

        do {
            // Needs refactoring
            System.out.println("0- SELL/LEASE A VEHICLE");
            System.out.println("1- Find vehicles within a price range");
            System.out.println("2- Find vehicles by make / model");
            System.out.println("3- Find vehicles by year range");
            System.out.println("4- Find vehicles by color");
            System.out.println("5- Find vehicles by mileage range");
            System.out.println("6- Find vehicles by type (sedan, truck, SUV, van)");
            System.out.println("7- List ALL vehicles");
            System.out.println("8- Add a vehicle");
            System.out.println("9- Remove a vehicle");
            System.out.println("99- Quit");

            System.out.print("Please choose an option: ");

            mainMenuCommand = scanner.nextInt();

            switch (mainMenuCommand) {
                case 0:
//                    processSellLeaseVehicle();
                    break;
                case 1:
//                    processGetByPriceRequest();
                    break;
                case 2:
//                   processGetByMakeModelRequest();
                    break;
                case 3:
//                    processGetByYearRequest();
                    break;
                case 4:
//                    processGetByColorRequest();
                    break;
                case 5:
//                    processGetByMileageRequest();
                    break;
                case 6:
//                    processGetByVehicleTypeRequest();
                    break;
                case 7:
//                    processGetAllVehiclesRequest();
                    break;
                case 8:
//                    processAddVehicleRequest();
                    break;
                case 9:
//                    processRemoveVehicleRequest();
                    break;
                case 99:
                    break;
            }
        } while (mainMenuCommand != 99);
    }

    private static void init(String[] args) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dealership_db");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(args[0]);
        basicDataSource.setPassword(args[1]);
        dealershipDAO = new DealershipDAO(basicDataSource);
    }
}
