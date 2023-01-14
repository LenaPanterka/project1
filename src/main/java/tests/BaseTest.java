package tests;

import exceptions.NoDriverException;
import helpers.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest extends NoDriverException{
    protected static WebDriver driver;

    String browser = System.getProperty("browser");

    @Before
    public void startUp(){
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @After
    public void cleanUp(){
        try{
            driver.quit();
        }catch (Exception e){}
    }
}
