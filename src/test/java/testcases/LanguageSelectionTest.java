package testcases;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pages.LanguageSelectionPage;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LanguageSelectionTest {
    private static ExtentReports extent;
    private static ExtentTest test;
    private AndroidDriver driver;
    private LanguageSelectionPage languagePage;

    @BeforeClass
    public void setupExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/LanguageSelectionReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("QA Name", "Ajeet Kumar Singh");
        extent.setSystemInfo("App Name", "Remittance");
        extent.setSystemInfo("Build Name", "v1.1.29");
        extent.setSystemInfo("Mobile OS", "Android 11");
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.UDID, "084113125P054404");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.gamechange.remittance");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.activities.onboarding.SplashScreenActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), dc);
        languagePage = new LanguageSelectionPage(driver);
    }

    @Test(priority = 1)
    public void testLanguageSelectionScreenLoads() {
        test = extent.createTest("Verify Language Selection Screen Loads");
        test.log(Status.INFO, "Checking if the language selection screen is displayed");

        boolean isDisplayed = languagePage.isChooseLanguageDisplayed();
        Assert.assertTrue(isDisplayed, "Language selection screen is not displayed!");
        test.log(Status.PASS, "Language selection screen is displayed successfully!");
    }

    @Test(priority = 2)
    public void testEnglishOptionVisible() {
        test = extent.createTest("Verify English Option is Visible");
        boolean isEnglishVisible = languagePage.isEnglishOptionDisplayed();
        Assert.assertTrue(isEnglishVisible, "English option is not visible!");
        test.log(Status.PASS, "English option is visible!");
    }

    @Test(priority = 3)
    public void testArabicOptionVisible() {
        test = extent.createTest("Verify Arabic Option is Visible");
        boolean isArabicVisible = languagePage.isArabicOptionDisplayed();
        Assert.assertTrue(isArabicVisible, "Arabic option is not visible!");
        test.log(Status.PASS, "Arabic option is visible!");
    }

    @Test(priority = 4)
    public void testContinueButtonDisabledInitially() {
        test = extent.createTest("Verify Continue Button is Disabled Initially");
        boolean isDisabled = languagePage.isContinueButtonDisabled();
        Assert.assertTrue(isDisabled, "Continue button should be disabled!");
        test.log(Status.PASS, "Continue button is correctly disabled initially!");
    }

    @Test(priority = 5)
    public void testSelectingEnglishEnablesContinueButton() {
        test = extent.createTest("Verify Selecting English Enables Continue Button");
        languagePage.selectEnglish();
        boolean isEnabled = !languagePage.isContinueButtonDisabled();
        Assert.assertTrue(isEnabled, "Continue button should be enabled!");
        test.log(Status.PASS, "Continue button is enabled after selecting English!");
    }

    @Test(priority = 6)
    public void testSelectingArabicEnablesContinueButton() {
        test = extent.createTest("Verify Selecting Arabic Enables Continue Button");
        languagePage.selectArabic();
        boolean isEnabled = !languagePage.isContinueButtonDisabled();
        Assert.assertTrue(isEnabled, "Continue button should be enabled!");
        test.log(Status.PASS, "Continue button is enabled after selecting Arabic!");
    }

    @Test(priority = 7)
    public void testNavigationAfterSelectingEnglish() {
        test = extent.createTest("Verify Navigation After Selecting English");
        languagePage.selectEnglish();
        languagePage.clickContinue();
        test.log(Status.PASS, "Navigated to the next screen successfully!");
    }

    @Test(priority = 8)
    public void testNavigationAfterSelectingArabic() {
        test = extent.createTest("Verify Navigation After Selecting Arabic");
        languagePage.selectArabic();
        languagePage.clickContinue();
        test.log(Status.PASS, "Navigated to the next screen successfully!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
