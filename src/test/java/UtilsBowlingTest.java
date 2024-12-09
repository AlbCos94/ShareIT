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
public class UtilsBowlingTest {
   
    public static final int POINTS_NO_PLAYED = -1;
    public static final int INDEX_NAME = 0;
    public static final int INDEX_LAST_NAME = 1;
    public static final int INDEX_AGE = 2;
    public static final int THROWS_NUMBER = 10;
    public static final int MAX_POINTS = 10;
    public static final int PLAYERS_MATRIX_COLUMNS = 3;

    int[][] pointsMatrix;
    String[][] playersData;

    public UtilsBowlingTest() {
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

    /**
     * Test of insertPlayerNames method, of class AppBowling.
     */
    @Test
    public void testInsertPlayerNames_NullArray() {
        String[][] playersData = null;
        int rowNumber = 0;
        String name = "John";
        String lastName = "Doe";
        int age = 30;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // Since the array is null, no changes should be made
        assertArrayEquals(null, playersData);
    }

    @Test
    public void testInsertPlayerNames_InvalidRowNumber_Negative() {
        String[][] playersData = new String[3][3];
        int rowNumber = -1;
        String name = "John";
        String lastName = "Doe";
        int age = 30;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // No changes should be made since the row number is invalid
        assertArrayEquals(new String[3][3], playersData);
    }

    @Test
    public void testInsertPlayerNames_InvalidRowNumber_GreaterThanLength() {
        String[][] playersData = new String[3][3];
        int rowNumber = 4;
        String name = "John";
        String lastName = "Doe";
        int age = 30;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // No changes should be made since the row number is invalid
        assertArrayEquals(new String[3][3], playersData);
    }

    @Test
    public void testInsertPlayerNames_NullName() {
        String[][] playersData = new String[3][3];
        int rowNumber = 0;
        String name = null;
        String lastName = "Doe";
        int age = 30;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // No changes should be made since the name is null
        assertArrayEquals(new String[3][3], playersData);
    }

    @Test
    public void testInsertPlayerNames_EmptyName() {
        String[][] playersData = new String[3][3];
        int rowNumber = 0;
        String name = "";
        String lastName = "Doe";
        int age = 30;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // No changes should be made since the name is empty
        assertArrayEquals(new String[3][3], playersData);
    }

    @Test
    public void testInsertPlayerNames_NullLastName() {
        String[][] playersData = new String[3][3];
        int rowNumber = 0;
        String name = "John";
        String lastName = null;
        int age = 30;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // No changes should be made since the last name is null
        assertArrayEquals(new String[3][3], playersData);
    }

    @Test
    public void testInsertPlayerNames_EmptyLastName() {
        String[][] playersData = new String[3][3];
        int rowNumber = 0;
        String name = "John";
        String lastName = "";
        int age = 30;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // No changes should be made since the last name is empty
        assertArrayEquals(new String[3][3], playersData);
    }

    @Test
    public void testInsertPlayerNames_NegativeAge() {
        String[][] playersData = new String[3][3];
        int rowNumber = 0;
        String name = "John";
        String lastName = "Doe";
        int age = -10;

        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);

        // No changes should be made since the age is negative
        assertArrayEquals(new String[3][3], playersData);
    }

    @Test
    public void testInsertPlayerNames_ValidData() {
        String[][] playersData = new String[3][3];
        int rowNumber = 1;
        String name = "Jane";
        String lastName = "Smith";
        int age = 25;
    
        UtilsBowling.insertPlayerNames(playersData, rowNumber, name, lastName, age);
    
        String[][] expectedData = new String[3][3];
        expectedData[rowNumber][INDEX_NAME] = name;
        expectedData[rowNumber][INDEX_LAST_NAME] = lastName;
        expectedData[rowNumber][INDEX_AGE] = String.valueOf(age);
    
        assertArrayEquals(expectedData, playersData);
    }
    
    /* 
     * Test of setRoundPoints method, of class AppBowling.
     */
    @Test
    public void testSetRoundPoints_NullMatrix() {
        int[][] pointsMatrix = null;
        int rowIndex = 0;
        int round = 1;
        int points = 5;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        // No changes should be made since the matrix is null
        assertNull(pointsMatrix);
    }

    @Test
    public void testSetRoundPoints_InvalidRowIndex_Negative() {
        int[][] pointsMatrix = new int[2][THROWS_NUMBER];
        int rowIndex = -1;
        int round = 1;
        int points = 5;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        // No changes should be made since the row index is invalid
        assertArrayEquals(new int[2][THROWS_NUMBER], pointsMatrix);
    }

