
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProfileTest extends BaseTest {
    @Test
    @DisplayName("Переход в Личный Кабинет авторизованным пользователем")
    public void personalAreaButtonWithAuthUser() {
        LoginPersonalAccount();

        mainPage.clickPersonalAccount();
        userPage.waitLoadingPage();

        assertEquals(user.getName(), userPage.getUserName());
        assertEquals(user.getEmail(), userPage.getUserLogin());
    }

    @Test
    @DisplayName("Выход из Личного Кабинета")
    public void exitFromLk() {
        LoginPersonalAccount();
        toPersonalAccountAfterLogin();

        userPage.clickExit();
        loginPage.waitLoadHeader();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";

        assertEquals(expectedUrl, webDriver.getCurrentUrl());
    }

    @Step("Авторизация")
    private void LoginPersonalAccount() {
        mainPage.clickPersonalAccount();
        loginPage.waitLoadHeader();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickButtonLogin();
    }

    @Step("Переход в Личный Кабинет после авторизации")
    private void toPersonalAccountAfterLogin() {
        mainPage.clickPersonalAccount();
        userPage.waitLoadingPage();
    }

}