package mobop.capitole.domain.mapper.tmdb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import mobop.capitole.domain.model.Genre;

public class GenreJsonMapper {

    /**
     * Mapper to get a collection of Genre from a
     * String of genres like this one: "Drama, Romance,..."
     * @param genresStr
     * @return Collection<Genre>
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
