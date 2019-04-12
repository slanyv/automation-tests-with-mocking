package core.managers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.chrome.ChromeDriver;

import core.constants.GeneralContants;
import core.entities.CraftComponent;
import core.entities.Spacecraft;
import core.screens.HomePageScreen;
import core.screens.SpacecraftsScreen;
import tools.Navigate;
import tools.Pause;

public class SpacecraftManager {


    private ChromeDriver driver;

    public SpacecraftManager(ChromeDriver driver) {
        this.driver = driver;
    }

    public void open() {

        Navigate.to(driver, GeneralContants.HOME_PAGE_URL);
        driver.findElement(HomePageScreen.spacecrafts).click();
        Pause.untilWithXPath(driver, SpacecraftsScreen.newSpacecraft);
        driver.findElement(SpacecraftsScreen.newSpacecraft).click();
        Pause.untilWithXPath(driver, SpacecraftsScreen.spacecraftName);
    }

    public void fill(Spacecraft spacecraft) {

        driver.findElement(SpacecraftsScreen.spacecraftName).sendKeys(spacecraft.getName());
        driver.findElement(SpacecraftsScreen.spacecraftType).sendKeys(spacecraft.getType());
        for (CraftComponent craftComponent : spacecraft.getCraftComponents()) {
            driver.findElement(SpacecraftsScreen.spacecraftCraftComponent(craftComponent.getName())).click();
        }
    }

    public void save() {

        driver.findElement(SpacecraftsScreen.saveSpacecraft).click();
        Pause.untilWithXPath(driver, SpacecraftsScreen.newSpacecraft);
    }

    public void check(Spacecraft spacecraft) {

        //TODO
        if (driver.findElement(SpacecraftsScreen.closeAlert).isDisplayed()) {
            driver.findElement(SpacecraftsScreen.closeAlert).click();
        }
        driver.findElement(SpacecraftsScreen.spacecraftCraftRow(spacecraft.getName())).click();
        Pause.untilWithElement(driver, 3 ,driver.findElement(SpacecraftsScreen.spacecraftCraftComponent(spacecraft.getCraftComponents().iterator().next().getName())));
        for (CraftComponent craftComponent : spacecraft.getCraftComponents()) {
            assertThat("Spacecraft component name mismatch after creation. spacecraft: " + spacecraft,
                    driver.findElement(SpacecraftsScreen.spacecraftCraftComponent(craftComponent.getName())).getText(), is(craftComponent.getName()));
            /*
            assertThat("Spacecraft component ready to use mismatch after creation. spacecraft: " + spacecraft,
                    driver.findElement(SpacecraftsScreen.spacecraftCraftComponent(craftComponent.getName())).getText(), is(craftComponent.getName()));*/
        }
    }
}
