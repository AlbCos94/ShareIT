import java.util.Scanner;

public class UtilsIO {
	// Methods to interact with the user ( intput and output streams of data )

	//Method to show the menu text
    public static void showMenu(String menuText) {	
        
        if ( (menuText == null) || (menuText.isEmpty()) ){
            return;
        }
        String stringResult = Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_MENU_LINE)+"\n"+Constants.MENU_TITLE+"\n"+Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_MENU_LINE)+"\n"+menuText;
        System.out.println(stringResult); // printed in a new line
        System.out.println("\n");
        System.out.println(Constants.QUESTION_OPTIONS); 
    }

	//Show a message with an error format
	public static void showError(String textError) {

        if ( (textError == null) || (textError.isEmpty()) ){
            return;
        }
		
        String stringResult = Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_MENU_LINE)+"\n"+Constants.ERROR_TITLE+"\n"+Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_MENU_LINE)+"\n"+textError;
        System.out.println(stringResult); 
    }

 	//Ask to the user for a string
	public static String askForString(String message, String errorMessage){
        
		Scanner reader = new Scanner(System.in); 
        boolean end = false;
        String inputString = "";

        do{
            System.out.println(message); // User message with a request
            inputString = reader.nextLine(); // Get the next string line introduced by user (everything he wrote before enter -> (\n) )

            if (!inputString.isEmpty()){
                end = true;
            } else{
                showError(errorMessage);
            }
        } while ( end == false );

        return inputString;
    }

	//Ask to the user for a integer
    public static int askForInteger(String message, String errorMessage) {
        
        Scanner reader = new Scanner(System.in); 
        boolean corectData = false;
        int inputInt = 0;

        do{
            System.out.println(message); 

            corectData = reader.hasNextInt();

            if(reader.hasNextInt()){
                corectData = true;
                inputInt = reader.nextInt();
            } else{
                
                showError(errorMessage);
                reader.next();
            }
        } while( !corectData );

        return inputInt;
    }

	//Ask to the user for an age (it has to be between 0 and 99)
	public static int askForAge(String message, String errorMessage) {
        
        Scanner reader = new Scanner(System.in); 
        boolean corectData = false;
        int inputInt = 0;

        do{
            System.out.println(message); 

            //corectData = reader.hasNextInt();

            if(reader.hasNextInt()){
                //corectData = true;
                inputInt = reader.nextInt();

                if (inputInt < Constants.MIN_AGE || inputInt > Constants.MAX_AGE){
                    showError(Constants.ERROR_PLAYERAGE_RANGE);
                    corectData = false;
                } else{
                    corectData = true;
                }

            } else{
                
                showError(errorMessage);
                reader.next();
            }
        } while( !corectData );

        return inputInt;
    }

	//Ask to the user for a float
    public static float askForFloat(String message, String errorMessage) {
        
        Scanner reader = new Scanner(System.in); 
        boolean corectData = false;
        float inputFloat = 0;

        do{
            System.out.println(message); 

            corectData = reader.hasNextFloat();

            if(reader.hasNextFloat()){
                corectData = true;
                inputFloat = reader.nextFloat();
            } else{
                
                showError(errorMessage);
                reader.next();
            }
        } while( !corectData );

        return inputFloat;
    }

	//Method to print the header of a points table
    public static void printHeaderTablePoints(int roundsNumber) {

        if ( roundsNumber == 0)  {
            return;
        }

        // print "full name" plus "age"
        System.out.printf(Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_TABLE_LINE) +"\n");
        System.out.printf("%-15s %10s", Constants.MENU_FULLNAME, Constants.MENU_AGE);
        
        for (int i= 0; i<roundsNumber; i++){
            System.out.printf("%5s", "R"+String.valueOf(i+1));
        }
        System.out.printf("\n");
        System.out.printf(Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_TABLE_LINE));
     }

	//Method to print the points of a single player
    public static void print1PlayersPoints(String[] onePlayersData, int[] pointsRow) {

        if ( (onePlayersData == null) || (pointsRow == null) ) {
            return;
        }

        if ( onePlayersData.length == 0 ) {
            return;
        }

        if ( pointsRow.length == 0 ) {
            return;
        }

        // Print "full name" plus "age"
        String fullName = onePlayersData[Constants.POS_NAME] + " " + onePlayersData[Constants.POS_LASTNAME];
        System.out.printf("%-15s %10s", fullName, onePlayersData[Constants.POS_AGE]);

        for (int i= 0; i<pointsRow.length; i++){ 
            if (pointsRow[i] == -1) {
                System.out.printf("%5s", "-");
            } else{
                System.out.printf("%5s", String.valueOf(pointsRow[i]));
            }
        }  
    }

	//Method to show all the points of the rounds
    public static void showRounds(String[][] playersData, int[][] pointsMatrix) {

        if ( (playersData == null) || (pointsMatrix == null) ) {
            return;
        }

        // Number of rows between matrixs does not match 
        if ( playersData.length != pointsMatrix.length ) {
            return;
        }

        if ( playersData.length == 0 ) {
            return;
        }

        //Print header of the table containing all the rounds
        printHeaderTablePoints(Constants.ROUNDS_NUMBER);
        System.out.print("\n");

        for (int i= 0; i<playersData.length; i++){ 
            print1PlayersPoints(playersData[i], pointsMatrix[i]);
            System.out.print("\n"); // line break is added at the end
        }
    }

    // Show table with the total points
    public static void showOrderedPointsList(String[][] playersData, int[] totalPoints, int[] indexArray){

        if ( (playersData == null) || (totalPoints == null) || (indexArray == null) ) {
            return;
        }

        // Number of rows between matrixs and arrays do not match 
        if ( (playersData.length != totalPoints.length) || (totalPoints.length != indexArray.length) ) {
            return;
        }

        // Matrixs of arrays have lenght equal to 0 
        if ( (playersData.length == 0) || (totalPoints.length == 0) || (indexArray.length == 0)) {
            return;
        }

        printHeaderTableSumPoints();
        System.out.print("\n");

        int j = 0; // current index in the index array
        
        // Loop among the index array where puntuations ared oredered
        for (int i= 0; i<indexArray.length; i++){ 
            j = indexArray[i];
            print1PlayersSumPoints(playersData[j], totalPoints[j]);
            System.out.print("\n"); // line break is added at the end
        }
    }

    //Method to print the header of a points table
    public static void printHeaderTableSumPoints() {

        // print "full name" plus "age" plus points
        System.out.printf(Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_SUMTABLE_LINE) +"\n");
        System.out.printf("%-15s %10s %15s", Constants.MENU_FULLNAME, Constants.MENU_AGE, Constants.MENU_POINTS);
       
        System.out.printf("\n");
        System.out.printf(Constants.SPLIT_LINE.repeat(Constants.NUMBER_DOTS_SUMTABLE_LINE));
    }

    //Method to print the sum of points of a single player
    public static void print1PlayersSumPoints(String[] onePlayersData, int totalPoints) {

        if ( onePlayersData == null ) {
            return;
        }

        if ( onePlayersData.length == 0 ) {
            return;
        }

        // Print "full name" plus "age" plus "total points"
        String fullName = onePlayersData[Constants.POS_NAME] + " " + onePlayersData[Constants.POS_LASTNAME];
        System.out.printf("%-15s %10s %15s", fullName, onePlayersData[Constants.POS_AGE], totalPoints);
    }

}