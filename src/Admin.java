import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class Admin {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";  // Change this for security

    // ✅ Admin Login
    public static boolean login(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    // ✅ Add a New Car
    public static void addCar(Scanner scanner) {
        System.out.print("Enter Car ID: ");
        String carId = scanner.nextLine().trim();
        System.out.print("Enter Car Brand: ");
        String brand = scanner.nextLine().trim();
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine().trim();
        System.out.print("Enter Base Price per Day: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO cars (car_id, brand, model, base_price) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, carId);
            stmt.setString(2, brand);
            stmt.setString(3, model);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
            System.out.println("✅ Car added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Remove a Car
    public static void removeCar(Scanner scanner) {
        System.out.print("Enter Car ID to remove: ");
        String carId = scanner.nextLine().trim();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM cars WHERE car_id = ?")) {
            stmt.setString(1, carId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Car removed successfully!");
            } else {
                System.out.println("❌ Car not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Update Car Rental Price
    public static void updateCarPrice(Scanner scanner) {
        System.out.print("Enter Car ID to update price: ");
        String carId = scanner.nextLine().trim();
        System.out.print("Enter new price per day: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE cars SET base_price = ? WHERE car_id = ?")) {
            stmt.setDouble(1, newPrice);
            stmt.setString(2, carId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Car price updated successfully!");
            } else {
                System.out.println("❌ Car not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}