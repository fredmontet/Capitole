package mobop.capitole.domain.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
      public void onSuccess(JSONObject response) {
        callback.onSuccess(response);
      }
    });
  }

  public void getMovieById(String id, String source, final ApiQueryCallback callback) throws MalformedURLException {
    String apiUrl = tmdbApi.getUrlMovieById(id, source);
    ApiConnection api = new ApiConnection(apiUrl, this.context);
    api.getJson(new ApiConnection.VolleyCallback() {
      @Override
      public void onSuccess(JSONObject response) {
        callback.onSuccess(response);
      }
    });
  }

  public void getMovieSuggestion(final ApiQueryCallback callback) throws  MalformedURLException{
    String apiUrl = tmdbApi.getUrlDiscoverMovies();
    ApiConnection api = new ApiConnection(apiUrl, this.context);
    api.getJson(new ApiConnection.VolleyCallback(){
      @Override
      public void onSuccess(JSONObject response) {
        callback.onSuccess(response);
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
    void onSuccess(JSONObject jsonObject);
  }
}
