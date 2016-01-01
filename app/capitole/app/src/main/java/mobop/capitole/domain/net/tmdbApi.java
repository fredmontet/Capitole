package mobop.capitole.domain.net;

import android.net.Uri;

/**
 * Created by fredmontet on 20/12/15.
 */
public class TmdbApi {

    private final String API_SEARCH_BASE_URL = "http://api.themoviedb.org/3/search/movie?";
    private final String API_FIND_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private final String API_DISCOVER_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
    private final String API_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    private final String API_KEY_PARAM = "api_key";
    // Please don't touch this API Key
    private final String API_KEY_VALUE = "a47e4f6e9fc5d19bf5bebd8596188dc4";

    /**
     * Get the URL to get some movies by their title
     *
     * @param title the title of the film to get
     * @return A string representing the URL
     */
    public String getUrlMoviesByTitle(String title) {

        Uri builtUri = Uri.parse(API_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter("query", title)
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();

        return builtUri.toString();
    }

    /**
     * Get the URL to get a movie by its ID
     *
     * @param id the TMDB id
     * @return A string representing the URL
     */
    public String getUrlMovieById(String id) {

        Uri builtUri = Uri.parse(API_FIND_BASE_URL).buildUpon()
                .appendPath(id + "?")
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();

        return builtUri.toString();
    }

    /**
     * Get the URL to get a list of movie suggestion
     *
     * @return A string representing the URL
     */
    public String getUrlDiscoverMovies() {

        Uri builtUri = Uri.parse(API_DISCOVER_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();

        return builtUri.toString();
    }

    /**
     * Get the url of the poster for a designated size
     *
     * @param path The path of the poster
     * @return A string representing the URL
     */
    public String getUrlPoster(String path) {

        // Available size: w500, w600, w1000, w1920
        String size = "w500";

        // Just because they put a "/" as first char in the poster URL...
        path = path.startsWith("/") ? path.substring(1) : path;

        Uri builtUri = Uri.parse(API_IMAGE_BASE_URL).buildUpon()
                .appendPath(size)
                .appendPath(path)
                .build();

        return builtUri.toString();
    }


}
