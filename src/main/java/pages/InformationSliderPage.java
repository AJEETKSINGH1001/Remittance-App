package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InformationSliderPage {
    AndroidDriver driver;
    WebDriverWait wait;

    // Locators for the three sliders
    private By firstSlider = AppiumBy.id("com.gamechange.remittance:id/progressView1");
    private By secondSlider = AppiumBy.id("com.gamechange.remittance:id/progressView2");
    private By thirdSlider = AppiumBy.id("com.gamechange.remittance:id/progressView3");

    //  SignIn Button
    private By nextButton = AppiumBy.id("com.gamechange.remittance:id/signInBtn");
    private By getStartedButton = AppiumBy.id("com.gamechange.remittance:id/signInBtn");

    public InformationSliderPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions
    public boolean isFirstSliderDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstSlider));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSecondSliderDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(secondSlider));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isThirdSliderDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(thirdSlider));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public boolean isGetStartedButtonDisplayed() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(getStartedButton));
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickGetStarted() {
        wait.until(ExpectedConditions.elementToBeClickable(getStartedButton)).click();
    }
}
