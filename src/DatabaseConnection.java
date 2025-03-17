import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CarRentalDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Shweta*251198"; // ✅ Ensure this is correct

    public static Connection getConnection() {
        try {
            System.out.println("Attempting database connection with user: " + USER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful!");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database!");
        }
    }
}  // ✅ Added missing closing bracket
