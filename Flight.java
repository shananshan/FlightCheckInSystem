import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;


public class Flight {
    String flightNum;
    int numOfPassengers;
    int maximumPassengers;
    double maximumBaggageWeight;
    double maxbaggageVolume;
    double extraVolumeFee;
    double extraWeightFee;

    static double maxFlightVolume;
    static double maxFlightWeight;

    String dest;
    String carrier;
    String flightCode;

    List<Passenger> passengerList;

    Flight(){
        passengerList = new ArrayList<>();
    }

    Flight(String []data) {
        passengerList = new ArrayList<>();
        flightCode = data[0];
        dest = data[2];
        carrier = data[3];

        maximumPassengers = Integer.parseInt(data[4]);
        maximumBaggageWeight = Double.parseDouble(data[5]);
        maxbaggageVolume = Double.parseDouble(data[6]);

        extraVolumeFee = Double.parseDouble(data[9]);
        extraWeightFee = Double.parseDouble(data[10]);

        maxFlightWeight = Double.parseDouble(data[11]);
        maxFlightVolume = Double.parseDouble(data[12]);
    }

    void readFromCSV(String csvFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            passengerList.add(new Passenger(data));
        }
    }

    double calculateFee(double width, double height, double length, double weight){
        double weightFee = weight > maximumBaggageWeight ? (weight - maximumBaggageWeight) * extraWeightFee : 0;

        double cur_Volume = width * height * length;
        double volumeFee = 0;
        if(cur_Volume > maxbaggageVolume) {
            volumeFee = (cur_Volume - maxbaggageVolume) * extraVolumeFee;
        }
        return weightFee + volumeFee;
    }

    public String toString(){
        return dest + ", " + carrier + ", " + Integer.toString(maximumPassengers) +
                ", " + Double.toString(maximumBaggageWeight) + ", " + Double.toString(maxbaggageVolume)
                + ", " + Double.toString(extraVolumeFee);
    }

}
