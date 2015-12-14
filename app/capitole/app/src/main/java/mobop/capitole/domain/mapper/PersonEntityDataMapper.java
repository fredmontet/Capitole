package mobop.capitole.data.entity.mapper;

import mobop.capitole.data.entity.PersonEntity;
import mobop.capitole.domain.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link PersonEntity} (in the data layer) to {@link Person} in the
 * domain layer.
 */
@Singleton
public class PersonEntityDataMapper {

    protected RoleEntityDataMapper roleEntityDataMapper;

    @Inject
    public PersonEntityDataMapper() {
        roleEntityDataMapper = new RoleEntityDataMapper();
    }

    /**
     * Transform a {@link PersonEntity} into an {@link Person}.
     *
     * @param personEntity Object to be transformed.
     * @return {@link Person} if valid {@link PersonEntity} otherwise null.
     */
    public Person transform(PersonEntity personEntity) {
        Person person = null;
        if (personEntity != null) {
            person = new Person(personEntity.getUuid());
            person.setFirstname(personEntity.getFirstname());
            person.setLastname(personEntity.getLastname());
            person.setGender(personEntity.getGender());
            person.setRoles(roleEntityDataMapper.transform(personEntity.getRoles()));
        }
        return person;
    }

    /**
     * Transform a Collection of {@link PersonEntity} into a Collection of {@link Person}.
     *
     * @param personEntityCollection Object Collection to be transformed.
     * @return {@link Person} if valid {@link PersonEntity} otherwise null.
     */
    public Collection<Person> transform(Collection<PersonEntity> personEntityCollection) {
        Collection<Person> personCollection = new ArrayList<>();
        Person person;
        for (PersonEntity personEntity : personEntityCollection) {
            person = transform(personEntity);
            if (person != null) {
                personCollection.add(person);
            }
        }

        return personCollection;
    }
}
