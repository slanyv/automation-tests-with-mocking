package core.managers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.openqa.selenium.chrome.ChromeDriver;

import core.constants.GeneralContants;
import core.entities.Course;
import core.screens.CoursesScreen;
import core.screens.HomePageScreen;
import tools.Navigate;
import tools.Pause;


public class CourseManager {

    private ChromeDriver driver;

    public CourseManager(ChromeDriver driver) {
        this.driver = driver;
    }

    public void open() {

        Navigate.to(driver, GeneralContants.HOME_PAGE_URL);
        driver.findElement(HomePageScreen.listOfCourses).click();
        Pause.untilWithXPath(driver, CoursesScreen.newCourse);
        driver.findElement(CoursesScreen.newCourse).click();
        Pause.untilWithXPath(driver, CoursesScreen.createCourse);
    }

    public void fill(Course course) {

        driver.findElement(CoursesScreen.nameInput).sendKeys(course.getName());
        driver.findElement(CoursesScreen.languageSelect).sendKeys(course.getLanguage().name());
        driver.findElement(CoursesScreen.proficiencyLevelSelect).sendKeys(course.getProficiencyLevel().name());
    }

    public void save(Course course) {

        driver.findElement(CoursesScreen.createCourse).click();
        course.setId(Long.parseLong(driver.findElement(CoursesScreen.courseID).getText()));
    }

    public void checkInView(Course course) {

        assertThat("Course id mismatch after creation " + course,
                driver.findElement(CoursesScreen.courseID).getText(), is(course.getId().toString()));
        assertThat("Course name mismatch after creation " + course,
                driver.findElement(CoursesScreen.courseName).getText(), is(course.getName()));
        assertThat("Course language mismatch after creation " + course,
                driver.findElement(CoursesScreen.courseLanguage).getText(), is(course.getLanguage().name()));
        assertThat("Course proficiency level mismatch after creation " + course,
                driver.findElement(CoursesScreen.courseProficiencyLevel).getText(), is(course.getProficiencyLevel().name()));
    }

    public void checkInCoursesList(Course course) {

        Navigate.to(driver, GeneralContants.LIST_OF_COURSES_URL);
    }
}
