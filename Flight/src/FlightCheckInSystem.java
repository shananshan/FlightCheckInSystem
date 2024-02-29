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

    public void readFlights(String csvFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            flightList.add(new Flight(data));
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

    public boolean checkIn(String lastname, String Code){
        for(Passenger p: passengerList){
            if(p.checkOne(lastname, Code))
                return true;
        }
        return false;
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
