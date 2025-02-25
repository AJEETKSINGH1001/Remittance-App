package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Sign_In_Existing_User_SMN_Page {
    AndroidDriver driver;
    WebDriverWait wait;

    // Locators for Sign In Screen
    private By skipButton = AppiumBy.id("com.gamechange.remittance:id/skipTv");
    private By signInButton = AppiumBy.id("com.gamechange.remittance:id/signInBtn");

    // Locators for Emirates ID Entry Screen
    private By emiratesIdHeader = AppiumBy.id("com.gamechange.remittance:id/tv_title");
    private By countryFlag = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"com.gamechange.remittance:id/phone_input_layout\"]/android.widget.ImageView");
    private By emiratesIdTextbox = AppiumBy.id("com.gamechange.remittance:id/emiratesIdEt");
    private By continueButton = AppiumBy.id("com.gamechange.remittance:id/bottomButton");
    // Locators for Phone Number Verification Dialog
    private By verificationIcon = AppiumBy.id("com.gamechange.remittance:id/bottomSheetIv");
    private By verificationHeader = AppiumBy.id("com.gamechange.remittance:id/titleTv");
    private By otpInfoText = AppiumBy.id("com.gamechange.remittance:id/subtitleTv");
    private By cancelButton = AppiumBy.id("com.gamechange.remittance:id/closeIv");
    private By continueToOtpButton = AppiumBy.id("com.gamechange.remittance:id/primaryButton");

    public Sign_In_Existing_User_SMN_Page(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void clickSkipButton() {
        wait.until(ExpectedConditions.elementToBeClickable(skipButton)).click();
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public boolean isEmiratesIdHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emiratesIdHeader)).isDisplayed();
    }

    public boolean isCountryFlagDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(countryFlag)).isDisplayed();
    }

    public boolean isEmiratesIdTextboxDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emiratesIdTextbox)).isDisplayed();
    }

    public boolean isContinueButtonEnabled() {
        return driver.findElement(continueButton).isEnabled();
    }

    public void enterEmiratesID(String id) {
        driver.findElement(emiratesIdTextbox).sendKeys(id);
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
 // Actions
    public boolean isVerificationDialogDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(verificationHeader)).isDisplayed();
    }

    public boolean isVerificationIconDisplayed() {
        return driver.findElement(verificationIcon).isDisplayed();
    }

    public String getVerificationHeaderText() {
        return driver.findElement(verificationHeader).getText();
    }

    public String getOtpInfoText() {
        return driver.findElement(otpInfoText).getText();
    }

    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public void clickContinueToOtpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueToOtpButton)).click();
    }
}

