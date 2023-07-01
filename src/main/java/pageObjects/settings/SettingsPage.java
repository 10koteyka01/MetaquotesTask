package pageObjects.settings;

import org.openqa.selenium.WebElement;

import static util.Driver.WebElementByXpath;

/**
 * Страница "Настройки"
 */
public class SettingsPage {
    /**
     * Поле "Язык"
     * @param languageName - "название" языка
     */
    public WebElement language(String languageName) {
        return WebElementByXpath("//android.widget.TextView[@text='" + languageName + "']");
    }
}
