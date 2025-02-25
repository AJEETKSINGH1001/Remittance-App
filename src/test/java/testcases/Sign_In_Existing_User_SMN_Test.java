package testcases;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pages.LanguageSelectionPage;
import pages.Sign_In_Existing_User_SMN_Page;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Sign_In_Existing_User_SMN_Test {
    private static ExtentReports extent;
    private static ExtentTest test;
    private AndroidDriver driver;
    private LanguageSelectionPage languagePage;
    private Sign_In_Existing_User_SMN_Page signInAndEmiratesIDPage;

    @BeforeClass
    public void setupExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/Sign_In_Existing_User_SMN.html");
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
        signInAndEmiratesIDPage = new Sign_In_Existing_User_SMN_Page(driver);

        // Select English and navigate to Sign In screen
        languagePage.selectEnglish();
        languagePage.clickContinue();
    }

    @Test(priority = 1)
    public void testSkipButtonNavigatesToSignInScreen() {
        test = extent.createTest("Verify Skip Button Navigates to Sign In Screen");
        signInAndEmiratesIDPage.clickSkipButton();
        boolean isSignInDisplayed = signInAndEmiratesIDPage.isEmiratesIdHeaderDisplayed();
        Assert.assertTrue(isSignInDisplayed, "Skipping did not navigate to Sign In screen!");
        //test.log(Status.PASS, "Skip button successfully navigated to Sign In screen!");
    }

    @Test(priority = 2)
    public void testSignInButtonNavigatesToEmiratesIDScreen() {
        test = extent.createTest("Verify Sign In Button Navigates to Emirates ID Screen");
        signInAndEmiratesIDPage.clickSkipButton();
        signInAndEmiratesIDPage.clickSignInButton();
        boolean isDisplayed = signInAndEmiratesIDPage.isEmiratesIdHeaderDisplayed();
        Assert.assertTrue(isDisplayed, "Sign In did not navigate to Emirates ID screen!");
        //test.log(Status.PASS, "Sign In button successfully navigated to Emirates ID screen!");
    }

    @Test(priority = 3)
    public void testEmiratesIDScreenElementsAreDisplayed() {
        test = extent.createTest("Verify Emirates ID Screen Elements are Displayed");
        signInAndEmiratesIDPage.clickSkipButton();
        signInAndEmiratesIDPage.clickSignInButton();

        Assert.assertTrue(signInAndEmiratesIDPage.isCountryFlagDisplayed(), "Country flag is not visible!");
        Assert.assertTrue(signInAndEmiratesIDPage.isEmiratesIdTextboxDisplayed(), "Emirates ID text box is not visible!");
        Assert.assertFalse(signInAndEmiratesIDPage.isContinueButtonEnabled(), "Continue button should be disabled initially!");

        //test.log(Status.PASS, "Emirates ID screen elements are displayed correctly!");
    }

    @Test(priority = 5)
    public void testEnteringEmiratesIDEnablesContinueButton() {
        test = extent.createTest("Verify Entering Emirates ID Enables Continue Button");
        signInAndEmiratesIDPage.clickSkipButton();
        signInAndEmiratesIDPage.clickSignInButton();
        
        signInAndEmiratesIDPage.enterEmiratesID("784-1985-1234567-1");
        boolean isEnabled = signInAndEmiratesIDPage.isContinueButtonEnabled();
        Assert.assertTrue(isEnabled, "Continue button should be enabled after entering Emirates ID!");

       // test.log(Status.PASS, "Continue button is enabled after entering Emirates ID!");
    }

    @Test(priority = 6)
    public void testNavigationAfterEnteringEmiratesID() {
        test = extent.createTest("Verify Navigation After Entering Emirates ID");
        signInAndEmiratesIDPage.clickSkipButton();
        signInAndEmiratesIDPage.clickSignInButton();
        
        signInAndEmiratesIDPage.enterEmiratesID("784-1985-1234567-1");
        signInAndEmiratesIDPage.clickContinueButton();
        
        //test.log(Status.PASS, "Successfully navigated to the next screen after entering Emirates ID!");
    }

    @Test(priority = 7)
    public void testPhoneNumberVerificationDialogAppears() {
        test = extent.createTest("Verify Phone Number Verification Dialog Appears");
        signInAndEmiratesIDPage.enterEmiratesID("784-1985-1234567-1");
        signInAndEmiratesIDPage.clickContinueButton();

        boolean isDialogDisplayed = signInAndEmiratesIDPage.isVerificationDialogDisplayed();
        Assert.assertTrue(isDialogDisplayed, "Phone Number Verification Dialog is not displayed!");

       // test.log(Status.PASS, "Phone Number Verification Dialog appeared successfully!");
    }

    @Test(priority = 8)
    public void testPhoneNumberVerificationDialogUI() {
        test = extent.createTest("Verify Phone Number Verification Dialog UI");
        signInAndEmiratesIDPage.enterEmiratesID("784-1985-1234567-1");
        signInAndEmiratesIDPage.clickContinueButton();

        Assert.assertTrue(signInAndEmiratesIDPage.isVerificationIconDisplayed(), "Verification icon is missing!");
        Assert.assertEquals(signInAndEmiratesIDPage.getVerificationHeaderText(), "Phone Number Verification", "Incorrect header text!");
        Assert.assertTrue(signInAndEmiratesIDPage.getOtpInfoText().contains("***4562"), "OTP info text does not match!");

       // test.log(Status.PASS, "Phone Number Verification Dialog UI is correct!");
    }

    @Test(priority = 9)
    public void testCancelButtonClosesDialog() {
        test = extent.createTest("Verify Cancel Button Closes Dialog");
        signInAndEmiratesIDPage.enterEmiratesID("784-1985-1234567-1");
        signInAndEmiratesIDPage.clickContinueButton();

        signInAndEmiratesIDPage.clickCancelButton();
        boolean isDialogClosed = !signInAndEmiratesIDPage.isVerificationDialogDisplayed();
        Assert.assertTrue(isDialogClosed, "Phone Number Verification Dialog should be closed!");

      // test.log(Status.PASS, "Cancel button successfully closed the dialog!");
    }

    @Test(priority = 10)
    public void testContinueButtonNavigatesToOtpScreen() {
    	
        test = extent.createTest("Verify Navigation After Entering Emirates ID");
        signInAndEmiratesIDPage.clickSkipButton();
        signInAndEmiratesIDPage.clickSignInButton();
        
        signInAndEmiratesIDPage.enterEmiratesID("784-1985-1234567-1");
        signInAndEmiratesIDPage.clickContinueButton();
        
        test.log(Status.PASS, "Successfully navigated to the next screen after entering Emirates ID!");
        test = extent.createTest("Verify Continue Button Navigates to OTP Screen");
        signInAndEmiratesIDPage.enterEmiratesID("784-1985-1234567-1");
        signInAndEmiratesIDPage.clickContinueButton();

        signInAndEmiratesIDPage.clickContinueToOtpButton();

        // Assuming OTP screen has a header with id otp_screen_header
        boolean isOtpScreenDisplayed = driver.findElement(AppiumBy.id("com.gamechange.remittance:id/topBar")).isDisplayed();
        Assert.assertTrue(isOtpScreenDisplayed, "OTP Entry Screen did not appear!");

        //test.log(Status.PASS, "Successfully navigated to OTP Entry Screen!");
    }
    
 // ✅ Correctly Logs the Test Status After Execution
    @AfterMethod
    public void logTestResults(ITestResult result) {
        if (test == null) {
            return; // Prevent NullPointerException in case test is not initialized
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            test.log(Status.FAIL, "Failure Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        }
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}