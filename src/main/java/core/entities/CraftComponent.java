package core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CraftComponent {

    private String name;
    private String readyDate;
    private boolean readyToUse;

    public CraftComponent(String name, boolean readyToUse, String readyDate) {
        this.name = name;
        this.readyDate = readyDate;
        this.readyToUse = readyToUse;
    }
}
