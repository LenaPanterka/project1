package pages;

import annotations.Path;
import data.EngMonth;
import data.RusMonth;
import data.RusMonthIn;
import helpers.JavaExecutor;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/")
public class MainPage extends AbsBasePage<MainPage>  {

    @FindBy(xpath = "//a[@href='tel:+7 499 938-92-02']")
    public WebElement phone;

    public MainPage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String convertListOfDatesForCoursesAndReturnNearestDate (ArrayList listOfCoursesByDateFull, String nearest) throws Exception{
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<LocalDate> localDateList = new ArrayList<>();
        String originalDate = null;

        for(int i = 0; i < listOfCoursesByDateFull.size(); i++){
            String fullDate = listOfCoursesByDateFull.get(i).toString();
            for(int a = 0; a < 12; a++){
                String month = RusMonth.values()[a].toString();
                String month2 = RusMonthIn.values()[a].toString();
                if(fullDate.contains(month)){
                    fullDate = fullDate.replaceAll(" "+month, "/");
                    fullDate = fullDate.concat(EngMonth.values()[a].toString());
                    fullDate = fullDate.replaceAll(" ", "");
                }

                listOfCoursesByDateFull.set(i, fullDate);

                if(fullDate.contains(month2)){
                    originalDate = fullDate.trim();
                    fullDate = "1/".concat(EngMonth.values()[a].toString());
                    listOfCoursesByDateFull.set(i, fullDate);
                }
            }

            //Convert to Date format
            LocalDate localDate = new SimpleDateFormat("dd/MMM").parse(listOfCoursesByDateFull.get(i).toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localDateList.add(localDate);
        }

        LocalDate min = localDateList.stream().reduce(localDateList.get(0), BinaryOperator.minBy(Comparator.nullsLast(Comparator.naturalOrder())));
        LocalDate max = localDateList.stream().reduce(localDateList.get(0), BinaryOperator.maxBy(Comparator.nullsLast(Comparator.naturalOrder())));

        //Get date equivalent from site
        String finalDate = null;
        int index = 0;
        int day = 0;
        String month = null;
        if(nearest == "min"){
            day = min.getDayOfMonth();
            month = min.getMonth().toString();
        }else{
           day = 01;
           month = max.getMonth().toString();
        }

        int indexEn =  EngMonth.valueOf(month).getOrder();
        month = RusMonth.values()[indexEn].toString();
        finalDate = day+ " " + month;
        try{
            driver.findElement(By.xpath("//div[@class='lessons__new-item-container']//*[contains(string(),'" + finalDate + "')]"));
            return finalDate;
        }catch (Exception e){
            //for format like: "В октябре..."
            month = RusMonthIn.values()[indexEn].toString();
            finalDate = originalDate;
            return finalDate;
        }
    }

    public ArrayList<String> finalListOfCoursesAfterModification (int amountOfPopularCourses, int amountOfCourses, String NeededXpath, boolean blockSpecialization){
        ArrayList<String> listOfCoursesByDate = new ArrayList<>();
        if(blockSpecialization) {
            amountOfCourses = driver.findElements(By.xpath(NeededXpath)).size() - amountOfPopularCourses;
        }else{
            amountOfCourses = driver.findElements(By.xpath(NeededXpath)).size();
        }
        String courseDate;
        for(int i = 0; i < amountOfCourses; i++){
            if(blockSpecialization){
                courseDate = driver.findElements(By.xpath(NeededXpath)).get(i+amountOfPopularCourses).getText();

            }else{
                courseDate = driver.findElements(By.xpath(NeededXpath)).get(i).getText();
            }

            if(!courseDate.contains("О дате старта будет объявлено позже")){
                courseDate = courseDate.replaceFirst("[С]","");
                courseDate = courseDate.trim();
                if(courseDate.contains("месяц") || courseDate.contains("месяца") || courseDate.contains("месяцев")){
                    courseDate = courseDate.replaceAll("\\d{1,2}\\s" + "(\\bмесяц).*","");
                }
                listOfCoursesByDate.add(courseDate);
            }
        }
        return listOfCoursesByDate;
    }

    public WebElement getNearestCours (String nearest) throws Exception {
        ArrayList<String> listOfCoursesByDate = new ArrayList<>();
        ArrayList<String> listOfCoursesByDateSpecialization = new ArrayList<>();
        ArrayList<String> listOfCoursesByDateFull = new ArrayList<>();

        //Get a list of popular courses
        int amountOfCourses = driver.findElements(By.xpath("//div[@class='lessons__new-item-container']//div[@class='lessons__new-item-start']")).size();
        listOfCoursesByDate = finalListOfCoursesAfterModification(0,amountOfCourses,"//div[@class='lessons__new-item-container']//div[@class='lessons__new-item-start']", false);

        listOfCoursesByDate.stream().sorted().collect(Collectors.toList());

        //Get a list of courses from Specialization
        int amountOfCoursesSecondGroup = driver.findElements(By.xpath("//div[@class='lessons__new-item-container']/div[contains (@class,'lessons__new-item-title')]")).size() - amountOfCourses;
        listOfCoursesByDateSpecialization = finalListOfCoursesAfterModification(amountOfCourses, amountOfCoursesSecondGroup,"//div[@class='lessons__new-item-container']//div[@class='lessons__new-item-time']", true);

        //Merge all courses
        listOfCoursesByDateFull = (ArrayList<String>) Stream.concat(listOfCoursesByDate.stream(), listOfCoursesByDateSpecialization.stream()).collect(Collectors.toList());

        String neededDate = convertListOfDatesForCoursesAndReturnNearestDate(listOfCoursesByDateFull, nearest);
        WebElement course = driver.findElement(By.xpath("//div[@class='lessons__new-item-container']//*[contains(string(),'" + neededDate + "')]"));

        Actions actions = new Actions(driver);
        JavaExecutor.highlightElement(driver, course);
        actions.click(course).release().build().perform();

        return course;
    }
}
