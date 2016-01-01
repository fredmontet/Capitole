package mobop.capitole.domain.net;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import mobop.capitole.domain.mapper.omdb.MovieJsonMapper;

/**
 * Class for retrieving data from the network.
 */
public class ApiQuery {

    private final Context context;
    private final MovieJsonMapper movieJsonMapper;
    private final TmdbApi tmdbApi;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     */
    public ApiQuery(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }
        this.context = context.getApplicationContext();
        this.movieJsonMapper = new MovieJsonMapper();
        this.tmdbApi = new TmdbApi();
    }

    /**
     * Get a json of movies in a callback when given the title (or a part)
     * of the title of a movie.
     *
     * @param title    The title to query with
     * @param callback The callback containing the json movies
     * @throws MalformedURLException
     */
    public void getMoviesByTitle(String title, final ApiQueryCallback callback) throws MalformedURLException {
        String apiUrl = tmdbApi.getUrlMoviesByTitle(title);
        ApiConnection api = new ApiConnection(apiUrl, this.context);
        api.getJson(new ApiConnection.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                callback.onSuccess(response.getJSONArray("results"));
            }
        });
    }

    /**
     * Get the json of a movie by its ID
     *
     * @param id       The TMDB ID to give
     * @param callback The callback containing the json of the movie
     * @throws MalformedURLException
     */
    public void getMovieById(String id, final ApiQueryCallback callback) throws MalformedURLException {
        String apiUrl = tmdbApi.getUrlMovieById(id);
        ApiConnection api = new ApiConnection(apiUrl, this.context);
        api.getJson(new ApiConnection.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                JSONArray array = new JSONArray();
                array.put(response);
                callback.onSuccess(array);
            }
        });
    }

    /**
     * Get a list of suggested movies to watch
     *
     * @param callback The callback containing the json of movies
     * @throws MalformedURLException
     */
    public void getMovieSuggestion(final ApiQueryCallback callback) throws MalformedURLException {
        String apiUrl = tmdbApi.getUrlDiscoverMovies();
        ApiConnection api = new ApiConnection(apiUrl, this.context);
        api.getJson(new ApiConnection.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                callback.onSuccess(response.getJSONArray("results"));
            }
        });
    }

    /**
     * A simple interface to make a success callback
     */
    public interface ApiQueryCallback {
        void onSuccess(JSONArray jsonArray);
    }
}
