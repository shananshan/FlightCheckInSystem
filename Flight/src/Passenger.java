import java.util.Objects;

public class Passenger {
    // Passenger booking reference code
    String bookingRefCode;
    // Full name of the passenger
    String name;
    // Code of the flight the passenger is booked on
    String flightCode;
    // Indicates if the passenger has passed some check
    boolean passCheckOrNot;
    // Last name of the passenger
    String lastName;

    // Default constructor
    Passenger() {}

    // Constructor with details
    Passenger(String[] args) {
        bookingRefCode = args[0];
        name = args[1] + " " + args[2];
        lastName = args[2];
        flightCode = args[3];
        passCheckOrNot = Objects.equals(args[4], "TRUE");
    }

    // Returns passenger's name and booking code (placeholder for future use)
    String[] readNameAndCode() {
        return new String[0];
    }

    // Checks if passenger meets specified criteria (placeholder method)
    boolean findPass(String pass, String ch) {
        return passCheckOrNot;
    }

    // Returns the flight code
    String getFlightCode() {
        return flightCode;
    }

    // Returns a string representation of the passenger
    @Override
    public String toString() {
        return bookingRefCode + "," + name + "," + flightCode + "," + (passCheckOrNot ? "True" : "False");
    }

    // Checks if the passenger's last name and flight code match given values
    public boolean checkOne(String lastname, String flightcode) {
        return Objects.equals(lastname, lastName) && Objects.equals(flightcode, bookingRefCode);
    }
}

