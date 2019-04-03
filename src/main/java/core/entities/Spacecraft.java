package core.entities;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Spacecraft {

    //private Long id;
    private String name;
    private String type;
    private Set<CraftComponent> craftComponents;

}
