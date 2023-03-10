package helpers;


import exceptions.BrowserNotSupprotMineException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
public class WebDriverFactory extends BrowserNotSupprotMineException {
        static WebDriver webDriver = null;

    public WebDriverFactory(String s) {
        super(s);
    }

    public static WebDriver createDriver(String browser){
        switch (browser) {
            case "firefox": webDriver = WebDriverManager.firefoxdriver().create();
                break;
            case "opera": webDriver = WebDriverManager.operadriver().create();
                break;
            case "chrome": webDriver = WebDriverManager.chromedriver().create();
                break;
        }
        return webDriver;

//            if (BrowserType.CHROME.equalsIgnoreCase(browser)) {
//                webDriver = WebDriverManager.chromedriver().create();
//            } else if (BrowserType.FIREFOX.equalsIgnoreCase(browser)) {
//                webDriver = WebDriverManager.firefoxdriver().create();
//            } else if (BrowserType.OPERA_BLINK.equalsIgnoreCase(browser)) {
//                webDriver = WebDriverManager.operadriver().create();
//            }
//            return webDriver;
        }
}