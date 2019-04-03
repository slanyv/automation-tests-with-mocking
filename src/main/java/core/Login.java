package core;

import org.openqa.selenium.chrome.ChromeDriver;

import core.constants.GeneralContants;
import core.screens.HomePageScreen;
import core.screens.LoginScreen;
import tools.Navigate;
import tools.Pause;

/**
 * @author Viktor Slany
 */
public class Login {

    private ChromeDriver driver;

    public Login(ChromeDriver driver) {
        this.driver = driver;
    }

    public void login(String userName, String password) {

        Navigate.to(driver, GeneralContants.HOME_PAGE_URL);
        driver.findElement(LoginScreen.usernameInput).sendKeys(userName);
        driver.findElement(LoginScreen.passwordInput).sendKeys(password);
        driver.findElement(LoginScreen.submitLoginButton).click();
        Pause.untilWithXPath(driver, HomePageScreen.spacecrafts);
    }
}