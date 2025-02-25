package testcases;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.logging.Level;

public class LaunchTest extends base.BaseTest {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "App Launch Test";
    protected AndroidDriver driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    
    // Extent Reports
    private static ExtentReports extent;
    private static ExtentTest test;
    
    // Metadata values
    private static final String QA_NAME = "Ajeet Kumar Singh"; // Update with actual QA name
    private static final String APP_NAME = "Remittance App";
    private static final String BUILD_NAME = "v1.1.29"; // Update based on build version
    private static final String MOBILE_OS = "Android 11"; // Update with actual OS version
    private static final String MODULE = "1"; // Update module

    @BeforeClass
    public void setupExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ApplaunchTest.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Add custom system information to the report
        extent.setSystemInfo("QA Name", QA_NAME);
        extent.setSystemInfo("App Name", APP_NAME);
        extent.setSystemInfo("Build Name", BUILD_NAME);
        extent.setSystemInfo("Mobile OS", MOBILE_OS);
        extent.setSystemInfo("Module", MODULE);
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "084113125P054404");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.gamechange.remittance");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.activities.onboarding.SplashScreenActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test(priority = 1)
    public void testAppLaunchesSuccessfully() {
        test = extent.createTest("Verify App Launches Successfully");
        test.log(Status.INFO, "Checking if the app launches on splash screen");

        String currentActivity = driver.currentActivity();
        test.log(Status.INFO, "Current Activity: " + currentActivity);

        try {
            Assert.assertEquals(currentActivity, ".ui.activities.onboarding.SplashScreenActivity",
                    "Splash screen activity did not load correctly!");
            test.log(Status.PASS, "Splash screen loaded successfully!");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Splash screen did not load!");
            captureScreenshot("AppLaunchFail");
            throw e;
        }
    }

    @Test(priority = 2)
    public void testLogoIsCenteredOnScreen() {
        test = extent.createTest("Verify Logo is Centered");
        test.log(Status.INFO, "Checking if the logo is centered on the screen");

        WebElement logoElement = driver.findElement(AppiumBy.id("com.gamechange.remittance:id/logoIv")); 

        int screenWidth = driver.manage().window().getSize().width;
        int screenHeight = driver.manage().window().getSize().height;

        int logoX = logoElement.getLocation().getX();
        int logoY = logoElement.getLocation().getY();
        int logoWidth = logoElement.getSize().getWidth();
        int logoHeight = logoElement.getSize().getHeight();

        int logoCenterX = logoX + (logoWidth / 2);
        int logoCenterY = logoY + (logoHeight / 2);

        int screenCenterX = screenWidth / 2;
        int screenCenterY = screenHeight / 2;

        boolean isLogoCentered = Math.abs(logoCenterX - screenCenterX) <= 10 && Math.abs(logoCenterY - screenCenterY) <= 10;
        
        try {
            Assert.assertTrue(isLogoCentered, "Logo is not centered on the screen!");
            test.log(Status.PASS, "Logo is properly centered!");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Logo is not centered!");
            captureScreenshot("LogoNotCentered");
            throw e;
        }
    }

    @Test(priority = 3)
    public void testBottomTextIsDisplayed() {
        test = extent.createTest("Verify Bottom Text is Displayed");
        test.log(Status.INFO, "Checking if the bottom text is visible");

        WebElement bottomTextElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Licensed By CBUAE\"]")); 

        try {
            Assert.assertTrue(bottomTextElement.isDisplayed(), "Bottom text is not displayed!");
            test.log(Status.PASS, "Bottom text is displayed correctly!");

            String expectedText = "Licensed By CBUAE";  
            Assert.assertEquals(bottomTextElement.getText(), expectedText, "Bottom text does not match expected!");
            test.log(Status.PASS, "Bottom text matches expected value!");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Bottom text is missing or incorrect!");
            captureScreenshot("BottomTextFail");
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    private void captureScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("reports/screenshots/" + testName + ".png");
        try {
            Files.copy(srcFile.toPath(), destFile.toPath());
            test.addScreenCaptureFromPath(destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
