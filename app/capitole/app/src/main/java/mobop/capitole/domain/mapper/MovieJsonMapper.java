package mobop.capitole.domain.mapper;

import mobop.capitole.domain.model.Country;
import mobop.capitole.domain.model.Genre;
import mobop.capitole.domain.model.Language;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.Mpaa;
import mobop.capitole.domain.model.Person;
import mobop.capitole.domain.model.Role;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class MovieJsonMapper {

  private final Gson gson;
  
  public MovieJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string coming from
   * Omdb API to {@link Movie}.
   *
   * @param movieJsonResponse A json representing a movie.
   * @return {@link Movie}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public Movie transform(String movieJsonResponse) throws JsonSyntaxException {

      Type type = new TypeToken<Map<String, String>>(){}.getType();
      Map<String, String> movieMap = gson.fromJson(movieJsonResponse, type);
      Movie movie = new Movie();

      movie.setTitle(movieMap.get("Title"));
      movie.setYear(new Date(movieMap.get("Year")));

      Mpaa mpaa = new Mpaa();
      mpaa.setMpaa(movieMap.get("Rated"));
      movie.setMpaa(mpaa);

      movie.setReleaseDate(new Date(movieMap.get("Released")));

      GenreJsonMapper genreJsonMapper = new GenreJsonMapper();
      Collection<Genre> genres = genreJsonMapper.transform(movieMap.get("Genre"));
      for (Genre genre : genres){
          movie.getGenres().add(genre);
      }

      PersonJsonMapper personJsonMapper = new PersonJsonMapper();
      Collection<Person> people = personJsonMapper.transform(movieMap.get("Director"), "Director");
      for(Person person : people){
          movie.getPeople().add(person);
      }

      people = personJsonMapper.transform(movieMap.get("Writer"), "Writer");
      for( Person person : people){
          movie.getPeople().add(person);
      }

      people = personJsonMapper.transform(movieMap.get("Actors"), "Actors");
      for (Person person : people){
          movie.getPeople().add(person);
      }

      movie.setSynopsis(movieMap.get("Plot"));

      LanguageJsonMapper languageJsonMapper = new LanguageJsonMapper();
      Collection<Language> languages = languageJsonMapper.transform(movieMap.get("Language"));
      for(Language language : languages ){
          movie.getLanguages().add(language);
      }

      Country country = new Country();
      country.setCountry(movieMap.get("Country"));
      movie.setCountry(country);

      movie.setAwards(movieMap.get("Awards"));
      movie.setPoster(movieMap.get("Poster"));
      movie.setMetascore(movieMap.get("Metascore"));
      movie.setImdbID(movieMap.get("imdbID"));
      movie.setType(movieMap.get("Type"));

      return movie;
  }

  /**
   * Transform from valid json string to List of {@link Movie}.
   *
   * @param movieCollectionJsonResponse A json representing a collection of users.
   * @return List of {@link Movie}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public Collection<Movie> transformMovieCollection(String movieCollectionJsonResponse)
      throws JsonSyntaxException {

    Collection<Movie> movieCollection;
    try {
      Type listOfMovieType = new TypeToken<Collection<Movie>>() {}.getType();
      movieCollection = this.gson.fromJson(movieCollectionJsonResponse, listOfMovieType);

      return movieCollection;
    } catch (JsonSyntaxException jsonException) {
      throw jsonException;
    }
  }
}
