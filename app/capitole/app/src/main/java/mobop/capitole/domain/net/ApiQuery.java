package mobop.capitole.domain.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

  public void getMovieSuggestion(final ApiQueryCallback callback) throws  MalformedURLException{
    String apiUrl = tmdbApi.getUrlDiscoverMovies();
    ApiConnection api = new ApiConnection(apiUrl, this.context);
    api.getJson(new ApiConnection.VolleyCallback(){
      @Override
      public void onSuccess(JSONObject response) throws JSONException {
        callback.onSuccess(response.getJSONArray("results"));
      }
    });
  }

  /**
   * Checks if the device has any active internet connection.
   *
   * @return true device with internet connection, otherwise false.
   */
  private boolean isThereInternetConnection() {
    boolean isConnected;

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }

  /**
   * Simple callback interface
   */
  public interface ApiQueryCallback {
    void onSuccess(JSONArray jsonArray);
  }
}
