package mobop.capitole.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Role extends RealmObject{

    @PrimaryKey
    private String role;

    // Methods
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
