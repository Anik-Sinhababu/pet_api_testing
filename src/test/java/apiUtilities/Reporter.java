package apiUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporter {
    static ExtentReports reports;
    static ExtentSparkReporter spark;

    public static ExtentReports generateReports(String name)
    {
        try{
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + name + "ExtentReport.html");
        spark.config().setReportName("Automation Test Report");
        spark.config().setDocumentTitle("Test Result");
        
        reports = new ExtentReports();
        reports.attachReporter(spark);

        reports.setSystemInfo("Tester", "Anik Sinhababu");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return reports;
    }
}
