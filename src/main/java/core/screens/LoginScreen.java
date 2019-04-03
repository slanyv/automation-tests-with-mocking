package core.screens;

import org.openqa.selenium.By;

public class LoginScreen {

    public static final By usernameInput = By.id("name");
    public static final By passwordInput = By.id("password");
    public static final By submitLoginButton = By.xpath("//*[@type =\"submit\"]");
}
