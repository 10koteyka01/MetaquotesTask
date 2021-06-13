package util;

/**
 * Перечисление устройств, доступных для запуска тестов
 */
public enum Device {
    Pixel4("Pixel 4 API 28", "Android", "9.0", "emulator-5554", "http://0.0.0.0:4723/wd/hub");

    private String deviceName;
    private String platformName;
    private String platformVersion;
    private String udid;
    private String url;

    public String getDeviceName() {
        return deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getUdid() {
        return udid;
    }

    public String getUrl() {
        return url;
    }

    Device(String deviceName, String platformName, String platformVersion, String udid, String url) {
        this.deviceName = deviceName;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.udid = udid;
        this.url = url;
    }
}
