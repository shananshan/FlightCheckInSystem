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

    public void readFlights(String csvFilePath) throws IOException, MyException{
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            validateFlightCodeFormat(data[0]);
            flightList.add(new Flight(data));
        }
    }

    void validateFlightCodeFormat(String code) throws MyException {
        // Check the format of flightCode
        if (!code.matches("^[A-Z]{2}-\\d{4}$")) {
            throw new MyException("Invalid flightCode format: " + code + ". Please use the format 'AA-1234'.");
        }
    }
    
    public void readPassengers(String csvFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
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
            throw new MyException("Invalid booking code format. Please use the format 'AB-123456'.");
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
