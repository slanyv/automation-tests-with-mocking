package main.course;

import org.testng.annotations.Test;

import core.entities.Course;
import core.managers.CourseManager;
import main.MainTest;
import providers.CourseProvider;
import tools.UniqueTag;

public class CourseTest extends MainTest {

    @Test(dataProvider = "AddNewCourseProvider", dataProviderClass = CourseProvider.class, description = "Create new course.")
    public void addCourse(Course course) {

        CourseManager courseManager = new CourseManager(driver);

        //set up unique name
        course.setName(course.getName() + UniqueTag.generateString());

        courseManager.open();
        courseManager.fill(course);
        courseManager.save(course);
        courseManager.checkInView(course);
        courseManager.checkInCoursesList(course);
    }
}
