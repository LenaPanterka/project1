package components;

import data.CategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CategoryPage;
public class MenuComponent extends AbsBaseComponent <MenuComponent>{

    public MenuComponent(WebDriver driver){
        super(driver);
    }

    private final String menuItemByTitleSelectorTemplate = "#categories_id a[title='%s']";

    public CategoryPage clickCategory(CategoryData categoryData){
        String selector = String.format(menuItemByTitleSelectorTemplate, categoryData.getName());

        driver.findElement(By.cssSelector(selector)).click();

        return new CategoryPage(driver);
    }
}
