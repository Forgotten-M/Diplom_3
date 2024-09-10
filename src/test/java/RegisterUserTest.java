
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import model.User;
import pages.MainPage;
import pages.LoginPage;
import pages.RegisterPage;
import steps.UserSteps;
import utils.RandomUser;
import utils.WebDriverFactory;

import static org.junit.Assert.assertEquals;
import static constants.Url.*;

public class RegisterUserTest {
    private WebDriver webDriver;
    public WebDriverFactory webDriverFactory = new WebDriverFactory();
    private String actual;
    private final String expected = BASE_URI + LOGIN_URL;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected User user;
    protected final RandomUser randomUser = new RandomUser();

    @Before
    public void setUp() {
        webDriver = webDriverFactory.getWebDriver();
        webDriver.get(BASE_URI + REGISTER_URL);
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
    @DisplayName("Закрытие браузера и удаление юзера")
    public void deleteUserAndCloseBrowser() {
        String response = new UserSteps()
                .loginUser(user)
                .extract().body()
                .path("accessToken");
        if (response != null){
            new UserSteps().deleteUser(response);
        }
        System.out.println("Пользователь " + user.getName() + " Был удален");
        webDriver.quit();
    }


}