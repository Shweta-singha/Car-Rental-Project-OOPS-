// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// class Car {
//     private String carId;
//     private String brand;
//     private String model;
//     private double basePricePerDay;
//     private boolean isAvailable;

//     public Car(String carId, String brand, String model, double basePricePerDay) {
//         this.carId = carId;
//         this.brand = brand;
//         this.model = model;
//         this.basePricePerDay = basePricePerDay;
//         this.isAvailable = true;
//     }
//     public String getCarId() {
//         return carId;
//     }

//     public String getBrand() {
//         return brand;
//     }

//     public String getModel() {
//         return model;
//     }

//     public double calculatePrice(int rentalDays) {
//         return basePricePerDay * rentalDays;
//     }

//     public boolean isAvailable() {
//         return isAvailable;
//     }

//     public void rent() {
//         isAvailable = false;
//     }

//     public void returnCar() {
//         isAvailable = true;
//     }
// }

// class Customer {
//     private String customerId;
//     private String name;

//     public Customer(String customerId, String name) {
//         this.customerId = customerId;
//         this.name = name;
//     }

//     public String getCustomerId() {
//         return customerId;
//     }

//     public String getName() {
//         return name;
//     }
// }

// class Rental {
//     private Car car;
//     private Customer customer;
//     private int days;

//     public Rental(Car car, Customer customer, int days) {
//         this.car = car;
//         this.customer = customer;
//         this.days = days;
//     }

//     public Car getCar() {
//         return car;
//     }

//     public Customer getCustomer() {
//         return customer;
//     }

//     public int getDays() {
//         return days;
//     }
// }

// class CarRentalSystem {
//     private List<Car> cars;
//     private List<Customer> customers;
//     private List<Rental> rentals;

//     public CarRentalSystem() {
//         cars = new ArrayList<>();
//         customers = new ArrayList<>();
//         rentals = new ArrayList<>();
//     }

//     public void addCar(Car car) {
//         cars.add(car);
//     }

//     public void addCustomer(Customer customer) {
//         customers.add(customer);
//     }

//     public void rentCar(Car car, Customer customer, int days) {
//         if (car.isAvailable()) {
//             car.rent();
//             rentals.add(new Rental(car, customer, days));

//         } else {
//             System.out.println("Car is not available for rent.");
//         }
//     }

//     public void returnCar(Car car) {
//         car.returnCar();
//         Rental rentalToRemove = null;
//         for (Rental rental : rentals) {
//             if (rental.getCar() == car) {
//                 rentalToRemove = rental;
//                 break;
//             }
//         }
//         if (rentalToRemove != null) {
//             rentals.remove(rentalToRemove);

//         } else {
//             System.out.println("Car was not rented.");
//         }
//     }

//     public void menu() {
//         Scanner scanner = new Scanner(System.in);

//         while (true) {
//             System.out.println("===== Car Rental System =====");
//             System.out.println("1. Rent a Car");
//             System.out.println("2. Return a Car");
//             System.out.println("3. Exit");
//             System.out.print("Enter your choice: ");

//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Consume newline

//             if (choice == 1) {
//                 System.out.println("\n== Rent a Car ==\n");
//                 System.out.print("Enter your name: ");
//                 String customerName = scanner.nextLine();

//                 System.out.println("\nAvailable Cars:");
//                 for (Car car : cars) {
//                     if (car.isAvailable()) {
//                         System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
//                     }
//                 }

//                 System.out.print("\nEnter the car ID you want to rent: ");
//                 String carId = scanner.nextLine();

//                 System.out.print("Enter the number of days for rental: ");
//                 int rentalDays = scanner.nextInt();
//                 scanner.nextLine(); // Consume newline

//                 Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
//                 addCustomer(newCustomer);

//                 Car selectedCar = null;
//                 for (Car car : cars) {
//                     if (car.getCarId().equals(carId) && car.isAvailable()) {
//                         selectedCar = car;
//                         break;
//                     }
//                 }

//                 if (selectedCar != null) {
//                     double totalPrice = selectedCar.calculatePrice(rentalDays);
//                     System.out.println("\n== Rental Information ==\n");
//                     System.out.println("Customer ID: " + newCustomer.getCustomerId());
//                     System.out.println("Customer Name: " + newCustomer.getName());
//                     System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
//                     System.out.println("Rental Days: " + rentalDays);
//                     System.out.printf("Total Price: $%.2f%n", totalPrice);

