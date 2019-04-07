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
                        new Spacecraft("New spacecraft 1", "long spacecraft", new HashSet<CraftComponent>() {
                            {
                                add(new CraftComponent("Wings", true, null));
                            }
                        })

                },
                {
                        new Spacecraft("New spacecraft 2", "long spacecraft", new HashSet<CraftComponent>() {
                            {
                                add(new CraftComponent("Wings", true, null));
                            }
                        })
                },
        });
    }
}
