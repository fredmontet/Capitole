package mobop.capitole.domain.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mobop.capitole.domain.model.Person;
import mobop.capitole.domain.model.Role;

/**
 * Created by fredmontet on 17/12/15.
 */
public class PersonJsonMapper {

    public Collection<Person> transform(String peopleStr, String roleStr){
        Collection<Person> peopleColl = new ArrayList<>();
        List<String> peopleList = Arrays.asList(peopleStr.split(", "));
        Person person;
        for(String personStr : peopleList){

            Role role = new Role();
            role.setRole(roleStr);

            person = new Person();
            person.setName(personStr);
            person.getRoles().add(role);

            peopleColl.add(person);
        }
        return peopleColl;
    }
}