package core.screens;

import org.openqa.selenium.By;

public class SpacecraftsScreen {

    public static final By newSpacecraft = By.xpath("//*[contains(text(),\"New spacecraft\")]");
    public static final By spacecraftName = By.id("name");
    public static final By spacecraftType = By.id("type");
    public static final By saveSpacecraft = By.xpath("//*[@type=\"submit\"]");
    public static final By closeAlert = By.xpath("//*[contains(@class,\"alert-success\")]//*[contains(@class,\"close\")]");
    public static final By spacecraftComponentsWindow = By.xpath("//*[contains(text(),\"Craft components for\")]");

    public static By spacecraftCraftComponent(String name) {
        return By.xpath("//*[contains(text(),\"" + name + "\")]");
    }

    public static By spacecraftCraftRow(String name) {
        return By.xpath("//*[contains(text(),\"" + name + "\")]/..");
    }
}
