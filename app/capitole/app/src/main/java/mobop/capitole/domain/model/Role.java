package mobop.capitole.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  Role model for the Capitole database
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
