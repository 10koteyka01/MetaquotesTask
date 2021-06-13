package pageObjects.settings;

import io.appium.java_client.MobileElement;

import static util.Driver.mobileElementByXpath;

/**
 * Страница "Настройки"
 */
public class SettingsPage {
    /**
     * Поле "Язык"
     * @param languageName - "название" языка
     */
    public MobileElement language(String languageName) {
        return mobileElementByXpath("//android.widget.TextView[@text='" + languageName + "']");
    }
}
