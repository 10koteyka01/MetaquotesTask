package pageObjects;

import org.openqa.selenium.WebElement;

import static util.Driver.*;

/**
 * Нижняя панель с контролами
 */
public class BottomBar {
    /**
     * Кнопка "Настройки"
     */
    public WebElement settings() {
        return WebElementById("net.metaquotes.economiccalendar:id/tab_settings");
    }

    /**
     * Кнопка "Календарь"
     */
    public WebElement calendar() {
        return WebElementByXpath("//android.widget.FrameLayout[@content-desc=\"Calendar\"]/android.widget.FrameLayout/android.widget.ImageView");
    }
}
