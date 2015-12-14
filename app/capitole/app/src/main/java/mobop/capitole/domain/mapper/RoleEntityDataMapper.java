package mobop.capitole.data.entity.mapper;

import mobop.capitole.data.entity.RoleEntity;
import mobop.capitole.domain.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link RoleEntity} (in the data layer) to {@link Role} in the
 * domain layer.
 */
@Singleton
public class RoleEntityDataMapper {

    @Inject
    public RoleEntityDataMapper() {}

    /**
     * Transform a {@link RoleEntity} into an {@link Role}.
     *
     * @param roleEntity Object to be transformed.
     * @return {@link Role} if valid {@link RoleEntity} otherwise null.
     */
    public Role transform(RoleEntity roleEntity) {
        Role role = null;
        if (roleEntity != null) {
            role = new Role();
            role.setRole(roleEntity.getRole());
        }
        return role;
    }

    /**
     * Transform a Collection of {@link RoleEntity} into a Collection of {@link Role}.
     *
     * @param roleEntityCollection Object Collection to be transformed.
     * @return {@link Role} if valid {@link RoleEntity} otherwise null.
     */
    public Collection<Role> transform(Collection<RoleEntity> roleEntityCollection) {
        Collection<Role> roleCollection = new ArrayList<>();
        Role role;
        for (RoleEntity roleEntity : roleEntityCollection) {
            role = transform(roleEntity);
            if (role != null) {
                roleCollection.add(role);
            }
        }

        return roleCollection;
    }
}
