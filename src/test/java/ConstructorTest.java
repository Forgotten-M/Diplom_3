import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход на секцию 'Булки'")
    public void testClickBunButton() {
        boolean actualActiveButton = new MainPage(driver)
                .clickSauce()
                .clickBun()
                .isSectionBunActive();
        assertTrue("Должна быть активна секция 'Булки'", actualActiveButton);
    }

    @Test
    @DisplayName("Переход на секцию 'Соусы'")
    public void testClickSaucesButton() {
        boolean actualActiveButton = new MainPage(driver)
                .clickSauce()
                .isSectionSauceActive();
        assertTrue("Должна быть активна секция 'Соусы'", actualActiveButton);
    }

    @Test
    @DisplayName("Переход на секцию 'Начинки'")
    public void testClickFillingsButton() {
        boolean actualActiveButton = new MainPage(driver)
                .clickFilling()
                .isSectionFillingActive();
        assertTrue("Должна быть активна секция 'Начинки'", actualActiveButton);
    }
}