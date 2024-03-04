import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Report class is responsible for generating a flight report based on the check-in data of passengers.
 */
public class Report {
    // List to store the check-in data of passengers
    private ArrayList<CheckInPassenger> passengerCheckins;

    /**
     * Constructor to initialize the Report object with a list of check-in passenger data.
     *
     * @param passengerCheckins List of CheckInPassenger objects containing check-in data.
     */
    public Report(ArrayList<CheckInPassenger> passengerCheckins) {
        this.passengerCheckins = passengerCheckins;
    }

    /**
     * Generates a report based on the check-in data and writes it to the specified file.
     *
     * @param filePath The path to the file where the report will be written.
     */
    public void generateReport(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Map to store flight statistics
            Map<String, FlightStats> flightStatsMap = new HashMap<>();

            // Iterate through each CheckInPassenger object to collect statistics
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

                // Write flight information to the file
                writer.write("Flight Number: " + flightNumber);
                writer.newLine();
                writer.write("Total Passengers: " + flightStats.getTotalPassengers());
                writer.newLine();
                writer.write("Total Luggage Size: " + flightStats.getTotalSize() + " cm^3");
                writer.newLine();
                writer.write("Total Luggage Weight: " + flightStats.getTotalWeight() + " kg");
                writer.newLine();
                writer.write("Total Excess Fee: " + flightStats.getTotalExcessFee() + " 拢" );
                writer.newLine();
                writer.write("Can Take Off: " + (flightStats.canTakeOff() ? "Yes" : "No"));
                writer.newLine();
                writer.newLine();  // Add empty line for better readability
            }

            System.out.println("Report generated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A static nested class to encapsulate flight statistics.
     */
    private static class FlightStats {
        private int totalPassengers;
        private double totalSize;
        private double totalWeight;
        private double totalExcessFee;

        /**
         * Adds statistics for a passenger to the overall flight statistics.
         *
         * @param weight The weight of the passenger's luggage.
         * @param size   The size of the passenger's luggage.
         * @param fee    The excess fee paid by the passenger.
         */
        public void addPassenger(double weight, double size, double fee) {
            totalPassengers++;
            totalSize += size;
            totalWeight += weight;
            totalExcessFee += fee;
        }

        /**
         * Gets the total number of passengers for the flight.
         *
         * @return The total number of passengers.
         */
        public int getTotalPassengers() {
            return totalPassengers;
        }

        /**
         * Gets the total size of luggage for the flight.
         *
         * @return The total size of luggage in cubic centimeters.
         */
        public double getTotalSize() {
            return totalSize;
        }

        /**
         * Gets the total weight of luggage for the flight.
         *
         * @return The total weight of luggage in kilograms.
         */
        public double getTotalWeight() {
            return totalWeight;
        }

        /**
         * Gets the total excess fee paid by passengers for the flight.
         *
         * @return The total excess fee in local currency.
         */
        public double getTotalExcessFee() {
            return totalExcessFee;
        }

        /**
         * Determines whether the flight can take off based on luggage size, weight, and passenger count.
         *
         * @return True if the flight can take off; otherwise, false.
         */
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
