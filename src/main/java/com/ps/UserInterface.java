package com.ps;

import com.ps.DAO.DealershipDAO;
import com.ps.DAO.LeaseContractDAO;
import com.ps.DAO.SalesContractDAO;
import com.ps.DAO.VehicleDAO;
import com.ps.models.Dealership;
import com.ps.models.SalesContract;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static DealershipDAO dealershipDAO;
    private static VehicleDAO vehicleDAO;
    private static LeaseContractDAO leaseContractDAO;
    private static SalesContractDAO salesContractDAO;
    private static Scanner scanner = new Scanner(System.in);

    public static void display(String[] args) {
        init(args);

        int mainMenuCommand;

        do {
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
                    System.out.println("Will you be (l)easing or (s)elling? ");
                    String leaseOrSell = scanner.nextLine();
                    switch (leaseOrSell) {
                        case "l":
                            LocalDate thisDay = LocalDate.now();
                            int currentYear = thisDay.getYear();
                            String dateOfContract = thisDay.toString();

                            System.out.println("What is your name?");
                            String customerName = scanner.nextLine();
                            scanner.nextLine();

                            System.out.println("What is your email?");
                            String customerEmail = scanner.nextLine();

                            System.out.println("What is the vin number of the car you are looking to lease?");
                            int userVin = scanner.nextInt();
                            break;
                        case "s":
                            break;
                    }
                    break;
                case 1:
                    System.out.println("What is the minimum price for your search?");
                    int minPrice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("What is the maximum price for your search?");
                    int maxPrice = scanner.nextInt();
                    List<Vehicle> vehiclesPrice = vehicleDAO.vehiclesByPrice(minPrice, maxPrice);
                    for(Vehicle vehicle: vehiclesPrice){
                        System.out.printf("%d) %d %s %s %s %s %b %d %d\n",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getSold(),
                                vehicle.getOdometer(),
                                vehicle.getPrice()
                        );
                    }
                    break;
                case 2:
                    System.out.println("What is the make of the vehicle?");
                    String make = scanner.nextLine();
                    System.out.println("What is the model of the Vehicle?");
                    String model = scanner.nextLine();
                    List<Vehicle> vehiclesMakeModel = vehicleDAO.vehiclesByMakeModel(make, model);
                    for(Vehicle vehicle: vehiclesMakeModel){
                        System.out.printf("%d) %d %s %s %s %s %b %d %d\n",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getSold(),
                                vehicle.getOdometer(),
                                vehicle.getPrice()
                        );
                    }
                    break;
                case 3:
                    System.out.println("What is the start year for your search?");
                    int minYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("What is the end year for your search?");
                    int maxYear = scanner.nextInt();
                    List<Vehicle> vehiclesYear = vehicleDAO.vehiclesByYears(minYear, maxYear);
                    for(Vehicle vehicle: vehiclesYear){
                        System.out.printf("%d) %d %s %s %s %s %b %d %d\n",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getSold(),
                                vehicle.getOdometer(),
                                vehicle.getPrice()
                        );
                    }
                    break;
                case 4:
                    System.out.println("What is the color of the Vehicle?");
                    String color = scanner.nextLine();
                    List<Vehicle> vehiclesColor = vehicleDAO.vehiclesByColor(color);
                    for(Vehicle vehicle: vehiclesColor){
                        System.out.printf("%d) %d %s %s %s %s %b %d %d\n",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getSold(),
                                vehicle.getOdometer(),
                                vehicle.getPrice()
                        );
                    }
                    break;
                case 5:
                    System.out.println("What is the minimum mileage of your search?");
                    int minMileage = scanner.nextInt();
                    System.out.println("What is the maximum mileage of your search?");
                    int maxMileage = scanner.nextInt();
                    List<Vehicle> vehiclesMileage = vehicleDAO.vehiclesByOdometer(minMileage, maxMileage);
                    for(Vehicle vehicle: vehiclesMileage){
                        System.out.printf("%d) %d %s %s %s %s %b %d %d\n",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getSold(),
                                vehicle.getOdometer(),
                                vehicle.getPrice()
                        );
                    }
                    break;
                case 6:
                    System.out.println("What type of Vehicle are you looking for?");
                    String type = scanner.nextLine();
                    List<Vehicle> vehiclesType = vehicleDAO.vehiclesByType(type);
                    for(Vehicle vehicle: vehiclesType){
                        System.out.printf("%d) %d %s %s %s %s %b %d %d\n",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getSold(),
                                vehicle.getOdometer(),
                                vehicle.getPrice()
                        );
                    }
                    break;
                case 7:
                    vehicleDAO.getAllVehicles();
                    List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
                    for(Vehicle vehicle: vehicles){
                        System.out.printf("%d) %d %s %s %s %s %b %d %d\n",
                                vehicle.getVin(),
                                vehicle.getYear(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getVehicleType(),
                                vehicle.getColor(),
                                vehicle.getSold(),
                                vehicle.getOdometer(),
                                vehicle.getPrice()
                        );
                    }
                    break;
                case 8:
//                    processAddVehicleRequest();
                    break;
                case 9:
                    System.out.println("What is the VIN of the car you would like to remove? ");
                    int vin = scanner.nextInt();
                    vehicleDAO.deleteVehicle(vin);
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
        leaseContractDAO = new LeaseContractDAO(basicDataSource);
        salesContractDAO = new SalesContractDAO(basicDataSource);
        vehicleDAO = new VehicleDAO(basicDataSource);
    }
}