//                     System.out.print("\nConfirm rental (Y/N): ");
//                     String confirm = scanner.nextLine();

//                     if (confirm.equalsIgnoreCase("Y")) {
//                         rentCar(selectedCar, newCustomer, rentalDays);
//                         System.out.println("\nCar rented successfully.");
//                     } else {
//                         System.out.println("\nRental canceled.");
//                     }
//                 } else {
//                     System.out.println("\nInvalid car selection or car not available for rent.");
//                 }
//             } else if (choice == 2) {
//                 System.out.println("\n== Return a Car ==\n");
//                 System.out.print("Enter the car ID you want to return: ");
//                 String carId = scanner.nextLine();

//                 Car carToReturn = null;
//                 for (Car car : cars) {
//                     if (car.getCarId().equals(carId) && !car.isAvailable()) {
//                         carToReturn = car;
//                         break;
//                     }
//                 }

//                 if (carToReturn != null) {
//                     Customer customer = null;
//                     for (Rental rental : rentals) {
//                         if (rental.getCar() == carToReturn) {
//                             customer = rental.getCustomer();
//                             break;
//                         }
//                     }

//                     if (customer != null) {
//                         returnCar(carToReturn);
//                         System.out.println("Car returned successfully by " + customer.getName());
//                     } else {
//                         System.out.println("Car was not rented or rental information is missing.");
//                     }
//                 } else {
//                     System.out.println("Invalid car ID or car is not rented.");
//                 }
//             } else if (choice == 3) {
//                 break;
//             } else {
//                 System.out.println("Invalid choice. Please enter a valid option.");
//             }
//         }

//         System.out.println("\nThank you for using the Car Rental System!");
//     }

// }
// public class Main{
//     public static void main(String[] args) {
//         CarRentalSystem rentalSystem = new CarRentalSystem();

//         Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
//         Car car2 = new Car("C002", "Honda", "Accord", 70.0);
//         Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
//         rentalSystem.addCar(car1);
//         rentalSystem.addCar(car2);
//         rentalSystem.addCar(car3);

//         rentalSystem.menu();
//     }
// }

// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// class DatabaseConnection {
//     private static final String URL = "jdbc:mysql://localhost:3306/CarRentalDB";
//     private static final String USER = "root";
//     private static final String PASSWORD = "Shweta*251198";

//     public static Connection getConnection() {
//         try {
//             return DriverManager.getConnection(URL, USER, PASSWORD);
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw new RuntimeException("Failed to connect to database!");
//         }
//     }
// }

// class Car {
//     private String carId;
//     private String brand;
//     private String model;
//     private double basePricePerDay;
//     private boolean isAvailable;

//     public Car(String carId, String brand, String model, double basePricePerDay) {
//         this.carId = carId;
//         this.brand = brand;
//         this.model = model;
//         this.basePricePerDay = basePricePerDay;
//         this.isAvailable = true;
//     }

//     public String getCarId() { return carId; }
//     public String getBrand() { return brand; }
//     public String getModel() { return model; }
//     public double calculatePrice(int rentalDays) { return basePricePerDay * rentalDays; }
//     public boolean isAvailable() { return isAvailable; }
//     public void rent() { isAvailable = false; }
//     public void returnCar() { isAvailable = true; }
// }

// class Customer {
//     private String customerId;
//     private String name;

//     public Customer(String customerId, String name) {
//         this.customerId = customerId;
//         this.name = name;
//     }

//     public String getCustomerId() { return customerId; }
//     public String getName() { return name; }
// }

// class CarRentalSystem {
//     private List<Car> cars;
//     private List<Customer> customers;

//     public CarRentalSystem() {
//         cars = new ArrayList<>();
//         customers = new ArrayList<>();
//         loadCarsFromDatabase();
//     }

//     private void loadCarsFromDatabase() {
//         try (Connection conn = DatabaseConnection.getConnection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {

//             while (rs.next()) {
//                 cars.add(new Car(
//                         rs.getString("car_id"),
//                         rs.getString("brand"),
//                         rs.getString("model"),
//                         rs.getDouble("base_price")
//                 ));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

