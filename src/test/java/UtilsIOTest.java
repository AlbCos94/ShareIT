import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 *
 * @author ioc
 */
public class UtilsIOTest {

    private final static String MESSAGE_ASK_ANYTHING = "Introdueixi alguna cosa";
    private final static String MESSAGE_ERROR = "Error en la entrada";
    private final static String MESSAGE_TEST_INPUT = "This message tests the input";
    private final static float FLOAT_TEST_INPUT = 6.3f;
    private final static int INT_TEST_INPUT = 6;    
    public static final int POINTS_NO_PLAYED = -1;
    public static final int INDEX_NAME = 0;
    public static final int INDEX_LAST_NAME = 1;
    public static final int INDEX_AGE = 2;
    public static final int THROWS_NUMBER = 10;
    public static final int MAX_POINTS = 10;
    public static final int PLAYERS_MATRIX_COLUMNS = 3;

    int[][] pointsMatrix;
    String[][] playersData;

    public UtilsIOTest() {
    } 

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
  
    }
    
    @AfterEach
    public void tearDown() {
    }

    private void provideInput(String data) {
        if (data != null) {
            ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }
    }   

    /**
     * Test of askForString method, of class AppBowling.
     */
    @Test
    public void testAskForStringOK() {
        System.out.println("testAskForStringOK");
        provideInput(MESSAGE_TEST_INPUT);
        String result = UtilsIO.askForStringLine(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR);
        assertEquals(MESSAGE_TEST_INPUT, result);
    }

    @Test
    public void testAskForStringNotOKIfEmpty() {
        System.out.println("testAskForStringNotOKIfEmpty");
        provideInput("\n" + MESSAGE_TEST_INPUT);
        String result = UtilsIO.askForStringLine(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR);
        assertEquals(MESSAGE_TEST_INPUT, result);
    }

    /**
     * Test of askForInteger method, of class AppBowling.
     */
    @Test
    public void testAskForIntegerOK() {
        System.out.println("testAskForIntegerOK");
        provideInput(String.valueOf(INT_TEST_INPUT)+"\n");
        int result = UtilsIO.askForInteger(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR);
        assertEquals(INT_TEST_INPUT, result);
    }
    
    @Test
    public void testAskForIntegerNotOKIfString() {
        System.out.println("testAskForIntegerNotOKIfString");
        provideInput(MESSAGE_TEST_INPUT + "\n" + String.valueOf(INT_TEST_INPUT)+"\n");
        int result = UtilsIO.askForInteger(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR);
        assertEquals(INT_TEST_INPUT, result);
    }
 
    @Test
    public void testAskForIntegerNotOKIfFloat() {
        System.out.println("testAskForIntegerNotOKIfFloat");
        provideInput(FLOAT_TEST_INPUT + "\n" + String.valueOf(INT_TEST_INPUT)+"\n");
        int result = UtilsIO.askForInteger(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR);
        assertEquals(INT_TEST_INPUT, result);
    }

    /* 
     * Test of showRounds method, of class AppBowling.
     */
    @Test
    public void testShowRounds_NullPlayersData() {
        String[][] playersData = null;
        int[][] pointsMatrix = new int[2][3];

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UtilsIO.showRounds(playersData, pointsMatrix);

        System.setOut(System.out); // Restore standard output

        assertEquals("", outContent.toString());
    }

    @Test
    public void testShowRounds_NullPointsMatrix() {
        String[][] playersData = new String[2][2];
        int[][] pointsMatrix = null;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UtilsIO.showRounds(playersData, pointsMatrix);

        System.setOut(System.out); // Restore standard output

        assertEquals("", outContent.toString());
    }

    @Test
    public void testShowRounds_DifferentArrayLengths() {
        String[][] playersData = new String[2][2];
        int[][] pointsMatrix = new int[3][3];

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UtilsIO.showRounds(playersData, pointsMatrix);

        System.setOut(System.out); // Restore standard output

        assertEquals("", outContent.toString());
    }

    @Test
    public void testShowRounds_EmptyData() {
        String[][] playersData = new String[0][0];
        int[][] pointsMatrix = new int[0][0];

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UtilsIO.showRounds(playersData, pointsMatrix);

        System.setOut(System.out); // Restore standard output

        // Since data is empty, no output is expected
        assertEquals("", outContent.toString());
    }
}
