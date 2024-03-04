import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the Flight class.
 */
public class FlightTest {

    /**
     * Tests the calculateFee method of the Flight class.
     */
    @Test
    public void testCalculateFee() {
        // Flight data for test initialization
        String[] flightData = {"ABC123", "Flight1", "Destination1", "Carrier1", "100", "50.0", "40.0", "24000.0", "20.0", "5.0", "10.0", "200.0", "500.0"};
        Flight flight = new Flight(flightData);

        try {
            // Read passenger bookings from CSV file
            flight.readFromCSV("Passenger Bookings.csv");

            // Test with baggage within limits
            double feeWithinLimits = flight.calculateFee(20.0, 30.0, 40.0, 15.0);
            assertEquals(0, feeWithinLimits, 0.01);

            // Test with excess weight
            double feeWithExcessWeight = flight.calculateFee(20.0, 30.0, 50.0, 15.0);
            assertEquals(60000, feeWithExcessWeight, 0.01);

            // Test with excess volume
            double feeWithExcessVolume = flight.calculateFee(20.0, 30.0, 20.0, 85.0);
            assertEquals(225.0, feeWithExcessVolume, 0.01);

            // Test with both excess weight and volume
            double feeWithBothExcess = flight.calculateFee(20.0, 30.0, 70.0, 55.0);
            assertEquals(180075.0, feeWithBothExcess, 0.01);
        } catch (IOException e) {
            // Fail the test if IOException occurs during data reading
            fail("IOException occurred while reading test data.");
        }
    }

    /**
     * Tests the toString method of the Flight class.
     */
    @Test
    public void testToString() {
        // Flight data for test initialization
        String[] flightData = {"ABC123", "Flight1", "Destination1", "Carrier1", "100", "50.0", "1000.0", "20.0", "20.0", "5.0", "10.0", "200.0", "500.0"};
        Flight flight = new Flight(flightData);

        // Check if the toString method returns the expected result
        assertEquals("Destination1, Carrier1, 100, 1000.0, 20.0, 10.0", flight.toString());
    }
}
