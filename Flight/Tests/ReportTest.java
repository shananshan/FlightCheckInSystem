import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The ReportTest class contains unit tests for the Report class.
 */
public class ReportTest {
    /**
     * Tests the generation of a flight report using the Report class.
     * Checks if the report is generated successfully based on provided check-in passenger data.
     */
    @Test
    public void testReport() {
        // Create a list of CheckInPassenger objects for testing
        ArrayList<CheckInPassenger> checkInPas = new ArrayList<>();
        CheckInPassenger pas1 = new CheckInPassenger("PA-5723", "ZOE", "PA-108489", 230, 30, 100);
        CheckInPassenger pas2 = new CheckInPassenger("PA-5723", "Boy", "PA-846582", 400, 30, 100);
        CheckInPassenger pas3 = new CheckInPassenger("BC-2859", "Cat", "BC-846582", 400, 30, 100);
        checkInPas.add(pas1);
        checkInPas.add(pas2);
        checkInPas.add(pas3);

        // Create a Report object with the test data
        Report report = new Report(checkInPas);

        // Specify the file path for the test report
        String filePath = "test_report.txt";

        // Generate the report and write it to the specified file path
        report.generateReport(filePath);
    }
}
