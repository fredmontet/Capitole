package mobop.capitole.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  Keyword model for the Capitole database
 */
public class Keyword extends RealmObject{

    @PrimaryKey
    private String keyword;

    // Methods
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
