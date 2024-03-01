import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    @Test
    public void testToString() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);
        assertEquals("ABC123, John Doe, Flight1, True", passenger.toString());
    }

    @Test
    public void testCheckOne() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);

        // Test matching passenger
        assertTrue(passenger.checkOne("Doe", "ABC123"));

        // Test non-matching passenger
        assertFalse(passenger.checkOne("Smith", "XYZ456"));
    }

    @Test
    public void testGetFlightCode() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);

        assertEquals("Flight1", passenger.getFlightCode());
    }

    @Test
    public void testFindPass() {
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);

        
        String pass = "pass";
        String ch = "A";

        // Test findPass method
        assertTrue(passenger.findPass(pass, ch));
    }

    @Test
    public void testReadNameAndCode() {
        // Adjust the test accordingly based on your actual implementation
        String[] expected = {"John Doe", "ABC123"};
        String[] passengerData = {"ABC123", "John", "Doe", "Flight1", "TRUE"};
        Passenger passenger = new Passenger(passengerData);

        assertArrayEquals(expected, passenger.readNameAndCode());
    }
}
