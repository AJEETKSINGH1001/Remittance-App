package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.SupportsRotation;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory extends BaseTest {

    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Set up desired capabilities for Appium
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        caps.setCapability(MobileCapabilityType.UDID, "084113125P054404");
        caps.setCapability("appPackage", "com.gamechange.remittance");
        caps.setCapability("appActivity", ".ui.activities.onboarding.SplashScreenActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        caps.setCapability("noReset", true);
        caps.setCapability("fullReset", false);

        // Initialize the Appium driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

	public static AppiumDriver getDriver() {
		// TODO Auto-generated method stub
		return null;
	}
}
