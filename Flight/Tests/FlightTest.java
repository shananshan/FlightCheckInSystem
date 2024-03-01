import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void testCalculateFee() {
        Flight flight = new Flight();
      
        try {
            flight.readFromCSV("Passenger Bookings.csv");

            // Test with baggage within limits
            double feeWithinLimits = flight.calculateFee(20.0, 30.0, 10.0, 15.0);
            assertEquals(0, feeWithinLimits, 0.01);

            // Test with excess weight
            double feeWithExcessWeight = flight.calculateFee(20.0, 30.0, 10.0, 25.0);
            assertEquals(10.0, feeWithExcessWeight, 0.01);

            // Test with excess volume
            double feeWithExcessVolume = flight.calculateFee(20.0, 30.0, 20.0, 15.0);
            assertEquals(200.0, feeWithExcessVolume, 0.01);

            // Test with both excess weight and volume
            double feeWithBothExcess = flight.calculateFee(20.0, 30.0, 20.0, 25.0);
            assertEquals(210.0, feeWithBothExcess, 0.01);
        } catch (IOException e) {
            fail("IOException occurred while reading test data.");
        }
    }

    @Test
    public void testToString() {
       
        String[] flightData = {"ABC123", "Flight1", "Destination1", "Carrier1", "100", "50.0", "1000.0", "10.0", "20.0", "5.0", "10.0", "200.0", "500.0"};
        Flight flight = new Flight(flightData);
        assertEquals("Destination1, Carrier1, 100, 50.0, 1000.0, 20.0", flight.toString());
    }
}