    @Test
    public void testSetRoundPoints_InvalidRowIndex_GreaterThanLength() {
        int[][] pointsMatrix = new int[2][THROWS_NUMBER];
        int rowIndex = pointsMatrix.length;
        int round = 1;
        int points = 5;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        // No changes should be made since the row index is invalid
        assertArrayEquals(new int[2][THROWS_NUMBER], pointsMatrix);
    }

    @Test
    public void testSetRoundPoints_InvalidRound_LessthanOne() {
        int[][] pointsMatrix = new int[2][THROWS_NUMBER];
        int rowIndex = 0;
        int round = 0;
        int points = 5;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        // No changes should be made since the round is invalid
        assertArrayEquals(new int[2][THROWS_NUMBER], pointsMatrix);
    }

    @Test
    public void testSetRoundPoints_InvalidRound_GreaterThanThrows() {
        int[][] pointsMatrix = new int[2][THROWS_NUMBER];
        int rowIndex = 0;
        int round = THROWS_NUMBER + 1;
        int points = 5;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        // No changes should be made since the round is invalid
        assertArrayEquals(new int[2][THROWS_NUMBER], pointsMatrix);
    }

    @Test
    public void testSetRoundPoints_InvalidPoints_Negative() {
        int[][] pointsMatrix = new int[2][THROWS_NUMBER];
        int rowIndex = 0;
        int round = 1;
        int points = -2;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        // No changes should be made since the points are invalid
        assertArrayEquals(new int[2][THROWS_NUMBER], pointsMatrix);
    }

    @Test
    public void testSetRoundPoints_InvalidPoints_GreaterThanMax() {
        int[][] pointsMatrix = new int[2][THROWS_NUMBER];
        int rowIndex = 0;
        int round = 1;
        int points = MAX_POINTS + 1;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        // No changes should be made since the points are invalid
        assertArrayEquals(new int[2][THROWS_NUMBER], pointsMatrix);
    }

    @Test
    public void testSetRoundPoints_ValidData() {
        int[][] pointsMatrix = new int[2][THROWS_NUMBER];
        int rowIndex = 1;
        int round = 2;
        int points = 8;

        UtilsBowling.setRoundPoints(pointsMatrix, rowIndex, round, points);

        int[][] expectedMatrix = new int[2][THROWS_NUMBER];
        expectedMatrix[rowIndex][round - 1] = points;

        assertArrayEquals(expectedMatrix, pointsMatrix);
    }

