import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Passenger class.
 */
public class PassengerTest {

    /**
     * Test case for the toString() method of the Passenger class.
     * Verifies that the method returns the expected string representation.
     */
    @Test
    public void testToString() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);
        assertEquals("ABC123,John Doe,Flight1,True", passenger.toString());
    }

    /**
     * Test case for the checkOne() method of the Passenger class.
     * Verifies that the method correctly checks for a matching passenger based on name and ticket number.
     */
    @Test
    public void testCheckOne() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);

        // Test matching passenger
        assertTrue(passenger.checkOne("Doe", "ABC123"));

        // Test non-matching passenger
        assertFalse(passenger.checkOne("Smith", "XYZ456"));
    }

    /**
     * Test case for the getFlightCode() method of the Passenger class.
     * Verifies that the method returns the correct flight code associated with the passenger.
     */
    @Test
    public void testGetFlightCode() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);

        assertEquals("Flight1", passenger.getFlightCode());
    }

    /**
     * Test case for the findPass() method of the Passenger class.
     * Verifies that the method correctly searches for a specified substring in the passenger data.
     */
    @Test
    public void testFindPass() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);

        // Define parameters for the findPass method
        String pass = "pass";
        String ch = "A";

        // Test findPass method
        assertTrue(passenger.findPass(pass, ch));
    }
}
