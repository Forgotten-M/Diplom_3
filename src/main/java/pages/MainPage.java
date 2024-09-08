package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.SectionName;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By buttonLogin = By.className("button_button__33qZ0");
    private final By personalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By homePageHeader = By.xpath(".//*[@class='active']");
    public final By sectionSauce = By.xpath(".//*[text()='Соусы']//parent::div");
    public final By sectionFilling = By.xpath(".//*[text()='Начинки']//parent::div");
    public final By sectionBun = By.xpath(".//*[text()='Булки']//parent::div");

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

    @Step("Клик по кнопке 'Соусы' на главной странице")
    public void clickSauce() {
        waitLoadHeader();
        driver.findElement(sectionSauce).click();
    }

    @Step("Клик по кнопке 'Булки' на главной странице")
    public void clickBun() {
        waitLoadHeader();
        clickSauce();
        driver.findElement(sectionBun).click();
    }

    @Step("Клик по кнопке 'Начинки' на главной странице")
    public void clickFilling() {
        waitLoadHeader();
        driver.findElement(sectionFilling).click();
    }

    @Step("Клик по одной из секции 'Соусы' 'Булки' 'Начинки' на главной странице ")
    public void clickSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                clickBun();
                break;
            case SAUCE:
                clickSauce();
                break;
            case FILLING:
                clickFilling();
                break;
        }
    }

    public WebElement getSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                return driver.findElement(sectionBun);
            case SAUCE:
                return driver.findElement(sectionSauce);
            case FILLING:
                return driver.findElement(sectionFilling);
            default:
                throw new RuntimeException("Некорректное название элемента");
        }
    }

    public String getClassName(SectionName sectionName) {
        return getSection(sectionName).getText();
    }

}