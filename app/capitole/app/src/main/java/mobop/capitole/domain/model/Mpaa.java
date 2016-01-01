package mobop.capitole.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  MPAA model for the Capitole database
 */
public class Mpaa extends RealmObject{

    @PrimaryKey
    private String mpaa;

    // Methods
    public String getMpaa() {
        return mpaa;
    }

    public void setMpaa(String mpaa) {
        this.mpaa = mpaa;
    }
}