// private String getNextCustomerId(Connection conn) throws SQLException {
//     String newId = "CUST1001"; // Default ID if table is empty
//     try (Statement stmt = conn.createStatement();
//          ResultSet rs = stmt.executeQuery("SELECT MAX(customer_id) FROM customers")) {

//         if (rs.next() && rs.getString(1) != null && !rs.getString(1).isEmpty()) {
//             String lastId = rs.getString(1);
//             int lastNum = Integer.parseInt(lastId.replaceAll("[^0-9]", "")); // Extract numbers safely
//             newId = "CUST" + (lastNum + 1);
//         }
//     }
//     return newId;
// }

//     public void rentCar(Car car, Customer customer, int days) {
//         if (car.isAvailable()) {
//             double totalPrice = car.calculatePrice(days);
//             car.rent();

//             try (Connection conn = DatabaseConnection.getConnection()) {
//                 String customerId = getNextCustomerId(conn); // ✅ Get unique customer ID

//                 // ✅ Insert new customer
//                 try (PreparedStatement stmt1 = conn.prepareStatement(
//                         "INSERT INTO customers (customer_id, name) VALUES (?, ?)")) {
//                     stmt1.setString(1, customerId);
//                     stmt1.setString(2, customer.getName());
//                     stmt1.executeUpdate();
//                 }

//                 // ✅ Insert rental record
//                 try (PreparedStatement stmt2 = conn.prepareStatement(
//                         "INSERT INTO rentals (car_id, customer_id, rental_days, total_price) VALUES (?, ?, ?, ?)")) {
//                     stmt2.setString(1, car.getCarId());
//                     stmt2.setString(2, customerId);
//                     stmt2.setInt(3, days);
//                     stmt2.setDouble(4, totalPrice);
//                     stmt2.executeUpdate();
//                 }

//                 System.out.println("✅ New customer added: " + customer.getName() + " (ID: " + customerId + ")");
//                 System.out.println("✅ Car rented successfully!");
//                 System.out.println("💰 Total Rental Price: " + totalPrice);

//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         } else {
//             System.out.println("❌ Car is not available for rent.");
//         }
//     }

//     public void menu() {
//         Scanner scanner = new Scanner(System.in);
//         while (true) {
//             System.out.println("===== Car Rental System =====");
//             System.out.println("1. Rent a Car");
//             System.out.println("2. Exit");
//             System.out.print("Enter your choice: ");
//             int choice = scanner.nextInt();
//             scanner.nextLine();

//             if (choice == 1) {
//                 System.out.print("Enter your name: ");
//                 String customerName = scanner.nextLine();
//                 System.out.println("\nAvailable Cars:");
//                 for (Car car : cars) {
//                     if (car.isAvailable()) {
//                         System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
//                     }
//                 }
//                 System.out.print("\nEnter the car ID you want to rent: ");
//                 String carId = scanner.nextLine().trim();
//                 System.out.print("Enter the number of days for rental: ");
//                 int rentalDays = scanner.nextInt();
//                 scanner.nextLine();
//                 Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
//                 cars.stream().filter(c -> c.getCarId().equals(carId) && c.isAvailable()).findFirst()
//                         .ifPresentOrElse(
//                                 car -> {
//                                     rentCar(car, newCustomer, rentalDays);
//                                     System.out.println("\nCar rented successfully.");
//                                 },
//                                 () -> System.out.println("\nInvalid car selection or car not available for rent.")
//                         );
//             } else if (choice == 2) {
//                 break;
//             } else {
//                 System.out.println("Invalid choice. Please enter a valid option.");
//             }
//         }
//         System.out.println("\nThank you for using the Car Rental System!");
//     }
// }

// public class Main {
//     public static void main(String[] args) {
//         CarRentalSystem rentalSystem = new CarRentalSystem();
//         rentalSystem.menu();
//     }
// }




// import java.sql.*;
// import java.util.*;

// class DatabaseConnection {
//     private static final String URL = "jdbc:mysql://localhost:3306/CarRentalDB";
//     private static final String USER = "root";
//     private static final String PASSWORD = "Shweta*251198";

//     public static Connection getConnection() {
//         try {
//             return DriverManager.getConnection(URL, USER, PASSWORD);
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw new RuntimeException("Failed to connect to database!");
//         }
//     }
// }

// class Car {
//     private String carId;
//     private String brand;
//     private String model;
//     private double basePricePerDay;
//     private boolean isAvailable;

