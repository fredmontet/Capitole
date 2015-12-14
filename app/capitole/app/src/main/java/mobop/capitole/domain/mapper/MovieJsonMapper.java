package mobop.capitole.domain.mapper;

import mobop.capitole.domain.model.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class MovieJsonMapper {

  private final Gson gson;
  
  public MovieJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link Movie}.
   *
   * @param movieJsonResponse A json representing a movie.
   * @return {@link Movie}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public Movie transformMovie(String movieJsonResponse) throws JsonSyntaxException {
    try {
      Type movieType = new TypeToken<Movie>() {}.getType();
      Movie movie = this.gson.fromJson(movieJsonResponse, movieType);

      return movie;
    } catch (JsonSyntaxException jsonException) {
      throw jsonException;
    }
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
