package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private final WebDriver driver;
    private final By allFieldsForRegister = By.className("input__textfield");
    private final By buttonForRegister = By.className("button_button__33qZ0");
    private final By incorrectPassword = By.className("input__error");
    private final By buttonLogin = By.linkText("Войти");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получение поля для ввода имени")
    public WebElement getNameFieldForRegister() {
        return driver.findElements(allFieldsForRegister).get(0);
    }

    @Step("Получение поля для ввода почты")
    public WebElement getEmailFieldForRegister() {
        return driver.findElements(allFieldsForRegister).get(1);
    }

    @Step("Получение инпута с полем для ввода пароля")
    public WebElement getPasswordFieldForRegister() {
        return driver.findElements(allFieldsForRegister).get(2);
    }

    @Step("Ввод имени пользователя в поле с именем")
    public void setNameForRegister(String name) {
        getNameFieldForRegister().sendKeys(name);
    }

    @Step("Ввод почты пользователя в поле с почтой")
    public void setEmailFieldForRegister(String email) {
        getEmailFieldForRegister().sendKeys(email);
    }

    @Step("Ввод пароля пользователя в поле с паролем")
    public void setPasswordFieldForRegister(String password) {
        getPasswordFieldForRegister().sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться' на странице с регистрацией")
    public void clickButtonForRegister() {
        driver.findElement(buttonForRegister).click();
    }

    @Step("Клик по кнопке 'Войти' на странице с регистрацией")
    public void clickButtonForLogin() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Получение текста при неверной аутентификации пользователя")
    public String getTestForIncorrectPassword() {
        return driver.findElement(incorrectPassword).getText();
    }
}
