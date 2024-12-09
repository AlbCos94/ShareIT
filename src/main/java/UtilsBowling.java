import java.util.Arrays;

public class UtilsBowling {

    // Set the players names 
    public static void insertPlayerNames(String[][] playersData, int rowNumber, String name, String lastName, int age) {
        
        // if the array is null, we return
        if (playersData == null) {
            return;
        }
        // if the row number is not valid, we return
        if ( (rowNumber < 0) || (rowNumber >= playersData.length) ) {
            return;
        }
        // if the name is null or empty, we return
        if ( (name == null) || (name.isEmpty()) ){
            return;
        }
        // if the last name is null or empty, we return
        if ( (lastName == null) || (lastName.isEmpty()) ){
            return;
        }
        // if the age is less than 0, we return
        if (age < 0){
            return;
        }        

        // insert the data in the array
        playersData[rowNumber][Constants.POS_NAME] = name;
        playersData[rowNumber][Constants.POS_LASTNAME] = lastName;
        playersData[rowNumber][Constants.POS_AGE] = String.valueOf(age);
    }

    // Set the points of a Round
    public static void setRoundPoints(int[][] pointsMatrix, int rowIndex, int round, int points) {
        // if the array is null, we return
        if (pointsMatrix == null) {
            return;
        }
        
        // playersData
        // if the row index is not valid, we return
        if ( (rowIndex < 0) || (rowIndex >= pointsMatrix.length) ) {
            return;
        }
        // if the round is not valid, we return
        if ( (round <= 0) || (round >= Constants.ROUNDS_NUMBER)){
            return;
        }   
        // if the points are not valid, we return
        if ( (points < 0) || (points > Constants.MAX_POINTS)){
            return;
        }   
        // set the points in the array
        pointsMatrix[rowIndex][round-1] = points;
    }

    // Returns the index of a given player in the playersmatrix. If it does not exists, returns -1
    public static int lookForPlayer(String[][] playersMatrix, String playerFullName){
        
        // if the array is null, we return
        if (playersMatrix == null) {
            return Constants.ERROR_INT_RESULT;
        }

        // if the playerFullName is null or empty, we return
        if ( (playerFullName == null) || (playerFullName.isEmpty()) ){
            return Constants.ERROR_INT_RESULT;
        }

        String[] splittedPlayerFullName = playerFullName.split(" ");

        // Check that the name array has 2 elements
        if ( splittedPlayerFullName.length != 2 ){
            return Constants.ERROR_INT_RESULT;
        }
        
        String namePlayer = splittedPlayerFullName[Constants.POS_NAME];
        String lastnamePlayer = splittedPlayerFullName[Constants.POS_LASTNAME];
        String name_i = "";
        String lastname_i = "";
        // loop among playersMatrix 
        for (int i= 0; i<playersMatrix.length; i++){ 
            name_i =  playersMatrix[i][Constants.POS_NAME];
            lastname_i =  playersMatrix[i][Constants.POS_LASTNAME];
            if ( name_i.equals(namePlayer) && lastname_i.equals(lastnamePlayer) ){
                return i;
            }
        }

        return Constants.ERROR_INT_RESULT;
    }

    // Given a matrix of points, it computes the total points for each player
    public static int[] calculateTotalPointsArray(int[][] pointsMatrix){
        
        // if the matrix is null, we return null
        if (pointsMatrix == null){
            return null;
        }

        // Array that will contain the total points of each player
        int [] totalPointsArray = new int[pointsMatrix.length];
        int totalPointsPlayer = 0;
        int currentPoints = 0;

        // Loop among playersMatrix (players)
        for (int i= 0; i<pointsMatrix.length; i++){ 
            
            // Loop among all the points of a player
            for (int j= 0; j<pointsMatrix[i].length; j++){ 
                currentPoints = pointsMatrix[i][j];

                if ( currentPoints >=0 ){
                    totalPointsPlayer+=currentPoints;
                }
            }
            totalPointsArray[i] = totalPointsPlayer;
            totalPointsPlayer = 0; // reset the value for the next player

        }
        return totalPointsArray;
    }

    // Given an array of points, the corresponding index array ordering it in a descending way is returned
    public static int[] getOrderedIndexArrayWithTotalPoints(int[] totalPointsArray){

        // If the array is null, we return null
        if (totalPointsArray == null){
            return null;
        }

        // Array containing the indexes
        int[] indexArray = new int[totalPointsArray.length];

        // Inicialization of the array with the indexes
        for (int i = 0; i < indexArray.length; i++){
            indexArray[i] = i;
        }

        // Copy of the array of points
        int [] copyTotalPointsArray = Arrays.copyOf(totalPointsArray, totalPointsArray.length);

        // BubbleSort algorithm
        for ( int i = 0; i < copyTotalPointsArray.length-1; i++){  

            for ( int j = i+1; j < copyTotalPointsArray.length; j++){

                if (copyTotalPointsArray[i] < copyTotalPointsArray[j]){
                    int changeValue = copyTotalPointsArray[i];
                    int changeIndex = indexArray[i];

                    copyTotalPointsArray[i] = copyTotalPointsArray[j];
                    indexArray[i] = indexArray[j];

                    copyTotalPointsArray[j] = changeValue;
                    indexArray[j] = changeIndex;
                } 
            }
        }
        return indexArray;
    }

}