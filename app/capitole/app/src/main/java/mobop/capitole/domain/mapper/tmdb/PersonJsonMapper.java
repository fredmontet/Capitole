package mobop.capitole.domain.mapper.tmdb;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import mobop.capitole.domain.model.Person;
import mobop.capitole.domain.model.Role;

public class PersonJsonMapper {

    /**
     * Transform a list of person with a same role
     * in a RealmList of Person with this role.
     * @param peopleStr the list of person in the form: "name1, name2, ..., nameN"
     * @param roleStr the role to attribute to each person
     * @return the peopleList RealmList<Person>
     */
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