import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ReportTest {
    @Test
	public void testReport() {
        ArrayList<CheckInPassenger> checkInPas = new ArrayList<CheckInPassenger>();
        CheckInPassenger pas = new CheckInPassenger("PA-5723","ZOE","PA-108489",230,30,200);
        checkInPas.add(pas);
        Report report = new Report(checkInPas);
        String filePath = "/Users/gw/Documents/大四下/F21AS/test_report.txt";

       
        report.generateReport(filePath);

    }
}
