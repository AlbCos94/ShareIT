/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;




/**
 *
 * @author IOC & Albert Costa Ruiz as student
 */
public class ShareIT {

    // Data base Connection
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/ShareIT";
    private static final String USER = "postgres";  // Replace with your DB username
    private static final String PASSWORD = "albert";  // Replace with your DB password

     public static void main(String[] args) {
        ShareIT bowlingApp = new ShareIT();
        bowlingApp.start();
    }

    public void start() {           

        System.out.println("this is a test"); 


        removeRecord("costa", 123); // elimina todos que se llamen costa

        /*
        
        addRecord("hola", 123);
        addRecord("adios", 987);
        
        addRecord("costa", 987);
        removeRecord("hola", 987);*/
        /*
        // Establish connection and perform operations
        try {
            // Connect to PostgreSQL database
             
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
            */
            // Create a statement and execute a query
            /* 
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mytable");  // Replace with your table

            // Process the result set
            while (rs.next()) {
                int id = rs.getInt("id");  // Replace with your column name
                String name = rs.getString("name");  // Replace with your column name
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Close resources
            rs.close();
            stmt.close();
            
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        
        // old code
        /* 
        BowlingData bowlingData = new BowlingData(); // inicializaction of a new object bowlingData 
        askingForPlayersAndDataInitialization(bowlingData);
        if (bowlingData.playersData == null){
            return; // end of the program
        }
        optionManager(bowlingData); 
        */
    }

    // function to Add a Record to a data base called "test_table" with attributes "name (TEXT) and numero (INT)"
    public static void addRecord(String name, int numero) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Step 1: Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

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


