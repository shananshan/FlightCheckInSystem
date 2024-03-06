import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightCheckInSystem {
     public FlightCheckInSystem() {
        passengerList = new ArrayList<>();
        flightList = new ArrayList<>();
    }

    static List<Passenger> passengerList;
    List<Flight> flightList;

    public void readFlights(String csvFilePath) throws IOException, AggregateException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line = reader.readLine(); // Assume the first line contains headers and skip it
        AggregateException aggregateException = new AggregateException("Multiple errors occurred while reading flight details.");

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            boolean lineHasError = false;

            // Check for correct column count
            if (data.length != 13) {
                aggregateException.addException(new MyException("Expected 13 columns, but found " + data.length));
                lineHasError = true;
            }

            // Validate flightCode format if column count is correct or independently
            if (!lineHasError || data.length >= 1) { // Ensure there's at least one column to check the flightCode
                try {
                    validateFlightCodeFormat(data[0]);
                } catch (MyException e) {
                    aggregateException.addException(e);
                    lineHasError = true;
                }
            }

            // Process the data if no error for this line
            if (!lineHasError) {
                flightList.add(new Flight(data));
            }
        }

        // After processing all lines, check if there were any errors collected
        if (aggregateException.hasExceptions()) {
            throw aggregateException;
        }
    }


    void validateFlightCodeFormat(String code) throws MyException {
        // Check the format of flightCode
        if (!code.matches("^[A-Z]{2}-\\d{4}$")) {
            throw new MyException("Invalid flightCode format: " + code + ". Please use the format 'AA-1234'.");
        }
    }
    
    public void readPassengers(String csvFilePath) throws IOException, MyException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line = reader.readLine(); // Assume the first line contains headers and skip it
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length != 5) { // Expected column count for Passenger Bookings CSV
                throw new MyException("Passenger Bookings CSV file format error: Expected 5 columns, but found " + data.length);
            }
            passengerList.add(new Passenger(data));
        }
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public boolean checkIn(String lastname, String code) {
    	//Booking Code Format exception
        try {
            validateBookingCodeFormat(code);
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        for (Passenger p : passengerList) {
            if (p.checkOne(lastname, code))
                return true;
        }
        return false;
    }
    
    void validateBookingCodeFormat(String code) throws MyException {
        // Ensure the booking code follows the format "AB-123456"
        if (!code.matches("^[A-Z]{2}-\\d{6}$")) {
            throw new MyException("Invalid booking code format. Please use the format 'AB-123456'.This line of error information will not be stored.");
        }
    }

    public static Passenger getPassenger(String lastname, String Code){
        for(Passenger p: passengerList){
            if(p.checkOne(lastname, Code))
                return p;
        }
        return null;
    }

    public Flight getFlight(String code) {
        for(Flight f: flightList) {
            if(Objects.equals(f.flightCode, code))
                return f;
        }
        return null;
    }

}
