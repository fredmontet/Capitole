package mobop.capitole.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  Country model for the Capitole database
 */
public class Country extends RealmObject {

    @PrimaryKey
    private String country;

    // Methods
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
