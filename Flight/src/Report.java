import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Report {
    private ArrayList<CheckInPassenger> passengerCheckins;

    public Report(ArrayList<CheckInPassenger> passengerCheckins) {
        this.passengerCheckins = passengerCheckins;
    }

    public void generateReport(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Map<String, FlightStats> flightStatsMap = new HashMap<>();

            for (CheckInPassenger checkin : passengerCheckins) {
                String flightNumber = checkin.getFlightCode();
                double weight = checkin.getWeight();
                double size = checkin.getSize();
                double fee = checkin.getFee();

                // If the flight number does not exist in statistics, add it to the statistics
                if (!flightStatsMap.containsKey(flightNumber)) {
                    flightStatsMap.put(flightNumber, new FlightStats());
                }

                FlightStats flightStats = flightStatsMap.get(flightNumber);
                flightStats.addPassenger(weight, size, fee);
            }

            // Write the report to the file
            for (Map.Entry<String, FlightStats> entry : flightStatsMap.entrySet()) {
                String flightNumber = entry.getKey();
                FlightStats flightStats = entry.getValue();

                writer.write("Flight Number: " + flightNumber);
                writer.newLine();
                writer.write("Total Passengers: " + flightStats.getTotalPassengers());
                writer.newLine();
                writer.write("Total Luggage Size: " + flightStats.getTotalSize() + " cm");
                writer.newLine();
                writer.write("Total Luggage Weight: " + flightStats.getTotalWeight() + " kg");
                writer.newLine();
                writer.write("Total Excess Fee: " + flightStats.getTotalExcessFee() + " £" );
                writer.newLine();
                writer.write("Can Take Off: " + (flightStats.canTakeOff() ? "Yes" : "No"));
                writer.newLine();
                writer.newLine();
            }

            System.out.println("Report generated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FlightStats {
        private int totalPassengers;
        private double totalSize;
        private double totalWeight;
        private double totalExcessFee;

        public void addPassenger(double weight, double size, double fee) {
            totalPassengers++;
            totalSize += size;
            totalWeight += weight;
            totalExcessFee += fee;
        }

        public int getTotalPassengers() {
            return totalPassengers;
        }

        public double getTotalSize() {
            return totalSize;
        }

        public double getTotalWeight() {
            return totalWeight;
        }

        public double getTotalExcessFee() {
            return totalExcessFee;
        }

        public boolean canTakeOff() {
            // Assume maximum luggage size and weight limits for the flight
            double maxAllowedSize = Flight.maxFlightVolume;  
            double maxAllowedWeight = Flight.maxFlightWeight; 
            int maxAllowedPassengers = Flight.maximumPassengers;

            // If the total luggage size or weight exceeds the limit, return false, indicating it cannot take off
            return totalPassengers <= maxAllowedPassengers && totalSize <= maxAllowedSize && totalWeight <= maxAllowedWeight;
        }
    }
}

