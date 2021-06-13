package pageObjects;

import io.appium.java_client.MobileElement;

import static util.Driver.mobileElementById;

/**
 * Нижняя панель с контролами
 */
public class BottomBar {
    /**
     * Кнопка "Настройки"
     */
    public MobileElement settings() {
        return mobileElementById("net.metaquotes.economiccalendar:id/bottom_bar_settings");
    }

    /**
     * Кнопка "Календарь"
     */
    public MobileElement calendar() {
        return mobileElementById("net.metaquotes.economiccalendar:id/bottom_bar_calendar");
    }
}
