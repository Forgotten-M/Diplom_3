package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.ConstructorButtons;
import model.User;

import java.time.Duration;

public class UserPage {
    private final WebDriver driver;
    private final By profileButton = By.xpath(".//*[text()='Профиль']");
    private final By allUserFields = By.xpath(".//*[@class='text input__textfield text_type_main-default input__textfield-disabled']");
    private final By constructorButton = By.linkText("Конструктор");
    private final By logoButton = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");


    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(profileButton).isEnabled()
        ));
    }

    @Step("Получение поля с именем пользователя")
    public String getUserName() {
        return driver.findElements(allUserFields).get(0).getAttribute("value");
    }

    @Step("Получение поля с логином пользователя")
    public String getUserLogin() {
        return driver.findElements(allUserFields).get(1).getAttribute("value");
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по Логотипу сайта")
    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    @Step("Клик по кнопке выход")
    public void clickExit() {
        driver.findElement(exitButton).click();
    }

    public void changeButton(ConstructorButtons buttonName) {
        switch (buttonName) {
            case CONSTRUCTOR:
                clickConstructorButton();
                break;
            case LOGO_STELLAR_BURGER:
                clickLogo();
                break;
        }
    }

    @Step("Переход в Личный Кабинет")
    public void transitionToPersonalAccount(MainPage mainPage, LoginPage loginPage, User user) {
        mainPage.clickPersonalAccount();
        loginPage.waitLoadHeader();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickButtonLogin();
        mainPage.clickPersonalAccount();
    }
}
