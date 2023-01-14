package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaExecutor {

    public static void highlightElement(WebDriver driver, WebElement element)
    {
        org.openqa.selenium.JavascriptExecutor js=(org.openqa.selenium.JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 0px;');", element);

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}