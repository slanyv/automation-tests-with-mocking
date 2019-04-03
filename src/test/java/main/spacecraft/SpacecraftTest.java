package main.spacecraft;

import org.testng.annotations.Test;

import core.api_managers.CraftComponentAPIManager;
import core.entities.Spacecraft;
import core.managers.SpacecraftManager;
import main.MainTest;
import providers.SpacecraftProvider;
import tools.UniqueTag;

public class SpacecraftTest extends MainTest {

    @Test(dataProvider = "AddNewSpacecraftProvider", dataProviderClass = SpacecraftProvider.class, description = "Create new spacecraft.")
    public void addSpacecraft(Spacecraft spacecraft) {

        SpacecraftManager spacecraftManager = new SpacecraftManager(this.driver);
        CraftComponentAPIManager.createComponents(spacecraft.getCraftComponents());

        spacecraft.setName(spacecraft.getName() + UniqueTag.generateString());
        spacecraftManager.open();
        spacecraftManager.fill(spacecraft);
        spacecraftManager.save();
        spacecraftManager.check(spacecraft);
    }
}
