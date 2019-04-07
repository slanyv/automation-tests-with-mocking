package providers;

import java.util.HashSet;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import core.entities.CraftComponent;
import core.entities.Spacecraft;

public class SpacecraftProvider {

    @DataProvider
    public Object[][] AddNewSpacecraftProvider(ITestContext ctx) {
        return CommonProvider.returnDesiredRows(ctx, new Object[][]{
                {
                        new Spacecraft("Hello there...", "General Kenobi", new HashSet<CraftComponent>() {
                            {
                                add(new CraftComponent("Wheels", true, null));
                            }
                        })

                },
                {
                        new Spacecraft("Hello there...", "General Kenobi", new HashSet<CraftComponent>() {
                            {
                                add(new CraftComponent("Wheels", true, null));
                            }
                        })
                },
        });
    }
}
