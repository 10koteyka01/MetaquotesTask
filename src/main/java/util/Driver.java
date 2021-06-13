package util;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;

import static io.qameta.allure.Allure.step;

public class Driver {
    public static AndroidDriver<MobileElement> driver;
    private static Device device;

    public static WebElement byId(String locator) {
        if (driver == null) setUpDriver();
        return driver.findElementById(locator);
    }

    /**
     * Поиск элемента по Id
     */
    public static MobileElement mobileElementById(String locator) {
        return (MobileElement) byId(locator);
    }

    /**
     * Поиск элемента по CSS
     */
    public static List<MobileElement> mobileElementsByClassName(String locator){
        return driver.findElementsByClassName(locator);
    }

    /**
     * Поиск списка элементов по Id
     */
    public static List<MobileElement> mobileElementsById(String locator){
        if (driver == null) setUpDriver();
        return driver.findElementsById(locator);
    }

    /**
     * Поиск элемента по AccessibilityId
     */
    public static MobileElement mobileElementByAccessibilityId(String locator) {
        return driver.findElementByAccessibilityId(locator);
    }

    /**
     * Поиск элемента по xpath
     */
    public static WebElement byXpath(String locator) {
        if (driver == null) setUpDriver();
        return driver.findElementByXPath(locator);
    }

    /**
     * Поиск мобильного элемента по xpath
     */
    public static MobileElement mobileElementByXpath(String locator) {
        return (MobileElement) byXpath(locator);
    }

    /**
     * Поиск мобильных элементов по xpath
     */
    public static List<MobileElement> mobileElementsByXpath(String locator) {
        return driver.findElementsByXPath(locator);
    }

    /**
     * Скролл до первого элемента, содержащего переданный текст
     * @param text - текст, который должен быть в искомом элементе
     * @return - элемент, содержащий text
     */
    public static WebElement scrollToElementWithText(String text) {
        return ((AndroidDriver) driver)
                .findElementByAndroidUIAutomator("new UiScrollable(" +
                        "new UiSelector().scrollable(true).instance(0)).scrollIntoView(" +
                        "new UiSelector().textContains(\"" + text +"\").instance(0))");
    }

    /**
     * 1 "swipe" вниз экрана
     */
    public static WebElement scrollDownOneTime(){
        return ((AndroidDriver) driver)
                .findElementByAndroidUIAutomator("new UiScrollable(" +
                        "new UiSelector().scrollable(true).instance(0))" +
                        ".scrollToEnd(1)");
    }

    private static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // iPhone Simulator, iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4 и т.д.
        // На iOS, это должно быть одно  из допустимых устройств. На Android эта возможность в настоящее время игнорируется,
        // но параметр является обязательным
        capabilities.setCapability("deviceName", device.getDeviceName());
        // Имя ОС на мобильном устройстве
        capabilities.setCapability("platformName", device.getPlatformName());
        // Версия ОС
        capabilities.setCapability("platformVersion", device.getPlatformVersion());
        // Уникальный индефикатор подключенного устройства
        capabilities.setCapability("udid", device.getUdid());
        // Java-пакет Android приложения, которое мы хотим запустить. (APK info)
        capabilities.setCapability("appPackage", "net.metaquotes.economiccalendar");
        // Имя activity которые мы хотим запустить из пакета, указанного выше. (APK info)
        capabilities.setCapability("appActivity", "net.metaquotes.ui.MainActivity");

        return capabilities;
    }

    public static void setUpDriver() {
        quit();

        if (device == null)
            device = Device.Pixel4;

        step("Открыть приложение Tradays", () -> {
            driver = new AndroidDriver(new URL(device.getUrl()), getCapabilities());
        });
    }

    /**
     * Завершить работу драйвера
     */
    public static void quit() {
        if (driver != null)
            driver.quit();
    }
}
