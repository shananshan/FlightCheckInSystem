import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the FlightCheckInSystem functionality.
 */
public class FlightCheckInSystemTest {
    // Test data: "PA-345219"->"PA-5723";    "3PA-345219"->"PA-345219";   "ABC123"->"DA-874156";   "Flight Detail隆垄.csv"->"Flight Detail.csv";

    /**
     * Test case for the check-in functionality in FlightCheckInSystem.
     */
    @Test
    public void testCheckIn() {
        FlightCheckInSystem checkInSystem = new FlightCheckInSystem();

        try {
            checkInSystem.readFlights("Flight Detail.csv");
            checkInSystem.readPassengers("Passenger Bookings.csv");

            // Test a successful check-in
            assertTrue(checkInSystem.checkIn("Smith", "DA-874156"));

            // Test an unsuccessful check-in
            assertFalse(checkInSystem.checkIn("NonExistent", "XY-3456"));
        } catch (IOException e) {
            fail("IOException occurred while reading test data.");
        }
    }

    /**
     * Test case for retrieving passenger information in FlightCheckInSystem.
     */
    @Test
    public void testGetPassenger() {
        FlightCheckInSystem checkInSystem = new FlightCheckInSystem();

        try {
            checkInSystem.readPassengers("Passenger Bookings.csv");

            // Test getting an existing passenger
            Passenger existingPassenger = checkInSystem.getPassenger("Zoe", "PA-345219");
            assertNotNull(existingPassenger);

            // Test getting a non-existent passenger
            Passenger nonExistentPassenger = checkInSystem.getPassenger("NonExistent", "XYZ456");
            assertNull(nonExistentPassenger);
        } catch (IOException e) {
            fail("IOException occurred while reading test data.");
        }
    }

    /**
     * Test case for retrieving flight information in FlightCheckInSystem.
     */
    @Test
    public void testGetFlight() {
        FlightCheckInSystem checkInSystem = new FlightCheckInSystem();

        try {
            checkInSystem.readFlights("Flight Detail.csv");

            // Test getting an existing flight
            Flight existingFlight = checkInSystem.getFlight("PA-5723");
            assertNotNull(existingFlight);

            // Test getting a non-existent flight
            Flight nonExistentFlight = checkInSystem.getFlight("XYZ456");
            assertNull(nonExistentFlight);
        } catch (IOException e) {
            fail("IOException occurred while reading test data.");
        }
    }
}