//     public Car(String carId, String brand, String model, double basePricePerDay) {
//         this.carId = carId;
//         this.brand = brand;
//         this.model = model;
//         this.basePricePerDay = basePricePerDay;
//         this.isAvailable = true;
//     }

//     public String getCarId() { return carId; }
//     public String getBrand() { return brand; }
//     public String getModel() { return model; }
//     public double getBasePricePerDay() { return basePricePerDay; }
//     public double calculatePrice(int rentalDays) { return basePricePerDay * rentalDays; }
//     public boolean isAvailable() { return isAvailable; }
//     public void rent() { isAvailable = false; }
//     public void returnCar() { isAvailable = true; }
// }

// class Customer {
//     private String customerId;
//     private String name;

//     public Customer(String customerId, String name) {
//         this.customerId = customerId;
//         this.name = name;
//     }

//     public String getCustomerId() { return customerId; }
//     public String getName() { return name; }
// }

// class Admin {
//     private Connection conn;

//     public Admin() {
//         this.conn = DatabaseConnection.getConnection();
//     }

//     // Add a new car
//     public void addCar(String carId, String brand, String model, double basePrice) {
//         String sql = "INSERT INTO cars (car_id, brand, model, base_price) VALUES (?, ?, ?, ?)";
//         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//             stmt.setString(1, carId);
//             stmt.setString(2, brand);
//             stmt.setString(3, model);
//             stmt.setDouble(4, basePrice);
//             stmt.executeUpdate();
//             System.out.println("✅ Car added successfully!");
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Remove a car
//     public void removeCar(String carId) {
//         String sql = "DELETE FROM cars WHERE car_id = ?";
//         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//             stmt.setString(1, carId);
//             int rowsAffected = stmt.executeUpdate();
//             if (rowsAffected > 0) {
//                 System.out.println("✅ Car removed successfully!");
//             } else {
//                 System.out.println("❌ Car not found.");
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Update car price
//     public void updateCarPrice(String carId, double newPrice) {
//         String sql = "UPDATE cars SET base_price = ? WHERE car_id = ?";
//         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//             stmt.setDouble(1, newPrice);
//             stmt.setString(2, carId);
//             int rowsAffected = stmt.executeUpdate();
//             if (rowsAffected > 0) {
//                 System.out.println("✅ Car price updated successfully!");
//             } else {
//                 System.out.println("❌ Car not found.");
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // View all cars
//     public void viewCars() {
//         String sql = "SELECT * FROM cars";
//         try (Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery(sql)) {
//             System.out.println("===== Available Cars =====");
//             while (rs.next()) {
//                 System.out.println(rs.getString("car_id") + " - " + rs.getString("brand") +
//                         " " + rs.getString("model") + " (₹" + rs.getDouble("base_price") + "/day)");
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }

// class CarRentalSystem {
//     private List<Car> cars;

//     public CarRentalSystem() {
//         cars = new ArrayList<>();
//         loadCarsFromDatabase();
//     }

//     private void loadCarsFromDatabase() {
//         try (Connection conn = DatabaseConnection.getConnection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {

//             while (rs.next()) {
//                 cars.add(new Car(
//                         rs.getString("car_id"),
//                         rs.getString("brand"),
//                         rs.getString("model"),
//                         rs.getDouble("base_price")
//                 ));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public void menu() {
//         Scanner scanner = new Scanner(System.in);
//         Admin admin = new Admin();

//         while (true) {
//             System.out.println("===== Car Rental System =====");
//             System.out.println("1. Rent a Car");
//             System.out.println("2. Admin Panel");
//             System.out.println("3. Exit");
//             System.out.print("Enter your choice: ");
//             int choice = scanner.nextInt();
//             scanner.nextLine();

//             if (choice == 1) {
//                 rentCarMenu(scanner);
//             } else if (choice == 2) {
//                 adminPanel(scanner, admin);
//             } else if (choice == 3) {
//                 break;
//             } else {
//                 System.out.println("Invalid choice. Please enter a valid option.");
//             }
//         }
//     }

//     private void rentCarMenu(Scanner scanner) {
//         System.out.print("Enter your name: ");
//         String customerName = scanner.nextLine();

//         System.out.println("\nAvailable Cars:");
//         for (Car car : cars) {
//             if (car.isAvailable()) {
//                 System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
//             }
//         }

