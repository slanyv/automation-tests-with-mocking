package core.api_managers;


import java.util.Set;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import core.constants.GeneralContants;
import core.entities.CraftComponent;
import tools.UniqueTag;

public class CraftComponentAPIManager {

    public static void createComponent(CraftComponent craftComponent) {

        HttpResponse<String> response;
        craftComponent.setName(craftComponent.getName() + UniqueTag.generateString());

        try {
            response = Unirest.post(GeneralContants.REST_URL + "/craftComponents")
                    .basicAuth("admin@admin.com", "password")
                    .header("Content-Type", "application/json")
                    .body(craftComponent)
                    .asString();

            if (response.getStatus() != 200) {
                throw new RuntimeException("CraftComponent was not created successfully. \n" + response.getRawBody());
            }
        } catch (UnirestException e) {
            throw new RuntimeException("CraftComponent was not created successfully.", e);
        }
    }

    public static void createComponents(Set<CraftComponent> craftComponents) {
        for (CraftComponent craftComponent1 : craftComponents) {
            createComponent(craftComponent1);
        }
    }
}
