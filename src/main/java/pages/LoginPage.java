package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By loginHeader = By.xpath(".//h2[text() = 'Вход']");
    private final By buttonLogin = By.xpath(".//button[text() = 'Войти']");
    private final By buttonRegister = By.linkText("Зарегистрироваться");
    private final By buttonForRecoveryPassword = By.linkText("Восстановить пароль");
    private final By allFieldsForLogin = By.xpath(".//*[@class='text input__textfield text_type_main-default']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(loginHeader).getText() != null
                && !driver.findElement(loginHeader).getText().isEmpty()
        ));
    }

    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }

    public void clickButtonForRecoveryPassword() {
        driver.findElement(buttonForRecoveryPassword).click();
    }

    public WebElement getEmailField() {
        return driver.findElements(allFieldsForLogin).get(0);
    }

    public WebElement getPasswordField() {
        return driver.findElements(allFieldsForLogin).get(1);
    }

    public void setEmailField(String email) {
        getEmailField().sendKeys(email);
    }

    public void setPassword(String password) {
        getPasswordField().sendKeys(password);
    }

}
