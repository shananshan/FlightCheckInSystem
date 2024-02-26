import java.io.IOException;

public class Main {
    public static void main(String []args) throws IOException {

//        FlightCheckInSystem fcs = new FlightCheckInSystem();
//
//        fcs.readPassengers("Passenger Bookings.csv");
//        fcs.readFlights("Flight Detail.csv");
//        for(Passenger p: fcs.passengerList){
//            System.out.println(p);
//        }
//
//        System.out.println(fcs.checkIn("Elric", "PA-233677"));
//        System.out.println(fcs.checkIn("Zoe", "PA-345219"));
        CheckInGUI f = new CheckInGUI();
    }
}
