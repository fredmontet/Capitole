package mobop.capitole.domain.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  Person model for the Capitole database
 */
public class Person extends RealmObject{

    @PrimaryKey
    private String uuid;
    private String name;

    // Many to many
    private RealmList<Role> roles;

    // Constructor
    public Person(){
        this.roles = new RealmList<>();
    }

    // Methods
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Role> getRoles() {
        return roles;
    }

    public void setRoles(RealmList<Role> roles) {
        this.roles = roles;
    }
}