   /* 
     * Test of lookForPlayer method, of class AppBowling.
     */    
    @Test
    public void testLookForPlayer_nullPlayersMatrix() {
      String[][] playersMatrix = null;
      String playerFullName = "John Doe";
      int expectedResult = -1;
      
      int actualResult = UtilsBowling.lookForPlayer(playersMatrix, playerFullName);
      
      assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testLookForPlayer_nullPlayerFullName() {
      String[][] playersMatrix = new String[][] {{"John", "Doe"}};
      String playerFullName = null;
      int expectedResult = -1;
      
      int actualResult = UtilsBowling.lookForPlayer(playersMatrix, playerFullName);
      
      assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testLookForPlayer_emptyPlayerFullName() {
      String[][] playersMatrix = new String[][] {{"John", "Doe"}};
      String playerFullName = "";
      int expectedResult = -1;
      
      int actualResult = UtilsBowling.lookForPlayer(playersMatrix, playerFullName);
      
      assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testLookForPlayer_invalidPlayerName() {
      String[][] playersMatrix = new String[][] {{"John", "Doe"}};
      String playerFullName = "John Doe Junior";
      int expectedResult = -1;
      
      int actualResult = UtilsBowling.lookForPlayer(playersMatrix, playerFullName);
      
      assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testLookForPlayer_playerNotFound() {
      String[][] playersMatrix = new String[][] {{"Jane", "Doe"}};
      String playerFullName = "John Doe";
      int expectedResult = -1;
      
      int actualResult = UtilsBowling.lookForPlayer(playersMatrix, playerFullName);
      
      assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testLookForPlayer_playerFound() {
      String[][] playersMatrix = new String[][] {{"John", "Doe"}};
      String playerFullName = "John Doe";
      int expectedResult = 0;
      
      int actualResult = UtilsBowling.lookForPlayer(playersMatrix, playerFullName);
      
      assertEquals(expectedResult, actualResult);
    }
     
    /* 
     * Test of getOrderedIndexArrayWithTotalPoints method, of class AppBowling.
     */    
    @Test
    public void testGetOrderedIndexArrayWithTotalPoints_nullArray() {
      int[] totalPointsArray = null;
      int[] expectedResult = null;
      
      int[] actualResult = UtilsBowling.getOrderedIndexArrayWithTotalPoints(totalPointsArray);
      
      assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetOrderedIndexArrayWithTotalPoints_emptyArray() {
      int[] totalPointsArray = new int[0];
      int[] expectedResult = new int[0];
      
      int[] actualResult = UtilsBowling.getOrderedIndexArrayWithTotalPoints(totalPointsArray);
      
      assertArrayEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testGetOrderedIndexArrayWithTotalPoints_singleElement() {
      int[] totalPointsArray = new int[]{10};
      int[] expectedResult = new int[]{0};
      
      int[] actualResult = UtilsBowling.getOrderedIndexArrayWithTotalPoints(totalPointsArray);
      
      assertArrayEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testGetOrderedIndexArrayWithTotalPoints_equalElements() {
      int[] totalPointsArray = new int[]{10, 10, 5};
      int[] expectedResult = new int[]{0, 1, 2};
      
      int[] actualResult = UtilsBowling.getOrderedIndexArrayWithTotalPoints(totalPointsArray);
      
      assertArrayEquals(expectedResult, actualResult);
    }
         
    @Test
    public void testGetOrderedIndexArrayWithTotalPoints_descendingOrder() {
      int[] totalPointsArray = new int[]{50, 20, 80};
      int[] expectedResult = new int[]{2, 0, 1};
      
      int[] actualResult = UtilsBowling.getOrderedIndexArrayWithTotalPoints(totalPointsArray);
      
      assertArrayEquals(expectedResult, actualResult);
    }
       
    @Test
    public void testGetOrderedIndexArrayWithTotalPoints_enunciat() {
      int[] totalPointsArray = new int[]{30, 30, 54, 12, 43, 55};
      int[] expectedResult = new int[]{5, 2, 4, 0, 1, 3};
      
      int[] actualResult = UtilsBowling.getOrderedIndexArrayWithTotalPoints(totalPointsArray);
      
      assertArrayEquals(expectedResult, actualResult);
    }

    /* 
     * Test of calculateTotalPointsArray method, of class AppBowling.
     */        
    @Test
    public void testCalculateTotalPointsArray_nullMatrix() {
      int[][] pointsMatrix = null;
      int[] expectedResult = null;
      
      int[] actualResult = UtilsBowling.calculateTotalPointsArray(pointsMatrix);
      
      assertArrayEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testCalculateTotalPointsArray_emptyMatrix() {
      int[][] pointsMatrix = new int[0][]; // Empty 2D array
      int[] expectedResult = new int[0];
      
      int[] actualResult = UtilsBowling.calculateTotalPointsArray(pointsMatrix);
      
      assertArrayEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testCalculateTotalPointsArray_singleRow() {
      int[][] pointsMatrix = new int[][]{{10, 20, 30}};
      int[] expectedResult = new int[]{60};
      
      int[] actualResult = UtilsBowling.calculateTotalPointsArray(pointsMatrix);
      
      assertArrayEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testCalculateTotalPointsArray_multipleRows() {
      int[][] pointsMatrix = new int[][]{{10, 5}, {20, 15}, {3, 8}};
      int[] expectedResult = new int[]{15, 35, 11};
      
      int[] actualResult = UtilsBowling.calculateTotalPointsArray(pointsMatrix);
      
      assertArrayEquals(expectedResult, actualResult);
    }
        
    @Test
    public void testCalculateTotalPointsArray_notTakeInvalidValues() {
      // Mock or stub the calculateRowPoints method to verify it's called
      // This assumes you have a way to mock or stub methods in your testing framework
      
      int[][] pointsMatrix = new int[][]{{1, 2, -1}, {-1,3, 4}};
      int[] expectedResult = new int[]{3, 7};
      
      // Mock calculateRowPoints to return expected values
      int[] actualResult = UtilsBowling.calculateTotalPointsArray(pointsMatrix);
      
      assertArrayEquals(expectedResult, actualResult);
      // Additional assertions to verify calculateRowPoints was called with expected arguments
    }

    @Test
    public void testCalculateTotalPointsArray_notStartedYet() {
      int[][] pointsMatrix = new int[][] {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
                                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
      int[] expectedResult = new int[]{0, 0, 0};
      
      int[] actualResult = UtilsBowling.calculateTotalPointsArray(pointsMatrix);
      
      assertArrayEquals(expectedResult, actualResult);
    }
}
