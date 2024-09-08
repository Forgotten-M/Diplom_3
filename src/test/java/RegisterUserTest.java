import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import model.User;
import pages.MainPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.RandomUser;
import utils.WebDriverFactory;

import static org.junit.Assert.assertEquals;
import static constants.Url.*;

public class RegisterUserTest {
    private WebDriver webDriver;
    public WebDriverFactory webDriverFactory = new WebDriverFactory();
    private String actual;
    private final String expected = BASE_URI + LOGIN_ENDPOINT;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected User user;
    protected final RandomUser randomUser = new RandomUser();

    @Before
    public void setUp() {
        webDriver = webDriverFactory.getWebDriver();
        webDriver.get("https://stellarburgers.nomoreparties.site/register");
        user = randomUser.getUser();
        registerPage = new RegisterPage(webDriver);
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
    }

    @Test
    @DisplayName("Регистрация валидного пользователя")
    public void registerValidUser() {

        registerPage.setNameForRegister(user.getName());
        registerPage.setEmailFieldForRegister(user.getEmail());
        registerPage.setPasswordFieldForRegister(user.getPassword());
        registerPage.clickButtonForRegister();

        loginPage.waitLoadHeader();
        actual = webDriver.getCurrentUrl();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Регистрация пользователя с паролем меньше 6 символов")
    public void registerInvalidUser() {
        registerPage.setNameForRegister(user.getName());
        registerPage.setEmailFieldForRegister(user.getEmail());
        registerPage.setPasswordFieldForRegister(randomUser.getInvalidPassword());
        registerPage.clickButtonForRegister();

        actual = registerPage.getTestForIncorrectPassword();
        System.out.println(actual);

        assertEquals("Некорректный пароль", actual);
    }

    @After
    @DisplayName("Выход из браузера")
    public void CloseBrowser() {
        webDriver.quit();
    }
}