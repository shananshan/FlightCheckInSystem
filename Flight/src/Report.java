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
                String flightNumber = checkin.getNum();
                double weight = checkin.getWeight();
                double size = checkin.getSize();
                double fee = checkin.getFee();

                // ����ú���Ų�������ͳ���У����ӽ�ͳ��
                if (!flightStatsMap.containsKey(flightNumber)) {
                    flightStatsMap.put(flightNumber, new FlightStats());
                }

                FlightStats flightStats = flightStatsMap.get(flightNumber);
                flightStats.addPassenger(weight, size, fee);
            }

            // ������д���ļ�
            for (Map.Entry<String, FlightStats> entry : flightStatsMap.entrySet()) {
                String flightNumber = entry.getKey();
                FlightStats flightStats = entry.getValue();

                writer.write("�����: " + flightNumber);
                writer.newLine();
                writer.write("�ܳ˿�����: " + flightStats.getTotalPassengers());
                writer.newLine();
                writer.write("������ߴ�: " + flightStats.getTotalSize());
                writer.newLine();
                writer.write("����������: " + flightStats.getTotalWeight());
                writer.newLine();
                writer.write("�ܳ������: " + flightStats.getTotalExcessFee());
                writer.newLine();
                writer.write("�Ƿ�������: " + (flightStats.canTakeOff() ? "��" : "��"));
                writer.newLine();
                writer.newLine();
            }

            System.out.println("�������ɳɹ���");

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
        	// ���躽����������ߴ�����������������
            double maxAllowedSize = Flight.maxFlightVolume;  //��
            double maxAllowedWeight = Flight.maxFlightWeight;  // ��

            // ���������ߴ�������������������ƣ�����false����ʾ�������
            return totalSize <= maxAllowedSize && totalWeight <= maxAllowedWeight;
        }
    }
}
