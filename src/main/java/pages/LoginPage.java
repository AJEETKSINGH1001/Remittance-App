package pages;

import base.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private AppiumDriver driver;

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.remittance.app:id/username")
    private WebElement usernameField;

    @AndroidFindBy(id = "com.remittance.app:id/password")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.remittance.app:id/loginBtn")
    private WebElement loginButton;

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}
