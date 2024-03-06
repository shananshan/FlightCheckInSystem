import java.util.ArrayList;

public class CheckInPassenger {
    // flight code, name, booking code, size, weight, fee

    private String flightCode;  // To tag passengers on the same flight
    private String name;
    private String bookingCode; // To check for duplicated travelers
    private double size;        // To sum the size of all passengers
    private double weight;      // sum the weight
    private double fee;         // sum the fee

    public CheckInPassenger(String flightCode, String name,String bookingCode, double size, double weight, double fee) {
        this.flightCode = flightCode;
        this.name = name;
        this.bookingCode = bookingCode;
        this.size = size;
        this.weight = weight;
        this.fee = fee;
    }

    // Getter and setter methods for each attribute
    // To facilitate report class to call these variables

    public String getFlightCode() { return flightCode; }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getBookingCode() { return bookingCode; }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public static boolean duplicatePassenger(String newFlightCode, ArrayList<CheckInPassenger> checkinpassengerList){
        // check if the passenger has checked
        boolean duplicatePassenger = false;
        for (CheckInPassenger passenger : checkinpassengerList){
            if (passenger.getBookingCode().equals(newFlightCode)){
                duplicatePassenger = true;
            }
        }
        return duplicatePassenger;
    }

}
