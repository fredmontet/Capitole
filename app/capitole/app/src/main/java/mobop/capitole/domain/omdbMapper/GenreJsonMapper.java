package mobop.capitole.domain.omdbMapper;

import java.util.ArrayList;
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
    public RealmList<Genre> transform(String genresStr){
        RealmList<Genre> genresList = new RealmList<>();
        List<String> genreStrList = Arrays.asList(genresStr.split(", "));
        Genre genre;
        for (String genreStr : genreStrList) {
            genre = new Genre();
            genre.setGenre(genreStr);
            genresList.add(genre);
        }
        return genresList;
    }

}
