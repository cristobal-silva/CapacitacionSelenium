package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance(String filePath) {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
            sparkReporter.config().setDocumentTitle("Reporte de Pruebas");
            sparkReporter.config().setReportName("Evidencia Unificada");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}
