import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import constants.LoginButtons;

import static org.junit.Assert.assertEquals;
import static constants.LoginButtons.*;

@RunWith(Parameterized.class)
public class LoginUserTest extends BaseTest {
    private final LoginButtons loginButtons;

    public LoginUserTest(LoginButtons loginButtons) {
        this.loginButtons = loginButtons;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {LOGIN_ON_MAIN_PAGE},
                {LOGIN_ON_PERSONAL_ACCOUNT},
                {LOGIN_ON_REGISTER_PAGE},
                {LOGIN_ON_PASSWORD_RECOVERY}
        };
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void loginTest() {
        selectButton(loginButtons);

        loginPage.waitLoadHeader();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickButtonLogin();

        mainPage.waitLoadHeader();

        String actual = webDriver.getCurrentUrl();

        assertEquals("https://stellarburgers.nomoreparties.site/", actual);

    }
}