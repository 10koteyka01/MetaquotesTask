package util;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;

import static io.qameta.allure.Allure.step;

public class Driver {
	public static AndroidDriver driver;
	private static Device device;

	/**
	 * Поиск элемента по Id
	 * Драйвер будет установлен автоматически, если это не было сделано ранее
	 */
	public static WebElement byId(String locator) {
		if (driver == null) setUpDriver();
		return driver.findElement(By.id(locator));
	}

	/**
	 * Поиск элемента по Id
	 * Драйвер будет установлен автоматически, если это не было сделано ранее
	 */
	public static WebElement WebElementById(String locator) {
		return (WebElement) byId(locator);
	}

	/**
	 * Поиск элемента по CSS
	 * Драйвер будет установлен автоматически, если это не было сделано ранее
	 */
	public static List<WebElement> WebElementsByClassName(String locator) {
		if (driver == null) setUpDriver();
		return driver.findElements(By.className(locator));
	}

	/**
	 * Поиск списка элементов по Id
	 * Драйвер будет установлен автоматически, если это не было сделано ранее
	 */
	public static List<WebElement> WebElementsById(String locator) {
		if (driver == null) setUpDriver();
		return driver.findElements(By.id(locator));
	}

	/**
	 * Поиск элемента по xpath
	 * Драйвер будет установлен автоматически, если это не было сделано ранее
	 */
	public static WebElement byXpath(String locator) {
		if (driver == null) setUpDriver();
		return driver.findElement(By.xpath(locator));
	}

	/**
	 * Поиск мобильного элемента по xpath
	 * Драйвер будет установлен автоматически, если это не было сделано ранее
	 */
	public static WebElement WebElementByXpath(String locator) {
		if (driver == null) setUpDriver();
		return byXpath(locator);
	}

	/**
	 * Поиск мобильных элементов по xpath
	 * Драйвер будет установлен автоматически, если это не было сделано ранее
	 */
	public static List<WebElement> WebElementsByXpath(String locator) {
		if (driver == null) setUpDriver();
		return driver.findElements(By.xpath(locator));
	}

	/**
	 * Скролл до первого элемента, содержащего переданный текст
	 *
	 * @param text - текст, который должен быть в искомом элементе
	 * @return - элемент, содержащий text
	 */
	public static WebElement scrollToElementWithText(String text) {
		return driver
				.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(" +
						"new UiSelector().scrollable(true).instance(0)).scrollIntoView(" +
						"new UiSelector().textContains(\"" + text + "\").instance(0))"));
	}

	/**
	 * 1 "swipe" вниз экрана
	 */
	public static WebElement scrollDownOneTime() {
		return driver
				.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(" +
						"new UiSelector().scrollable(true).instance(0))" +
						".scrollToEnd(1)"));
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
		// Уникальный идентификатор подключенного устройства
		capabilities.setCapability("udid", device.getUdid());
		// Java-пакет Android приложения, которое мы хотим запустить. (APK info)
		capabilities.setCapability("appPackage", "net.metaquotes.economiccalendar");
		// Имя activity которые мы хотим запустить из пакета, указанного выше. (APK info)
		capabilities.setCapability("appActivity", "net.metaquotes.ui.MainActivity");

		return capabilities;
	}

	public static void setUpDriver() {
		quit();

		String deviceName = System.getenv("deviceName");

		if (deviceName == null)
			device = Device.Pixel4;
		else
			device = Device.valueOf(deviceName);

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
