import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class UserManager {

    /*
     Static methods to manage data regarding with the users of ShareIT App
    */

    // Ask for User data to create User

    public static boolean askForNewUser(Connection conn) {	
        // returns true if it was correctly created

        boolean userIsOk = false;
        boolean emailIsOk = false;
        boolean userExists = false;
        boolean emailExists = false;
        String userName; 
        String userEmail; 

        // Asking for the user name
        do{
            userName = UtilsIO.askForStringLine("Introduce the user name you want to use", Constants.ERROR_PLAYERNAME);
            
            try{
                userExists = UtilsDB.userExists(userName, conn);

                if (!userExists){
                    userIsOk = true;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


        } while ( userIsOk == false );

        // Asking for the email
        do{
            userEmail = UtilsIO.askForStringLine("Introduce the email you want to use", Constants.ERROR_PLAYERNAME);
            
            try{
                emailIsOk = UtilsDB.emailExists(userName, conn);

                if (!emailExists){
                    emailIsOk = true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } while ( emailIsOk == false );

        // Creating the user ( for testing purposes just two datas are asked)
        java.lang.String fecha_nac = new java.lang.String("1994-08-14");

        User newUser = new User(
            userName, userEmail, "hashed_password", "dgf", "dg",
                fecha_nac, "649853472", true, true
        );

        try {
            UtilsDB.addUserV2(newUser, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean askForDeleteUser(Connection conn) {	
        // returns true if it was correctly created

        boolean userIsOk = false;
        boolean userExists = false;
        String userName; 

        // Asking for the user name
        do{
            userName = UtilsIO.askForStringLine("Introduce the user name you want to delete", Constants.ERROR_PLAYERNAME);
            
            try{
                userExists = UtilsDB.userExists(userName, conn);

                if (userExists){
                    userIsOk = true;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        } while ( userIsOk == false );


        try {
            UtilsDB.deleteUserV2(userName, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void showUserNames(Connection conn) {	

        System.out.println("Usernames in ShareIT: \n");
        
        try {
            UtilsDB.showUsernames(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}