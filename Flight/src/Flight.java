import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class Flight {
    // Attributes to store flight details.
    String flightNum; // Unique flight number.
    int numOfPassengers; // Current number of passengers on the flight.
    static int maximumPassengers; // Maximum number of passengers 
    double maximumBaggageWeight; // Maximum total weight of baggage 
    double maxbaggageVolume; // Maximum volume of baggage allowed per passenger.
    double extraVolumeFee; // Fee for each unit volume of baggage exceeding the maximum allowed.
    double extraWeightFee; // Fee for each unit weight of baggage exceeding the maximum allowed.

    static double maxFlightVolume; // Maximum total volume of baggage allowed on the flight.
    static double maxFlightWeight; // Maximum total weight of baggage allowed on the flight.

    String dest; // Destination of the flight.
    String carrier; // Carrier or airline operating the flight.
    String flightCode; 

    // List to store passengers on the flight.
    List<Passenger> passengerList;

    // Default constructor initializes an empty list of passengers.
    Flight(){
        passengerList = new ArrayList<>();
    }

    // Constructor that initializes flight details from a given array of data.
    Flight(String []data) {
        passengerList = new ArrayList<>();
        flightCode = data[0];
        dest = data[2];
        carrier = data[3];

        // Parse numeric and double values from the string array.
        maximumPassengers = Integer.parseInt(data[4]);
        maximumBaggageWeight = Double.parseDouble(data[6]);
        maxbaggageVolume = Double.parseDouble(data[7]);
        extraVolumeFee = Double.parseDouble(data[10]);
        extraWeightFee = Double.parseDouble(data[9]);
        maxFlightWeight = Double.parseDouble(data[11]);
        maxFlightVolume = Double.parseDouble(data[12]);
    }

    // Reads passenger data from a CSV file and adds them to the passenger list.
    void readFromCSV(String csvFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line = reader.readLine(); // Skip header line or check if needed.
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            passengerList.add(new Passenger(data));
        }
    }

    // Calculates extra fees based on baggage dimensions and weight.
    double calculateFee(double width, double height, double length, double weight){
        // Calculate extra weight fee.
        double weightFee = weight > maximumBaggageWeight ? (weight - maximumBaggageWeight) * extraWeightFee : 0;

        // Calculate extra volume fee.
        double cur_Volume = width * height * length;
        double volumeFee = cur_Volume > maxbaggageVolume ? (cur_Volume - maxbaggageVolume) * extraVolumeFee : 0;

        return weightFee + volumeFee;
    }

    // Overrides toString method to provide a string representation of flight details.
    public String toString(){
        return dest + ", " + carrier + ", " + Integer.toString(maximumPassengers) +
                ", " + Double.toString(maximumBaggageWeight) + ", " + Double.toString(maxbaggageVolume)
                + ", " + Double.toString(extraVolumeFee);
    }
}
