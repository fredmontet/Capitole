package mobop.capitole.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

import mobop.capitole.data.entity.MovieEntity;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class MovieEntityJsonMapper {

  private final Gson gson;

  @Inject
  public MovieEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link MovieEntity}.
   *
   * @param movieJsonResponse A json representing a movie profile.
   * @return {@link MovieEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public MovieEntity transformMovieEntity(String movieJsonResponse) throws JsonSyntaxException {
    try {
      Type movieEntityType = new TypeToken<MovieEntity>() {}.getType();
      MovieEntity movieEntity = this.gson.fromJson(movieJsonResponse, movieEntityType);

      return movieEntity;
    } catch (JsonSyntaxException jsonException) {
      throw jsonException;
    }
  }

  /**
   * Transform from valid json string to List of {@link MovieEntity}.
   *
   * @param movieListJsonResponse A json representing a collection of movies.
   * @return List of {@link MovieEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public List<MovieEntity> transformMovieEntityCollection(String movieListJsonResponse)
      throws JsonSyntaxException {

    List<MovieEntity> movieEntityCollection;
    try {
      Type listOfMovieEntityType = new TypeToken<List<MovieEntity>>() {}.getType();
      movieEntityCollection = this.gson.fromJson(movieListJsonResponse, listOfMovieEntityType);

      return movieEntityCollection;
    } catch (JsonSyntaxException jsonException) {
      throw jsonException;
    }
  }
}
