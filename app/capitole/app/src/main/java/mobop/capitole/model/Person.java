package mobop.capitole.model;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Person extends RealmObject{

    @PrimaryKey
    private String uuid;
    private String firstname;
    private String lastname;
    private String gender;

    // Many to many
    private RealmList<Role> roles;

    // Methods
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RealmList<Role> getRoles() {
        return roles;
    }

    public void setRoles(RealmList<Role> roles) {
        this.roles = roles;
    }
}
