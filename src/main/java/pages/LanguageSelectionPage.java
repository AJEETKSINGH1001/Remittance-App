package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LanguageSelectionPage {
    AndroidDriver driver;
    WebDriverWait wait;

    // Locators
    private By welcomeText = AppiumBy.id("com.gamechange.remittance:id/header_tv");
    private By chooseLanguageText = AppiumBy.id("com.gamechange.remittance:id/title_tv");
    private By englishOption = AppiumBy.id("com.gamechange.remittance:id/englishLanguageTitleTv");
    private By arabicOption = AppiumBy.id("com.gamechange.remittance:id/arabicLanguageTitleTv");
    private By continueButton = AppiumBy.id("com.gamechange.remittance:id/btn_proceed");

    public LanguageSelectionPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions with Explicit Waits
    public boolean isWelcomeTextDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeText));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isChooseLanguageDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(chooseLanguageText));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnglishOptionDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(englishOption));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArabicOptionDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(arabicOption));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isContinueButtonDisabled() {
        try {
            WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
            return !button.isEnabled();
        } catch (Exception e) {
            return true; // Assume disabled if not found
        }
    }

    public void selectEnglish() {
        wait.until(ExpectedConditions.elementToBeClickable(englishOption)).click();
    }

    public void selectArabic() {
        wait.until(ExpectedConditions.elementToBeClickable(arabicOption)).click();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}
