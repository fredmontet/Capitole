package mobop.capitole.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  Language model for the Capitole database
 */
public class Language extends RealmObject{

    @PrimaryKey
    private String language;

    // Methods
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
