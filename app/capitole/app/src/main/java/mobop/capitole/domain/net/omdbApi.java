package mobop.capitole.domain.net;

/**
 * Created by fredmontet on 14/12/15.
 */
public class OmdbApi {

    private String API_BASE_URL = "http://www.omdbapi.com/?";

    public String getUrlMoviesByTitle(String title, String year, String plot, String resType){
        return API_BASE_URL+"t="+title+"&y="+year+"&plot="+plot+"&r="+resType;
    }

    public String getUrlMovieById(String id, String plot, String resType){
        return API_BASE_URL+"i="+id+"&plot="+plot+"&r="+resType;
    }

}
