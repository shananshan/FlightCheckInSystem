import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class CheckInPassenger {
	// flight code, name, size, weight, fee

	private String flightCode;
    private String name;
    private String bookingCode;
    private double size;
    private double weight;
    private double fee;

    public CheckInPassenger(String flightCode, String name,String bookingCode, double size, double weight, double fee) {
        this.name = CheckInGUI.checkinInfoS[0];
    	this.flightCode = CheckInGUI.checkinInfoS[1];
        this.bookingCode = CheckInGUI.checkinInfoS[2];
        this.size = CheckInGUI.checkinInfoD[0];
        this.weight = CheckInGUI.checkinInfoD[1];
        this.fee = CheckInGUI.checkinInfoD[2];
    }

    // Getter and setter methods for each attribute

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
        boolean duplicatePassenger = false;
        for (CheckInPassenger passenger : checkinpassengerList){

            if (passenger.getBookingCode().equals(newFlightCode)){
                duplicatePassenger = true;
            }
        }
        return duplicatePassenger;
    }

}
