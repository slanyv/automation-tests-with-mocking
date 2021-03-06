package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import core.Login;
import core.constants.GeneralContants;
import mocker.mock_server_client_example.MockServerClientExample;
import mocker.wiremock_server_example.WireMockServerExample;
import tools.Configuration;
import tools.object_mapper.ObjectMapperFactory;


public class MainTest {

    protected ChromeDriver driver;
    private Configuration configuration;

    @BeforeTest
    public void setUpConfiguration() throws IOException {

        configuration = new Configuration();
        configuration.initialize();

        if (configuration.isUseWireMockServer()) {
            WireMockServerExample.startMockServer();
        }

        MockServerClientExample.initialize();
        if (configuration.isUseMockServerClient()) {
            MockServerClientExample.startMockServer();
        }

        ObjectMapperFactory objectMapperFactory = new ObjectMapperFactory();
        objectMapperFactory.create();

        //set up chrome driver
        System.setProperty(GeneralContants.CHROME_DRIVER_PROPERTY, GeneralContants.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //set up properties
        String username;
        String password;
        Properties properties = new Properties();

        try (InputStream fis = MainTest.class.getClassLoader().getResourceAsStream(GeneralContants.PROPERTIES_FILE_NAME)) {
            properties.load(fis);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        }

        //login
        Login login = new Login(driver);
        login.login(username, password);
    }

    protected boolean isUsingAnyMockServer() {
        return (WireMockServerExample.isServerRunning() || MockServerClientExample.isServerRunning() || configuration.isUseExternalMockServer());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {

        if (WireMockServerExample.isServerRunning()) {
            WireMockServerExample.stopMockServer();
        }
        if (MockServerClientExample.isServerRunning()) {
            MockServerClientExample.stopMockServer();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        driver.close();
        driver.quit();
    }
}
