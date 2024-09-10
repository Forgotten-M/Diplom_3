package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By buttonLogin = By.className("button_button__33qZ0");
    private final By personalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By homePageHeader = By.xpath(".//*[@class='active']");
    public final By sectionSauce = By.xpath(".//*[text()='Соусы']//parent::div");
    public final By sectionFilling = By.xpath(".//*[text()='Начинки']//parent::div");
    public final By sectionBun = By.xpath(".//*[text()='Булки']//parent::div");
    private final By sectionSauceActive = By.xpath(".//*[text()='Соусы']//parent::div" +
            "[contains(@class, 'tab_tab_type_current__2BEPc')]");
    private final By sectionFillingActive = By.xpath(".//*[text()='Начинки']//parent::div" +
            "[contains(@class, 'tab_tab_type_current__2BEPc')]");
    private final By sectionBunActive = By.xpath(".//*[text()='Булки']//parent::div" +
            "[contains(@class, 'tab_tab_type_current__2BEPc')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(homePageHeader).isEnabled()
        ));
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public void clickButtonLogin() {
        waitLoadHeader();
        driver.findElement(buttonLogin).click();
    }

    @Step("Клик по ссылке 'Личный кабинет' на главной странице")
    public void clickPersonalAccount() {
        waitLoadHeader();
        driver.findElement(personalAccount).click();
    }

    @Step("Клик по кнопке 'Булки' на главной странице")
    public MainPage clickBun() {
        driver.findElement(sectionBun).click();
        return new MainPage(driver);
    }

    @Step("Клик по кнопке 'Соусы' на главной странице")
    public MainPage clickSauce() {
        driver.findElement(sectionSauce).click();
        return new MainPage(driver);
    }


    @Step("Клик по кнопке 'Начинки' на главной странице")
    public MainPage clickFilling() {
        driver.findElement(sectionFilling).click();
        return new MainPage(driver);
    }


    @Step("Проверка, что секция 'Соусы' активна")
    public boolean isSectionSauceActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(sectionSauceActive));
        return driver.findElement(sectionSauceActive).isDisplayed();
    }


    @Step("Проверка, что секция 'Начинки' активна")
    public boolean isSectionFillingActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(sectionFillingActive));
        return driver.findElement(sectionFillingActive).isDisplayed();
    }

    @Step("Проверка, что секция 'Булки' активна")
    public boolean isSectionBunActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(sectionBunActive));
        return driver.findElement(sectionBunActive).isDisplayed();
    }

}