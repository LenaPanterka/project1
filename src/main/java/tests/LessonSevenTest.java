package tests;

import org.junit.Test;
import pages.MainPage;

public class LessonSevenTest extends BaseTest {
    @Test
    public void selectCourse() throws Exception {

        MainPage mainPage = new MainPage(driver);

        for(int i = 0; i < 2; i++){
            mainPage.open();
            if(i == 0){
                //nearest course
                mainPage.getNearestCours("min");
            }else{
                //latest course
                mainPage.getNearestCours("max");
            }
        }
    }
}
