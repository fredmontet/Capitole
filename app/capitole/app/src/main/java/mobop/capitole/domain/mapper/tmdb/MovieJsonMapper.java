package mobop.capitole.domain.mapper.tmdb;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import mobop.capitole.domain.model.Country;
import mobop.capitole.domain.model.Genre;
import mobop.capitole.domain.model.Language;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.Mpaa;
import mobop.capitole.domain.model.Person;

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
     * @param response A json representing a movie.
     * @return {@link Movie}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<Movie> transform(JSONObject response) throws JsonSyntaxException, JSONException, ParseException {

        List<Movie> movieList = new ArrayList<>();
        JSONArray result = response.getJSONArray("results");

        for(int i = 0; i < result.length(); i++){

            Movie movie = new Movie();
            JSONObject JSONMovie = result.getJSONObject(i);

            movie.setTitle(JSONMovie.getString("original_title"));

            String dateStr = JSONMovie.getString("release_date");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateStr);
            movie.setReleaseDate(date);

            GenreJsonMapper genreJsonMapper = new GenreJsonMapper();
            RealmList<Genre> genres = genreJsonMapper.transform(JSONMovie.getJSONArray("genre_ids"));
            movie.setGenres(genres);

            movie.setSynopsis(JSONMovie.getString("overview"));

            LanguageJsonMapper languageJsonMapper = new LanguageJsonMapper();
            RealmList<Language> languages = languageJsonMapper.transform(JSONMovie.getString("original_language"));
            movie.setLanguages(languages);

            movie.setPoster(JSONMovie.getString("poster_path"));
            movie.setTmdbID(JSONMovie.getString("id"));

            movieList.add(movie);

        }

        return movieList;
    }
}