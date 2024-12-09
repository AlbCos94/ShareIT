import java.util.Arrays;

// to get time info
import java.time.LocalDateTime;

// SQL pacakges
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilsDB {

    // Data base Connection
    String DB_URL = "jdbc:postgresql://localhost:5433/ShareIT";
    String USER = "postgres";  // Replace with your DB username
    String PASSWORD = "albert";  // Replace with your DB password
    

    public static Connection getConnectionWithDB() {
        
        Connection connection = null;
        
        try {
            // Connect to PostgreSQL database
            connection = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnectionWithDB(Connection connDB) {
    
        try {
            // Close connection with PostgreSQL database
            connDB.close();
            System.out.println("Connected to the PostgreSQL closed successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // function to Add a Record to a data base called "test_table" with attributes "name (TEXT) and numero (INT)"
    public static void addRecord(String name, int numero, Connection conn) {
        PreparedStatement stmt = null;

        try {
            // Step 1: Establish a connection to the database
            //conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); -> connection alreay open..

            if (conn.isClosed()){
                conn = getConnectionWithDB();
            }

            // Step 2: Prepare the SQL INSERT statement
            String sql = "INSERT INTO test1.test_table (name, numero) VALUES (?, ?)";

            // Step 3: Create a PreparedStatement to execute the query
            stmt = conn.prepareStatement(sql);

            // Step 4: Set parameters for the PreparedStatement
            stmt.setString(1, name);  // (number of the field: 1, value parameter: name)
            stmt.setInt(2, numero);   // (number of the field: 2, value parameter: numero)

            // Step 5: Execute the query
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Record with name "+name+ " and number "+ numero + " inserted successfully. Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            // Handle any SQL exceptions (e.g., connection issues, query issues)
            e.printStackTrace();
        } finally {
            // Step 6: Close the resources to avoid memory leaks
            try {
                if (stmt != null) {
                    stmt.close();
                }
                /* 
                // connection is closed using a specific method
                if (conn != null) {
                    conn.close();
                }*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // function to remove a Record of a data base called "test_table" with attributes "name (TEXT)"
    public static void removeRecord(String name, Connection conn) {
        PreparedStatement stmt = null;

        try {
            // Step 1: Establish a connection to the database
            //conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);


            if (conn.isClosed()){
                conn = getConnectionWithDB();
            }

            // Step 2: Prepare the SQL DELETE statement
            String sql = "DELETE FROM test1.test_table WHERE name = ?";

            // Step 3: Create a PreparedStatement to execute the query
            stmt = conn.prepareStatement(sql);

            // Step 4: Set parameters for the PreparedStatement
            stmt.setString(1, name);  // (number of the field: 1, value parameter: name)


            // Step 5: Execute the query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record with name " + name + " removed successfully.");
            } else {
                System.out.println("No record found with name " + name);
            }


        } catch (SQLException e) {
            // Handle any SQL exceptions (e.g., connection issues, query issues)
            e.printStackTrace();
        } finally {
            // Step 6: Close the resources to avoid memory leaks
            try {
                if (stmt != null) {
                    stmt.close();
                }
                /* 
                if (conn != null) {
                    conn.close();
                }
                // DB closed using a specific method
                */
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public static void addUser(User user, Connection conn) throws SQLException {
        // Establish a connection to the PostgreSQL database
        try (conn) {
            
            // SQL insert statement to add a new user to the database
            String sql = "INSERT INTO users (username, email, password_hash, first_name, last_name, " +
                    "date_of_birth, phone_number, created_at, updated_at, " +
                    "is_active, is_verified) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement to insert the user into the database
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                
                // Set the values for the PreparedStatement
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPasswordHash());
                statement.setString(4, user.getFirstName());
                statement.setString(5, user.getLastName());
                statement.setDate(6, user.getDateOfBirth());
                statement.setString(7, user.getPhoneNumber());
                statement.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
                statement.setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
                statement.setBoolean(10, user.isActive());
                statement.setBoolean(11, user.isVerified());

                // Execute the statement to insert the user
                int rowsAffected = statement.executeUpdate();
                
                // Optional: Check if the insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("User added successfully.");
                } else {
                    System.out.println("Failed to add user.");
                }
            }
        }
    
    }

    

    

}