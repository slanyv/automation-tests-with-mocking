package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import core.Login;
import core.constants.GeneralContants;
import mocker.wiremock_server_example.WireMockServerExample;
import tools.object_mapper.ObjectMapperFactory;


public class MainTest {

    protected ChromeDriver driver;
    protected WireMockServerExample wireMockServerExample;

    @BeforeTest
    public void setUpConfiguration() throws IOException {

        ObjectMapperFactory objectMapperFactory = new ObjectMapperFactory();
        objectMapperFactory.create();

        wireMockServerExample = new WireMockServerExample();
        wireMockServerExample.startMockServer();

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

    @AfterTest
    public void afterTest(){
        WireMockServerExample.stopMockServer();
        driver.close();
        driver.quit();
    }
}
