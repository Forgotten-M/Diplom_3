import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import constants.ConstructorButtons;

import static org.junit.Assert.assertEquals;
import static constants.ConstructorButtons.CONSTRUCTOR;
import static constants.ConstructorButtons.LOGO_STELLAR_BURGER;

@RunWith(Parameterized.class)
public class GoFromProfileTest extends BaseTest {
    private final ConstructorButtons buttonName;

    public GoFromProfileTest(ConstructorButtons buttonName) {
        this.buttonName = buttonName;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {CONSTRUCTOR},
                {LOGO_STELLAR_BURGER}
        };
    }

    @Test
    @DisplayName("Переход из Личного Кабинета в Конструктор")
    public void transitionToConstructorFromLk() {
        userPage.transitionToPersonalAccount(mainPage, loginPage, user);

        userPage.waitLoadingPage();
        userPage.changeButton(buttonName);

        String expectedUrl = "https://stellarburgers.nomoreparties.site/";

        assertEquals(expectedUrl, webDriver.getCurrentUrl());
    }


}