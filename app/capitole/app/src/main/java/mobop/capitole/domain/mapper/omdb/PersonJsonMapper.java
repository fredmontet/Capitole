package mobop.capitole.domain.mapper.omdb;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import mobop.capitole.domain.model.Person;
import mobop.capitole.domain.model.Role;

/**
 * Created by fredmontet on 17/12/15.
 */
public class PersonJsonMapper {

    public RealmList<Person> transform(String peopleStr, String roleStr){
        RealmList<Person> peopleList = new RealmList<>();
        RealmList<Role> rolesList = new RealmList<>();
        List<String> peopleStrList = Arrays.asList(peopleStr.split(", "));
        Person person;
        for(String personStr : peopleStrList){

            Role role = new Role();
            role.setRole(roleStr);
            rolesList.add(role);

            person = new Person();
            person.setName(personStr);
            person.setRoles(rolesList);

            peopleList.add(person);
        }
        return peopleList;
    }
}