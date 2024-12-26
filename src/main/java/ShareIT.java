/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate; // for birthday date
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author IOC & Albert Costa Ruiz as student
 */
public class ShareIT {

    // Data base Connection
    Connection connDB;


     public static void main(String[] args) {
        ShareIT bowlingApp = new ShareIT();
        bowlingApp.start();
    }

    public void start() {           

        System.out.println("this is a test"); 

        connDB = UtilsDB.getConnectionWithDB();

        java.lang.String fecha_nac = new java.lang.String("1994-08-14");

        User newUser = new User(
                "albertcr22", "albertcr22@gdample.com", "hashed_password", "dgf", "dg",
                fecha_nac, "649853472", true, true
        );

        optionManager();

        // We create a user in the data base using a function defined in the data base
        /*
        try {
            UtilsDB.addUserV2(newUser, connDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         */
        // We remove a user
        /* 
        try {
            UtilsDB.deleteUserV2("albertcr", connDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        // We remove a user
        /* 
        try {
            UtilsDB.showUsernames(connDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */


        UtilsDB.closeConnectionWithDB(connDB);
        //removeRecord("costa", 123); // elimina todos que se llamen costa
        
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
            namePlayer = UtilsIO.askForStringLine(questionName, Constants.ERROR_EMPTY_STRING);
            surnamePlayer = UtilsIO.askForStringLine(questionSurname, Constants.ERROR_EMPTY_STRING);
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

    // Method that manages the different options of the SHARE IT application - MAIN MENU
    public void optionManager() {

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
                    optionManagerUserMenu();
                    //askingForRoundPoints(bowlingData);
                    //UtilsIO.showRounds(bowlingData.playersData,bowlingData.pointsMatrix);

                } else if (inputInt == Constants.OPTION_SHOW_RANKING){  
                    finish = true;
                    //showGeneralRanking(bowlingData);                                
                
                } else if (inputInt == Constants.OPTION_CHANGE_POINTS){   
                    finish = true;
                    //changePlayersPoints(bowlingData);
                    //UtilsIO.showRounds(bowlingData.playersData,bowlingData.pointsMatrix);                
                
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


    // Method that manages the different options of the SHARE IT application
    public void optionManagerUserMenu() {

        Scanner reader = new Scanner(System.in); 
        boolean corectData = false;
        boolean finish = false;
        int inputInt = 0;

        do{
            UtilsIO.showMenu(Constants.MENU_USER_TEXT);

            corectData = reader.hasNextInt();

            if(corectData){
                
                inputInt = reader.nextInt();

                if (inputInt == Constants.OPTION_ADD_USER){
                    UserManager.askForNewUser(connDB);


                } else if (inputInt == Constants.OPTION_REMOVE_USER){  
                    UserManager.askForDeleteUser(connDB);
                           
                
                } else if (inputInt == Constants.OPTION_SHOW_USERS){  
                    UserManager.showUserNames(connDB);               
                
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

        optionManager(); // go back to option Manager (Main Menu)
    }





    // Method that manages the different options of the application
    public void optionManagerBowling(BowlingData bowlingData) {

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
        String playerFullName = UtilsIO.askForStringLine(Constants.ASK_FOR_COMPLETE_NAME, Constants.ERROR_EMPTY_STRING);
        
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