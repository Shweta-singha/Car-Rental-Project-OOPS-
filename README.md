🚗 Car Rental System is a console-based Java application that allows users to rent cars and manage rentals using JDBC and MySQL. The system includes an Admin Panel for managing cars, customers, and rental records.


🛠️ Features
👤 User Panel
View Available Cars: Fetch and display cars from the database.
Rent a Car: Enter user details and rental duration, then book a car.
Return a Car: Mark a rented car as returned and update availability.
🛠️ Admin Panel
Add New Cars: Insert new car records into the database.
View All Rentals: Check rental history and active rentals.
Manage Customers: View registered customers and rental details.
🗄️ Tech Stack
Java (Core Java, OOPs)
JDBC (Java Database Connectivity)
MySQL (Database for storing car, user, and rental data)
📁 Project Structure
pgsql
Copy code
📂 Car-Rental-System/
├── 📄 Main.java        # Entry point (user interaction)
├── 📄 DatabaseConnection.java  # Manages MySQL connection
├── 📄 Car.java         # Car entity with properties (ID, brand, model, price)
├── 📄 User.java        # User entity for customer details
├── 📄 Rental.java      # Handles rental logic
├── 📄 Admin.java       # Admin functionalities (CRUD operations)
└── 📄 car_rental.sql   # SQL file for database setup
🚀 How to Run
1️⃣ Set Up MySQL Database

Import car_rental.sql into MySQL.
Update database credentials in DatabaseConnection.java.
2️⃣ Compile and Run

sh
Copy code
javac Main.java
java Main
