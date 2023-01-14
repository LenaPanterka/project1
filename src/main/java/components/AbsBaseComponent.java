package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsBaseComponent <T>{

    protected WebDriver driver;

    public AbsBaseComponent (WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
}
