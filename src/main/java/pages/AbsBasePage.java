package pages;

import annotations.Path;
import exceptions.PathEmptyException;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage<T> {
    protected WebDriver driver;

    private String baseUrl = System.getProperty("webdriver.base.url");

    public AbsBasePage(WebDriver driver){
        this.driver = driver;
    }

    private String getPath() throws PathEmptyException{
        Class clazz = this.getClass();

        if(clazz.isAnnotationPresent(Path.class)){
            Path path = (Path)clazz.getAnnotation(Path.class);
            return path.value();
        }
        throw new PathEmptyException();
    }

    public T open() throws PathEmptyException {
        driver.get(baseUrl + getPath());
        return (T)this;
    }
}
