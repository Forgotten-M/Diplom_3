import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import constants.LoginButtons;
import model.User;
import pages.*;
import utils.RandomUser;
import utils.WebDriverFactory;

import static io.restassured.RestAssured.given;
import static constants.Url.*;

public class BaseTest {
    protected WebDriver webDriver;
    protected User user;
    protected final RandomUser randomUser = new RandomUser();
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    private RecoveryPasswordPage recoveryPasswordPage;
    protected UserPage userPage;
    public WebDriverFactory webDriverFactory = new WebDriverFactory();

    @Before
    public void setUp() {
        webDriver = webDriverFactory.getWebDriver();
        webDriver.get("https://stellarburgers.nomoreparties.site");
        registerPage = new RegisterPage(webDriver);
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        userPage = new UserPage(webDriver);
        recoveryPasswordPage = new RecoveryPasswordPage(webDriver);
        createUser();
    }

    @After
    @DisplayName("Удаление user и закрытие браузера")
    public void deleteUserAndCloseBrowser() {
        System.out.println("Пользователь " + user.getName() + " Был удален");
        deleteUser();
        webDriver.quit();
    }

    private void createUser() {
        user = randomUser.getUser();
        given().contentType(ContentType.JSON)
                .body(user)
                .post(BASE_URI + "/api/auth/register");
    }

    private void deleteUser() {
        String accessToken = given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(BASE_URI + "/api/auth/login")
                .body().path("accessToken");
        given().contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .body(user).delete(BASE_URI + "/api/auth/user");
    }

    public void selectButton(LoginButtons loginButtons) {
        switch (loginButtons) {
            case LOGIN_ON_MAIN_PAGE:
                mainPage.clickButtonLogin();
                break;
            case LOGIN_ON_PERSONAL_ACCOUNT:
                mainPage.clickPersonalAccount();
                break;
            case LOGIN_ON_REGISTER_PAGE:
                mainPage.clickPersonalAccount();
                loginPage.clickButtonRegister();
                registerPage.clickButtonForLogin();
                break;
            case LOGIN_ON_PASSWORD_RECOVERY:
                mainPage.clickPersonalAccount();
                loginPage.clickButtonForRecoveryPassword();
                recoveryPasswordPage.clickLoginButton();
                break;
        }
    }
}