package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ReportGenerator {
    private ExtentReports extent;
    private ExtentTest test;
    private String reportDirectory;

    public ReportGenerator(String reportFileName) {
        this.reportDirectory = "reports";
        this.extent = ExtentManager.getInstance(reportDirectory + "/" + reportFileName);
        new File(reportDirectory + "/screenshots").mkdirs();
    }

    public void logTest(String testName, String description) {
        test = extent.createTest(testName, description);
    }

    public void captureScreenshot(WebDriver driver, String screenshotName) {
        if (test == null) {
            throw new IllegalStateException("No se ha inicializado el caso de prueba con logTest.");
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        String relativePath = "screenshots/" + screenshotName + ".png";
        String destPath = reportDirectory + "/" + relativePath;
        try {
            Files.copy(srcFile.toPath(), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
            test.addScreenCaptureFromPath(relativePath, screenshotName);
        } catch (IOException e) {
            test.fail("Error al capturar la captura de pantalla: " + screenshotName);
        }
    }

    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

}
