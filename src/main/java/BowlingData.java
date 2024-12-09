public class BowlingData {

    // Data structure to model the Bowling program

    String[][] playersData = null; // matrix that will contain data of the players // defer / lazy initialization - its creation is deferred until it is first used.
    int[][] pointsMatrix = null; // matrix that will contain points of the players

    // Initialize matrix of points
    public int[][] initializePoints(int playersNumber) {

        if (playersNumber<=0) {
            System.out.println(Constants.ERROR_NUM_PLAYERS);
            return null; 
        }

        pointsMatrix = new int[playersNumber][Constants.ROUNDS_NUMBER];

        // pointsMatrix it is filled with -1's
        for (int i= 0; i<playersNumber; i++){ 
            for (int j= 0; j<Constants.ROUNDS_NUMBER; j++){
                pointsMatrix[i][j] = Constants.NULL_POINTS;
            }              
        }

        return pointsMatrix;
    }

    // Initialize matrix of players
    public String[][] initializePlayers(int playersNumber) {

        if (playersNumber<=0) {
            System.out.println(Constants.ERROR_NUM_PLAYERS);
            return null; 
        }

        playersData = new String[playersNumber][Constants.PLAYER_DATA_ELEMENTS]; 
        
        // playersData it is filled with empty strings
        for (int i= 0; i<playersNumber; i++){ 
            for (int j= 0; j<Constants.PLAYER_DATA_ELEMENTS; j++){
                playersData[i][j] = Constants.EMPTY_DATA_PLAYER;
            }              
        }
        return playersData;
    }

}