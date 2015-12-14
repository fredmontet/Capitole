package mobop.capitole.data.entity.mapper;

import mobop.capitole.data.entity.GenreEntity;
import mobop.capitole.domain.Genre;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link GenreEntity} (in the data layer) to {@link Genre} in the
 * domain layer.
 */
@Singleton
public class GenreEntityDataMapper {

    @Inject
    public GenreEntityDataMapper() {}

    /**
     * Transform a {@link GenreEntity} into an {@link Genre}.
     *
     * @param genreEntity Object to be transformed.
     * @return {@link Genre} if valid {@link GenreEntity} otherwise null.
     */
    public Genre transform(GenreEntity genreEntity) {
        Genre genre = null;
        if (genreEntity != null) {
            genre = new Genre();
            genre.setGenre(genreEntity.getGenre());
        }
        return genre;
    }

    /**
     * Transform a Collection of {@link GenreEntity} into a Collection of {@link Genre}.
     *
     * @param genreEntityCollection Object Collection to be transformed.
     * @return {@link Genre} if valid {@link GenreEntity} otherwise null.
     */
    public Collection<Genre> transform(Collection<GenreEntity> genreEntityCollection) {
        Collection<Genre> genreCollection = new ArrayList<>();
        Genre genre;
        for (GenreEntity genreEntity : genreEntityCollection) {
            genre = transform(genreEntity);
            if (genre != null) {
                genreCollection.add(genre);
            }
        }

        return genreCollection;
    }
}
