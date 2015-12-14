package mobop.capitole.data.entity.mapper;

import mobop.capitole.data.entity.MovieEntity;
import mobop.capitole.domain.Movie;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link MovieEntity} (in the data layer) to {@link Movie} in the
 * domain layer.
 */
@Singleton
public class MovieEntityDataMapper {

  private PersonEntityDataMapper personEntityDataMapper;
  private LanguageEntityDataMapper languageEntityDataMapper;
  private GenreEntityDataMapper genreEntityDataMapper;
  private CountryEntityDataMapper countryEntityDataMapper;

  @Inject
  public MovieEntityDataMapper() {
    personEntityDataMapper = new PersonEntityDataMapper();
    languageEntityDataMapper = new LanguageEntityDataMapper();
    genreEntityDataMapper = new GenreEntityDataMapper();
    countryEntityDataMapper = new CountryEntityDataMapper();
  }

  /**
   * Transform a {@link MovieEntity} into an {@link Movie}.
   *
   * @param movieEntity Object to be transformed.
   * @return {@link Movie} if valid {@link MovieEntity} otherwise null.
   */
  public Movie transform(MovieEntity movieEntity) {
    Movie movie = null;
    if (movieEntity != null) {
      movie = new Movie(movieEntity.getUuid());
      movie.setTitle(movieEntity.getTitle());
      movie.setTagline(movieEntity.getTagline());
      movie.setSynopsis(movieEntity.getSynopsis());
      movie.setLength(movieEntity.getLength());
      movie.setTrailerlink(movieEntity.getTrailerlink());
      movie.setWebsite(movieEntity.getWebsite());
      movie.setReleaseDate(movieEntity.getReleaseDate());
      movie.setBudget(movieEntity.getBudget());
      movie.setPeople(personEntityDataMapper.transform(movieEntity.getPeople()));
      movie.setLanguages(languageEntityDataMapper.transform(movieEntity.getLanguages()));
      movie.setGenres(genreEntityDataMapper.transform(movieEntity.getGenres()));
      movie.setCountry(countryEntityDataMapper.transform(movieEntity.getCountry()));
      movie.setMpaa(movieEntity.getMpaa().toString());
    }

    return movie;
  }

  /**
   * Transform a Collection of {@link MovieEntity} into a Collection of {@link Movie}.
   *
   * @param movieEntityCollection Object Collection to be transformed.
   * @return {@link Movie} if valid {@link MovieEntity} otherwise null.
   */
  public Collection<Movie> transform(Collection<MovieEntity> movieEntityCollection) {
    Collection<Movie> movieCollection = new ArrayList<>();
    Movie movie;
    for (MovieEntity movieEntity : movieEntityCollection) {
      movie = transform(movieEntity);
      if (movie != null) {
        movieCollection.add(movie);
      }
    }

    return movieCollection;
  }
}
