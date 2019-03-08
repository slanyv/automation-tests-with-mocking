package tools;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * Wrapper around WebDriver instance.
 */
public class Driver {

    private final String CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";
    private final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public ChromeDriver setUp(){

        ChromeDriver webDriver = new ChromeDriver();

        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_PATH);

        webDriver.manage().window().maximize();
        return webDriver;
    }


}
