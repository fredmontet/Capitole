package mobop.capitole.domain.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mobop.capitole.domain.model.Genre;

public class GenreJsonMapper {

    /**
     * Mapper to get a collection of Genre from a
     * String of genres like this one: "Drama, Romance,..."
     * @param genresStr
     * @return Collection<Genre>
     */
    public Collection<Genre> transform(String genresStr){
        Collection<Genre> genresColl = new ArrayList<>();
        List<String> genreList = Arrays.asList(genresStr.split(", "));
        Genre genre = new Genre();
        for (String genreStr : genreList) {
            genre.setGenre(genreStr);
            genresColl.add(genre);
        }
        return genresColl;
    }

}