//         System.out.print("\nEnter the car ID you want to rent: ");
//         String carId = scanner.nextLine().trim();
//         System.out.print("Enter the number of days for rental: ");
//         int rentalDays = scanner.nextInt();
//         scanner.nextLine();

//         cars.stream().filter(c -> c.getCarId().equals(carId) && c.isAvailable()).findFirst()
//                 .ifPresentOrElse(
//                         car -> {
//                             rentCar(car, customerName, rentalDays);
//                             System.out.println("\nCar rented successfully.");
//                         },
//                         () -> System.out.println("\nInvalid car selection or car not available for rent.")
//                 );
//     }

//     private void rentCar(Car car, String customerName, int days) {
//         double totalPrice = car.calculatePrice(days);
//         car.rent();
//         System.out.println("✅ Car rented successfully!");
//         System.out.println("💰 Total Rental Price: ₹" + totalPrice);
//     }

//     private void adminPanel(Scanner scanner, Admin admin) {
//         while (true) {
//             System.out.println("\n===== Admin Panel =====");
//             System.out.println("1. Add Car");
//             System.out.println("2. Remove Car");
//             System.out.println("3. Update Car Price");
//             System.out.println("4. View Cars");
//             System.out.println("5. Back to Main Menu");
//             System.out.print("Enter your choice: ");
//             int choice = scanner.nextInt();
//             scanner.nextLine();

//             if (choice == 1) {
//                 System.out.print("Enter Car ID: ");
//                 String carId = scanner.nextLine();
//                 System.out.print("Enter Brand: ");
//                 String brand = scanner.nextLine();
//                 System.out.print("Enter Model: ");
//                 String model = scanner.nextLine();
//                 System.out.print("Enter Base Price per day: ");
//                 double price = scanner.nextDouble();
//                 admin.addCar(carId, brand, model, price);
//             } else if (choice == 2) {
//                 System.out.print("Enter Car ID to remove: ");
//                 String carId = scanner.nextLine();
//                 admin.removeCar(carId);
//             } else if (choice == 3) {
//                 System.out.print("Enter Car ID to update: ");
//                 String carId = scanner.nextLine();
//                 System.out.print("Enter new Base Price per day: ");
//                 double price = scanner.nextDouble();
//                 admin.updateCarPrice(carId, price);
//             } else if (choice == 4) {
//                 admin.viewCars();
//             } else if (choice == 5) {
//                 break;
//             }
//         }
//     }
// }

// public class Main {
//     public static void main(String[] args) {
//         CarRentalSystem rentalSystem = new CarRentalSystem();
//         rentalSystem.menu();
//     }
// }

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

class CarRentalGUI {
    private JFrame frame;
    private JTable carTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, daysField;
    private JButton rentButton;

    public CarRentalGUI() {
        frame = new JFrame("Car Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel();
        carTable = new JTable(tableModel);
        tableModel.addColumn("Car ID");
        tableModel.addColumn("Brand");
        tableModel.addColumn("Model");
        tableModel.addColumn("Price/Day");
        loadCarsFromDatabase();

        JScrollPane scrollPane = new JScrollPane(carTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input fields and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Your Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Rental Days:"));
        daysField = new JTextField();
        inputPanel.add(daysField);
        rentButton = new JButton("Rent Selected Car");
        inputPanel.add(rentButton);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Button Action
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentCar();
            }
        });

        frame.setVisible(true);
    }

    private void loadCarsFromDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("car_id"));
                row.add(rs.getString("brand"));
                row.add(rs.getString("model"));
                row.add(String.valueOf(rs.getDouble("base_price")));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rentCar() {
        int selectedRow = carTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a car to rent.");
            return;
        }
        
        String carId = (String) tableModel.getValueAt(selectedRow, 0);
        String customerName = nameField.getText();
        int rentalDays;
        
        try {
            rentalDays = Integer.parseInt(daysField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number of days.");
            return;
        }
        
        double pricePerDay = Double.parseDouble((String) tableModel.getValueAt(selectedRow, 3));
        double totalPrice = pricePerDay * rentalDays;
        
        JOptionPane.showMessageDialog(frame, "Car rented successfully!\nTotal Price: ₹" + totalPrice);
    }

    public static void main(String[] args) {
        new CarRentalGUI();
    }
}
