package mobop.capitole.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Genre extends RealmObject{

    @PrimaryKey
    private String id;
    private String genre;

    // Methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
