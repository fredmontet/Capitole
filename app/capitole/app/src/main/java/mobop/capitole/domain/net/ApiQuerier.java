package mobop.capitole.domain.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.net.MalformedURLException;

import mobop.capitole.domain.mapper.OmdbMapper;

/**
 * Class for retrieving data from the network.
 */
public class ApiQuerier{

  private final Context context;
  private final OmdbMapper omdbMapper;
  private final OmdbApi omdbApi;

  /**
   * Constructor of the class
   *
   * @param context {@link android.content.Context}.
   */
  public ApiQuerier(Context context) {
    if (context == null) {
      throw new IllegalArgumentException("The constructor parameters cannot be null!");
    }
    this.context = context.getApplicationContext();
    this.omdbMapper = new OmdbMapper();
    this.omdbApi = new OmdbApi();
  }

  public String getMoviesByTitle(String title, String year, String plot) throws MalformedURLException {
    String resType = "json";
    String apiUrl = omdbApi.getUrlMoviesByTitle(title, year, plot, resType);
    return ApiConnection.createGET(apiUrl).requestSyncCall();
  }

  public String getMovieById(String id, String plot) throws MalformedURLException {
    String resType = "json";
    String apiUrl = omdbApi.getUrlMovieById(id, plot, resType);
    return ApiConnection.createGET(apiUrl).requestSyncCall();
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
}
