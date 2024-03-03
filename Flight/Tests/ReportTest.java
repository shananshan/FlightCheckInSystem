import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ReportTest {
    @Test
    public void testReport() {
        ArrayList<CheckInPassenger> checkInPas = new ArrayList<>();
        CheckInPassenger pas1 = new CheckInPassenger("PA-5723","ZOE","PA-108489",230,30,100);
        CheckInPassenger pas2 = new CheckInPassenger("PA-5723","Boy","PA-846582",400,30,100);
        CheckInPassenger pas3 = new CheckInPassenger("BC-2859","Cat","BC-846582",400,30,100);
        checkInPas.add(pas1);
        checkInPas.add(pas2);
        checkInPas.add(pas3);
        Report report = new Report(checkInPas);
        String filePath = "test_report.txt";

       
        report.generateReport(filePath);

    }
}
