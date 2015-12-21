package mobop.capitole.domain.net;

import android.net.Uri;

/**
 * Created by fredmontet on 20/12/15.
 */
public class TmdbApi {

    private final String API_SEARCH_BASE_URL = "http://api.themoviedb.org/3/search/movie?";
    private final String API_FIND_BASE_URL = "https://api.themoviedb.org/3/find/";
    private final String API_DISCOVER_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";

    private final String API_KEY_PARAM = "api_key";
    private final String API_KEY_VALUE = "a47e4f6e9fc5d19bf5bebd8596188dc4";


    public String getUrlMoviesByTitle(String title){

        Uri builtUri = Uri.parse(API_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter("query", title)
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();

        return builtUri.toString();

    }

    public String getUrlMovieById(String id, String source){

        if(source.equals(null)){
            source = "imdb_id";
        }

        Uri builtUri = Uri.parse(API_FIND_BASE_URL).buildUpon()
                .appendPath(id+"?")
                .appendQueryParameter("external_source", source)
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();

        return builtUri.toString();
    }

    public String getUrlDiscoverMovies(){

        Uri builtUri = Uri.parse(API_DISCOVER_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();

        return builtUri.toString();
    }






}
