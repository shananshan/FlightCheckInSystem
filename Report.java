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

                // 如果该航班号不存在于统计中，添加进统计
                if (!flightStatsMap.containsKey(flightNumber)) {
                    flightStatsMap.put(flightNumber, new FlightStats());
                }

                FlightStats flightStats = flightStatsMap.get(flightNumber);
                flightStats.addPassenger(weight, size, fee);
            }

            // 将报告写入文件
            for (Map.Entry<String, FlightStats> entry : flightStatsMap.entrySet()) {
                String flightNumber = entry.getKey();
                FlightStats flightStats = entry.getValue();

                writer.write("航班号: " + flightNumber);
                writer.newLine();
                writer.write("总乘客人数: " + flightStats.getTotalPassengers());
                writer.newLine();
                writer.write("总行李尺寸: " + flightStats.getTotalSize());
                writer.newLine();
                writer.write("总行李重量: " + flightStats.getTotalWeight());
                writer.newLine();
                writer.write("总超额费用: " + flightStats.getTotalExcessFee());
                writer.newLine();
                writer.write("是否可以起飞: " + (flightStats.canTakeOff() ? "是" : "否"));
                writer.newLine();
                writer.newLine();
            }

            System.out.println("报告生成成功！");

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
        	// 假设航班的最大行李尺寸和最大行李重量限制
            double maxAllowedSize = Flight.maxFlightVolume;  //改
            double maxAllowedWeight = Flight.maxFlightWeight;  // 改

            // 如果总行李尺寸或总行李重量超过限制，返回false，表示不能起飞
            return totalSize <= maxAllowedSize && totalWeight <= maxAllowedWeight;
        }
    }
}
