import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ioc
 */
public class BowlingDataTest {
   
    private final static int TEST_PLAYERS_NUMBER = 2;
    public static final int POINTS_NO_PLAYED = -1;
    public static final int INDEX_NAME = 0;
    public static final int INDEX_LAST_NAME = 1;
    public static final int INDEX_AGE = 2;
    public static final int THROWS_NUMBER = 10;
    public static final int MAX_POINTS = 10;
    public static final int PLAYERS_MATRIX_COLUMNS = 3;

    BowlingData bowlingData;

    public BowlingDataTest() {
    } 

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
      bowlingData = new BowlingData();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of initializePoints method, of class AppBowling.
     */
    @Test
    public void testInitializePointsOK() {
        System.out.println("initializePoints");
        bowlingData.initializePoints(TEST_PLAYERS_NUMBER);
        assertEquals(TEST_PLAYERS_NUMBER, bowlingData.pointsMatrix.length);
        assertEquals(THROWS_NUMBER, bowlingData.pointsMatrix[0].length);
        for (int[] row: bowlingData.pointsMatrix) {
            for (int point: row) {
                assertEquals(POINTS_NO_PLAYED, point);
            }
        }
    }

    @Test
    public void testInitializePointsNullIfPlayersNegative() {
        System.out.println("initializePoints");
        int playersNumber = -1;
        bowlingData.initializePoints(playersNumber);
        assertNull(bowlingData.pointsMatrix);
    }

    /**
     * Test of initializePlayers method, of class AppBowling.
     */
    @Test
    public void testInitializePlayersOK() {
        System.out.println("initializePlayers");
        bowlingData.initializePlayers(TEST_PLAYERS_NUMBER);;
        assertEquals(TEST_PLAYERS_NUMBER, bowlingData.playersData.length);
        assertEquals(PLAYERS_MATRIX_COLUMNS, bowlingData.playersData[0].length);
        for (String[] row: bowlingData.playersData) {
            for (String playerData: row) {
                assertEquals("", playerData);
            }
        }
    }
    
    @Test
    public void testInitializePlayersNullIfPlayersNegative() {
        System.out.println("initializePlayers");
        int playersNumber = -1;
        bowlingData.initializePlayers(playersNumber);
        assertNull(bowlingData.playersData);
    }
               
}
