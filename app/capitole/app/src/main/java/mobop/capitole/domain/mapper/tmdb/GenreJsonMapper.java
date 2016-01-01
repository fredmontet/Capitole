package mobop.capitole.domain.mapper.tmdb;

import org.json.JSONArray;
import org.json.JSONException;

import io.realm.RealmList;
import mobop.capitole.domain.model.Genre;

public class GenreJsonMapper {

    /**
     * Mapper to get a RealmList of Genre from
     * a jsonArray of Genre with only the genre IDs
     * @param jsonArray of genres
     * @return the RealmList<Genre>
     * @throws JSONException
     */
    public RealmList<Genre> transform(JSONArray jsonArray) throws JSONException {
        RealmList<Genre> genresList = new RealmList<>();
        Genre genre;
        for (int i=0; i<jsonArray.length();i++) {
            genre = new Genre();
            genre.setId(jsonArray.getString(i));
            genresList.add(genre);
        }
        return genresList;
    }

    /**
     * Mapper to get a RealmList of Genre from
     * a jsonArray of Genre
     * @param jsonArray of genres
     * @return the RealmList<Genre>
     * @throws JSONException
     */
    public RealmList<Genre> transformDetails(JSONArray jsonArray) throws JSONException {
        RealmList<Genre> genresList = new RealmList<>();
        Genre genre;
        for (int i=0; i<jsonArray.length();i++) {
            genre = new Genre();
            genre.setId(jsonArray.getJSONObject(i).getString("id"));
            genre.setGenre(jsonArray.getJSONObject(i).getString("name"));
            genresList.add(genre);
        }
        return genresList;
    }



}
