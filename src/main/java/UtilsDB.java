import java.util.Arrays;

import org.postgresql.util.PSQLException;

// to get time info
import java.time.LocalDateTime;

// SQL pacakges
import java.sql.*;

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
            System.out.println("Connection to the PostgreSQL closed successfully.");

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
        
        // Not write "try(conn)"" since that will close the connection!!

            
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
            statement.setDate(6, java.sql.Date.valueOf(user.getDateOfBirth()));
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

    public static void addUserV2(User user, Connection conn) throws SQLException {
        

        // Not write "try(conn)"" since that will close the connection after the "try and catch" structure!!
 
        // Define SQL to call the adduser function with an appuser record of type "AppUser"
        // Not possible to pass an object directly to SQL!! it needs to be passed as a record with the type. It needs to be parsed!
        String sql = "SELECT add_user(ROW(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)::appuser)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the values for the appuser composite type
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setDate(6, java.sql.Date.valueOf(user.getDateOfBirth()));
            stmt.setString(7, user.getPhoneNumber());
            stmt.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
            stmt.setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
            stmt.setTimestamp(10, Timestamp.valueOf(user.getLastLogin()));
            stmt.setBoolean(11, user.isActive());
            stmt.setBoolean(12, user.isVerified());
            
            // Execute the query
            stmt.execute();
            System.out.printf("User with username %s has been created. \n", user.getUsername());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String username, Connection conn) throws SQLException {
            
        // SQL insert statement to remove a user by its username
        String sql = "DELETE FROM users WHERE username = ?";
        
        // Create a PreparedStatement to insert the user into the database
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            
            // Set the values for the PreparedStatement
            statement.setString(1, username);

            // Execute the statement to insert the user
            int rowsAffected = statement.executeUpdate();
            
            // Optional: Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user.");
            }
        }
    
    }
    
    public static void deleteUserV2(String username, Connection conn) throws SQLException {
        

            
        // Define SQL to call the adduser function with an appuser record of type "AppUser"
        // Not possible to pass an object directly to SQL!! it needs to be passed as a record with the type. It needs to be parsed!
        String sql = "SELECT delete_user(?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the values for the appuser composite type
            stmt.setString(1, username);                
            // Execute the query
            stmt.execute();
            System.out.printf("User with username %s has been removed. \n", username);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public static void showUsernames(Connection conn) throws SQLException {
        

        // Define SQL to call the adduser function with an appuser record of type "AppUser"
        // Not possible to pass an object directly to SQL!! it needs to be passed as a record with the type. It needs to be parsed!
        String sql = "SELECT * FROM show_usernames()";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            // Loop through the result set and print each username
            while (rs.next()) {
                String username = rs.getString(1); // The first (and only) column is the username
                System.out.println(username);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public static boolean userExists(String username, Connection conn) throws SQLException {
        
        boolean userExists = false;

        String sql = "SELECT exists_user(?)"; // function defined in Postgres

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the values for the appuser composite type
            stmt.setString(1, username);                
            // Execute the query
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                // Retrieve the boolean value from the result set
                userExists =  resultSet.getBoolean(1);  // '1' refers to the first column in the result
                return userExists;
            }           
    
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userExists;
    }

    public static boolean emailExists(String email, Connection conn) throws SQLException {
        
        boolean emailExists = false;

        String sql = "SELECT exists_email(?)"; // function defined in Postgres

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the values for the appuser composite type
            stmt.setString(1, email);                
            // Execute the query
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                // Retrieve the boolean value from the result set
                emailExists =  resultSet.getBoolean(1);  // '1' refers to the first column in the result
                return emailExists;
            }           
    
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailExists;
    }


}
    