    // function to Add a Record to a data base called "test_table" with attributes "name (TEXT) and numero (INT)"
    public static void removeRecord(String name, int numero) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Step 1: Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }






    // -- Auxiliar methods --

    // Method to ask for the number of players and initialize the data structure.  
    public void askingForPlayersAndDataInitialization(BowlingData bowlingData) {

        String namePlayer = "";
        String surnamePlayer = "";
        int agePlayer = 0;
        int numPlayers = 0;

        numPlayers = UtilsIO.askForInteger(Constants.ASK_FOR_NUM_PLAYERS, Constants.ERROR_ENTER);
        
        if (numPlayers <= 0) {
            UtilsIO.showError(Constants.ERROR_NUM_PLAYERS);
            return; // exit program
        }

        // Matrixs are initialized
        bowlingData.initializePlayers(numPlayers);
        bowlingData.initializePoints(numPlayers);      

        for (int i= 0; i<numPlayers; i++){
            String playerNumber = String.valueOf(i+1)+"/"+String.valueOf(numPlayers);
            String questionName  = playerNumber+" - "+ Constants.ASK_FOR_PLAYERNAME;
            String questionSurname  = playerNumber+" - "+ Constants.ASK_FOR_PLAYERSURNAME;
            String questionAge  = playerNumber+" - "+ Constants.ASK_FOR_PLAYERAGE;
            namePlayer = UtilsIO.askForString(questionName, Constants.ERROR_EMPTY_STRING);
            surnamePlayer = UtilsIO.askForString(questionSurname, Constants.ERROR_EMPTY_STRING);
            agePlayer = UtilsIO.askForAge(questionAge, Constants.ERROR_PLAYERAGE);

            UtilsBowling.insertPlayerNames(bowlingData.playersData, i, namePlayer, surnamePlayer, agePlayer);
        }
    }

    // Method to ask for the points of all the players for a given round 
    public void askingForRoundPoints(BowlingData bowlingData) {

        int roundNumber = 0;
        int pointsToInsert = 0;
        boolean correctPoints = false;
        boolean correctRound = false;
        String playerFullName = "";

        do{
            roundNumber = UtilsIO.askForInteger(Constants.ROUND_TO_POINT, Constants.ERROR_ENTER);

            if ( (roundNumber<1) || (roundNumber > Constants.ROUNDS_NUMBER) ){
                UtilsIO.showError(Constants.ROUND_TO_POINT_ERROR);
                correctRound = false;
            } else {
                correctRound = true;
            }
        } while (!correctRound);
  
        // Loop among all the players of the round
        for (int i=0; i<bowlingData.playersData.length; i++){
            playerFullName = bowlingData.playersData[i][0] + " " + bowlingData.playersData[i][1];

            do{
                pointsToInsert = UtilsIO.askForInteger(Constants.QUESTION_ENTER_POINTS + playerFullName, Constants.ERROR_ENTER); // it gives us an int but it is not sure if between 1 and 10

                if ( (pointsToInsert >= 0) && (pointsToInsert <= Constants.MAX_POINTS) ) {
                    correctPoints = true;
                } else {
                    UtilsIO.showError(Constants.ERROR_ENTER_RANGE + Constants.MAX_POINTS);
                    correctPoints = false;
                }
            } while (!correctPoints);

            UtilsBowling.setRoundPoints(bowlingData.pointsMatrix, i, roundNumber, pointsToInsert);
        }
    }
    
    // Method that manages the different options of the application
    public void optionManager(BowlingData bowlingData) {

        Scanner reader = new Scanner(System.in); 
        boolean corectData = false;
        boolean finish = false;
        int inputInt = 0;

        do{
            UtilsIO.showMenu(Constants.MENU_TEXT);

            corectData = reader.hasNextInt();

            if(corectData){
                
                inputInt = reader.nextInt();

                if (inputInt == Constants.OPTION_POINT_ROUND){
                    askingForRoundPoints(bowlingData);
                    UtilsIO.showRounds(bowlingData.playersData,bowlingData.pointsMatrix);

                } else if (inputInt == Constants.OPTION_SHOW_RANKING){  
                    showGeneralRanking(bowlingData);                                
                
                } else if (inputInt == Constants.OPTION_CHANGE_POINTS){   
                    changePlayersPoints(bowlingData);
                    UtilsIO.showRounds(bowlingData.playersData,bowlingData.pointsMatrix);                
                
                } else if (inputInt == Constants.OPTION_QUIT){
                    finish = true;
                
                } else{
                    UtilsIO.showError(Constants.ERROR_OPTION);
                }

            } else{
                
                UtilsIO.showError(Constants.ERROR_OPTION);
                reader.next();
            }
        } while( !finish );
    }

    // Method to show the complete puntuations
    public void showGeneralRanking(BowlingData bowlingData){
        // Compute of the total points of each user
        int [] totalPointsArray = UtilsBowling.calculateTotalPointsArray(bowlingData.pointsMatrix);
        
        // Get the array of indexes corresponding to the ordered array of total points
        int [] arrayOfIndexes = UtilsBowling.getOrderedIndexArrayWithTotalPoints(totalPointsArray);

        // Display the classification with the total points of each player
        UtilsIO.showOrderedPointsList(bowlingData.playersData, totalPointsArray, arrayOfIndexes);
    }
    
    // Method to change some players points for a specific round
    public void changePlayersPoints(BowlingData bowlingData){
        
        // Ask for the name of the player
        String playerFullName = UtilsIO.askForString(Constants.ASK_FOR_COMPLETE_NAME, Constants.ERROR_EMPTY_STRING);
        
        // Get the index of the player
        int indexPlayer = UtilsBowling.lookForPlayer(bowlingData.playersData, playerFullName);
        
        if (indexPlayer == Constants.ERROR_INT_RESULT) {
            UtilsIO.showError(Constants.PLAYER_NOT_FOUND);
            return;
        }

        // We ask for the round to point, the new quantity of points and the matrix of points is changed
        askingForRoundPointsForOnePlayer(bowlingData.pointsMatrix, playerFullName, indexPlayer);
    } 

    // Method to ask for the points of a player for a specific round
    public void askingForRoundPointsForOnePlayer(int[][] pointsMatrix, String playerFullName, int indexPlayer) {

        int roundNumber = 0;
        int pointsToInsert = 0;
        boolean correctPoints = false;
        boolean correctRound = false;

        // Asking the round to point
        do{
            roundNumber = UtilsIO.askForInteger(Constants.ROUND_TO_POINT, Constants.ERROR_ENTER);

            if ( (roundNumber<1) || (roundNumber > Constants.ROUNDS_NUMBER) ){
                UtilsIO.showError(Constants.ROUND_TO_POINT_ERROR);
                correctRound = false;
            } else {
                correctRound = true;
            }
        } while (!correctRound);
    
        do{
            pointsToInsert = UtilsIO.askForInteger(Constants.QUESTION_ENTER_POINTS + playerFullName, Constants.ERROR_ENTER); // it gives us an int but it is not sure if between 1 and 10

            if ( (pointsToInsert >= 0) && (pointsToInsert <= Constants.MAX_POINTS) ) {
                correctPoints = true;
            } else {
                UtilsIO.showError(Constants.ERROR_ENTER_RANGE + Constants.MAX_POINTS);
                correctPoints = false;
            }

        } while (!correctPoints);

        UtilsBowling.setRoundPoints(pointsMatrix, indexPlayer, roundNumber, pointsToInsert);
    }

	
}