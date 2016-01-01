package mobop.capitole.domain.net;

/**
 * A class containing the URL to access OMDb ressources
 */
public class OmdbApi {

    private String API_BASE_URL = "http://www.omdbapi.com/?";

    /**
     * Return a String of the URL to get some movies by their title
     *
     * @param title
     * @param year
     * @param plot    (short or full)
     * @param resType (json or xml)
     * @return The URL in a String
     */
    public String getUrlMoviesByTitle(String title, String year, String plot, String resType) {
        return API_BASE_URL + "t=" + title + "&y=" + year + "&plot=" + plot + "&r=" + resType;
    }

    /**
     * Return a String of the URL to get a movie by its OMDB Id
     *
     * @param id
     * @param plot    (short or full)
     * @param resType (json or xml)
     * @return The URL in a String
     */
    public String getUrlMovieById(String id, String plot, String resType) {
        return API_BASE_URL + "i=" + id + "&plot=" + plot + "&r=" + resType;
    }

}
