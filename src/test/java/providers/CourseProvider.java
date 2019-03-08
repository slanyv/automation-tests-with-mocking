package providers;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import core.entities.Course;
import core.enums.Language;
import core.enums.ProficiencyLevel;

public class CourseProvider {

    @DataProvider
    public Object[][] AddNewCourseProvider(ITestContext ctx) {
        return CommonProvider.returnDesiredRows(ctx, new Object[][]{
                {
                        new Course("New English Course", Language.ENGLISH, ProficiencyLevel.A2)
                },
                {
                        new Course("New Deutsh Course", Language.GERMAN, ProficiencyLevel.B1)
                },
        });
    }
}
