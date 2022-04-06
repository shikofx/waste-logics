package uk.co.myyard.devmaster.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BrowserManager {

    private static final String BROWSER = "browser";

    private final BrowserProperties properties;
    private static final ThreadLocal<Optional<BrowserManager>> instance =
            ThreadLocal.withInitial(Optional::empty);
    private final WebDriver driver;
    private final WebDriverWait driverWait;
    private int implicitlyWait;

    private BrowserManager() {
        properties = new BrowserProperties();
        driver = initDriver();
        driverWait = new WebDriverWait(driver, 30);
    }

    public static BrowserManager browser() {
        if (instance.get().isEmpty()) {
            instance.set(Optional.of(new BrowserManager()));
        }

        return instance.get().get();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait driverWait() {
        return BrowserManager.browser().driverWait;
    }

    public BrowserProperties getProperties() {
        return properties;
    }

    public void destroy() {
        if (driver != null) {
            getDriver().quit();
        }
        instance.set(Optional.empty());
    }

    private WebDriver initDriver() {
        String browser = System.getProperty(BROWSER);
        if (browser == null) {
            browser = "chrome";
        }

        switch (browser) {
            case BrowserType.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case BrowserType.EDGE -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            case BrowserType.CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        }

        throw new ExceptionInInitializerError("Could not find competitive driver");
    }

    public BrowserManager maximizeWindow() {
        getDriver().manage().window().maximize();
        return this;
    }

    public BrowserManager clearCookies() {
        getDriver().manage().deleteAllCookies();
        return this;
    }

    public void open(String titlePageUrl) {
        getDriver().get(titlePageUrl);
    }

    public void setTimeoutForElement(int implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
        getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(implicitlyWait, SECONDS);
    }

    public void setTimeoutForPageLoad(int timeout) {
        getDriver().manage().timeouts().pageLoadTimeout(timeout, SECONDS);
    }

    public void setTimeoutForScript(int timeout) {
        getDriver().manage().timeouts().setScriptTimeout(timeout, SECONDS);
    }

    public int getImplicitlyWait() {
        return implicitlyWait;
    }
}
