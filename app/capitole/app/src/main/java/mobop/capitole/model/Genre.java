package mobop.capitole.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Genre extends RealmObject{

    @PrimaryKey
    private String genre;

    // Methods
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
