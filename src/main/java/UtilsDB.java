import java.util.Arrays;

// SQL pacakges
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
            System.out.println("Record inserted successfully. Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            // Handle any SQL exceptions (e.g., connection issues, query issues)
            e.printStackTrace();
        } finally {
            // Step 6: Close the resources to avoid memory leaks
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    

    

    

    

}