package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private final By loginButton = By.linkText("Войти");
    private final WebDriver driver;

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}