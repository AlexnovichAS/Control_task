package ru.ozon.managers;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static ru.ozon.utils.PropConst.*;


/**
 * Класс для управления веб драйвером
 *
 * @author Алехнович Александр
 */

public class DriverManager {

    /**
     * Переменна для хранения объекта веб-драйвера
     *
     * @author Алехнович Александр
     * @see WebDriver
     */
    private WebDriver driver;


    /**
     * Переменна для хранения объекта DriverManager
     *
     * @author Алехнович Александр
     */
    private static DriverManager INSTANCE = null;


    /**
     * Менеджер properties
     *
     * @author Алехнович Александр
     * @see TestPropManager#getTestPropManager()
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @author Алехнович Александр
     * @see DriverManager#getDriverManager()
     */
    private DriverManager() {
    }

    /**
     * Метод ленивой инициализации DriverManager
     *
     * @return DriverManager - возвращает DriverManager
     * @author Алехнович Александр
     */
    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     * @author Алехнович Александр
     */
    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }


    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @author Алехнович Александр
     * @see WebDriver#quit()
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    /**
     * Метод инициализирующий веб драйвер
     *
     * @author Алехнович Александр
     */
    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        } else if (OS.isFamilyUnix()) {
            initDriverUnixOsFamily();
        }
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Windows
     *
     * @author Алехнович Александр
     */
    private void initDriverWindowsOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
    }


    /**
     * Метод инициализирующий веб драйвер под ОС семейства Mac
     *
     * @author Алехнович Александр
     */
    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Unix
     *
     * @author Алехнович Александр
     */
    private void initDriverUnixOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);
    }


    /**
     * Метод инициализирующий веб драйвер под любую ОС
     *
     * @param gecko  - переменная firefox из файла application.properties в классе {@link ru.ozon.utils.PropConst}
     * @param chrome - переменная chrome из файла application.properties в классе {@link ru.ozon.utils.PropConst}
     * @author Алехнович Александр
     */
    private void initDriverAnyOsFamily(String gecko, String chrome) {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
 /*               ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//                options.addArguments("user-data-dir=C:\\Users\\aster\\IdeaProjects\\IBS\\Домашки по Selenium\\Control_task\\src\\main\\resources\\drivers");
                options.addArguments("--disable-blink-features");
                options.addArguments("--incognito", "--disable-blink-features=AutomationControlled");
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(cap);*/
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-blink-features");
                options.addArguments("--disable-blink-features=AutomationControlled");
                driver = new ChromeDriver(options);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("source", "Object.defineProperty(navigator, 'webdriver', { get: () => undefined })");
//                driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", params);
                break;
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("84.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                ChromeOptions remoteChromeOptions = new ChromeOptions();
                remoteChromeOptions.addArguments("--disable-notifications");
                capabilities.setCapability(ChromeOptions.CAPABILITY, remoteChromeOptions);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://130.193.49.85:4444/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Assertions.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }
}
