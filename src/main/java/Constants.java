public class Constants {
    // All the constants needed for Bowling Program 
    // They will be used in other classes
    

    // Constant declarations for ShareIT
    // Data base Connection
    public static final String DB_URL = "jdbc:postgresql://localhost:5433/ShareIT";
    public static final String USER = "postgres";  // Replace with your DB username
    public static final String PASSWORD = "albert";  // Replace with your DB password


    // Constant declarations
    public static final int PLAYER_DATA_ELEMENTS = 3; // number of data elemetns that will be add for each player (name, surname and age)
    public static final String EMPTY_DATA_PLAYER = "";
    public static final int POS_NAME = 0;
    public static final int POS_LASTNAME = 1;
    public static final int POS_AGE = 2;
    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 99;
    public static final int ROUNDS_NUMBER = 10; // number of rounds that will be played
    public static final int MAX_POINTS = 10; // max number of points a player can get by round
    public static final int NULL_POINTS = -1; // max number of points a player can get by round
    public static final int ERROR_INT_RESULT = -1; 
    public static final int OPTION_POINT_ROUND = 1; // option given to introduce the points of a round
    public static final int OPTION_SHOW_RANKING = 2; // option given to display the points table
    public static final int OPTION_CHANGE_POINTS = 9; // option given to correct a players points

    public static final int OPTION_QUIT = 0; // option given to finish the program
    public static final String SPLIT_LINE = "-"; // line displayed in the menu
    public static final int NUMBER_DOTS_MENU_LINE = 30;
    public static final int NUMBER_DOTS_SUMTABLE_LINE = 45;
    public static final int NUMBER_DOTS_TABLE_LINE = 120;

    public static final String MENU_FULLNAME = "FULL NAME"; // line displayed in the menu
    public static final String MENU_AGE = "AGE"; // line displayed in the men
    public static final String MENU_POINTS = "POINTS"; // line displayed in the men
    public static final String MENU_TITLE = "GESTIO IOC BOWLING"; // title displayed in the menu
    public static final String ERROR_TITLE = "ERROR"; // error title displayed in the menu
    public static final String MENU_TEXT = "1) Puntuar ronda. \n2) Mostrar classificació. \n9) Corregir puntuació. \n0) Sortir."; // Options menu
    public static final String ERROR_OPTION = "No s'ha introduït un enter vàlid com a opció."; // Options menu
    public static final String ROUND_TO_POINT = "Quina ronda vol puntuar?"; // Question to ask to point a round
    public static final String ROUND_TO_POINT_ERROR = "La ronda introduïda no existeix. Introdueixi un valor entre 1 i " + ROUNDS_NUMBER; // Error round to point
    public static final String QUESTION_ENTER_POINTS = "Introdueixi els punts per "; 
    public static final String ERROR_ENTER = "No s'ha introduït un número enter";
    public static final String ERROR_ENTER_RANGE = "Els punts han de ser entre 0 i ";
    public static final String ASK_FOR_PLAYERNAME = "Introdueixi el nom del jugador";
    public static final String ERROR_PLAYERNAME = "El nom introduït es incorrecte";  
    public static final String ASK_FOR_PLAYERSURNAME = "Introdueixi el cognom del jugador";
    public static final String ERROR_PLAYERSURNAME = "El cognom introduït es incorrecte";   
    public static final String ASK_FOR_PLAYERAGE = "Introdueixi l'edat del jugador";
    public static final String ERROR_PLAYERAGE = "L'edat introduïda es incorrecte"; 
    public static final String ERROR_PLAYERAGE_RANGE = "L'edat introduïda ha de ser un valor entre 0 i 99 anys";   
    public static final String ASK_FOR_NUM_PLAYERS = "\nQuants jugadors hi haurà?";
    public static final String ERROR_NUM_PLAYERS = "No s'ha introdït un nombre correcte de jugadors";
    public static final String QUESTION_OPTIONS = "Introdueixi un valor enter per l'opció";
    public static final String ASK_FOR_COMPLETE_NAME = "Introdueixi el nom complet del jugador";
    public static final String STRING_ERROR = "Valor incorrecte. Introdueixi un nom";
    public static final String PLAYER_NOT_FOUND = "El jugador no s'ha trobat";
    public static final String ERROR_EMPTY_STRING = "S'ha introduït un text buit";

}