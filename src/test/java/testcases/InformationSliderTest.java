package testcases;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pages.InformationSliderPage;
import pages.LanguageSelectionPage;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InformationSliderTest {
    private static ExtentReports extent;
    private static ExtentTest test;
    private AndroidDriver driver;
    private InformationSliderPage sliderPage;
    private LanguageSelectionPage languagePage;

    @BeforeClass
    public void setupExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/InformationSliderReport.html");
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
        sliderPage = new InformationSliderPage(driver);

        // Select English and navigate to sliders
        languagePage.selectEnglish();
        languagePage.clickContinue();
    }

    @Test(priority = 1)
    public void testFirstSliderIsVisible() {
        test = extent.createTest("Verify First Information Slider is Visible");
        boolean isDisplayed = sliderPage.isFirstSliderDisplayed();
        Assert.assertTrue(isDisplayed, "First slider is not visible!");
        test.log(Status.PASS, "First slider is visible!");
    }

    @Test(priority = 2)
    public void testSecondSliderIsVisible() {
        test = extent.createTest("Verify Second Information Slider is Visible");
        sliderPage.clickNextButton();
        boolean isDisplayed = sliderPage.isSecondSliderDisplayed();
        Assert.assertTrue(isDisplayed, "Second slider is not visible!");
        test.log(Status.PASS, "Second slider is visible!");
    }

    @Test(priority = 3)
    public void testThirdSliderIsVisible() {
        test = extent.createTest("Verify Third Information Slider is Visible");
        sliderPage.clickNextButton();
        boolean isDisplayed = sliderPage.isThirdSliderDisplayed();
        Assert.assertTrue(isDisplayed, "Third slider is not visible!");
        test.log(Status.PASS, "Third slider is visible!");
    }

    @Test(priority = 4)
    public void testGetStartedButtonIsDisplayedOnLastSlider() {
        test = extent.createTest("Verify SignIn Button Appears on Last Slider");
        sliderPage.clickNextButton();
        boolean isDisplayed = sliderPage.isGetStartedButtonDisplayed();
        Assert.assertTrue(isDisplayed, "Get SignIn button is not visible!");
        test.log(Status.PASS, "Get SignIn button is visible!");
    }

    @Test(priority = 5)
    public void testNavigationToNextScreenAfterGetStarted() {
        test = extent.createTest("Verify Navigation After Clicking Get SignIn");
        sliderPage.clickNextButton();
        sliderPage.clickGetStarted();
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
