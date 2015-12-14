package mobop.capitole.data.entity.mapper;

import mobop.capitole.data.entity.MpaaEntity;
import mobop.capitole.domain.Mpaa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link MpaaEntity} (in the data layer) to {@link Mpaa} in the
 * domain layer.
 */
@Singleton
public class MpaaEntityDataMapper {

    @Inject
    public MpaaEntityDataMapper() {}

    /**
     * Transform a {@link MpaaEntity} into an {@link Mpaa}.
     *
     * @param mpaaEntity Object to be transformed.
     * @return {@link Mpaa} if valid {@link MpaaEntity} otherwise null.
     */
    public Mpaa transform(MpaaEntity mpaaEntity) {
        Mpaa mpaa = null;
        if (mpaaEntity != null) {
            mpaa = new Mpaa();
            mpaa.setMpaa(mpaaEntity.getMpaa());
        }
        return mpaa;
    }

    /**
     * Transform a Collection of {@link MpaaEntity} into a Collection of {@link Mpaa}.
     *
     * @param mpaaEntityCollection Object Collection to be transformed.
     * @return {@link Mpaa} if valid {@link MpaaEntity} otherwise null.
     */
    public Collection<Mpaa> transform(Collection<MpaaEntity> mpaaEntityCollection) {
        Collection<Mpaa> mpaaCollection = new ArrayList<>();
        Mpaa mpaa;
        for (MpaaEntity mpaaEntity : mpaaEntityCollection) {
            mpaa = transform(mpaaEntity);
            if (mpaa != null) {
                mpaaCollection.add(mpaa);
            }
        }

        return mpaaCollection;
    }
}
