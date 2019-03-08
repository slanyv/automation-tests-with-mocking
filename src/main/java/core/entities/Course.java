package core.entities;

import core.enums.Language;
import core.enums.ProficiencyLevel;

public class Course {

    //TODO getter setter
    private Long id;
    private String name;
    private Language language;
    private ProficiencyLevel proficiencyLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }


    public Course(String name, Language language, ProficiencyLevel proficiencyLevel) {
        this.name = name;
        this.language = language;
        this.proficiencyLevel = proficiencyLevel;
    }
}
