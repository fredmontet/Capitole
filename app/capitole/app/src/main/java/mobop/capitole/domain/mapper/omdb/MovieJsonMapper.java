package mobop.capitole.domain.mapper.omdb;

import io.realm.RealmList;
import mobop.capitole.domain.model.Country;
import mobop.capitole.domain.model.Genre;
import mobop.capitole.domain.model.Language;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.Mpaa;
import mobop.capitole.domain.model.Person;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MovieJsonMapper {

    private final Gson gson;

    public MovieJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string coming from
     * Omdb API to Movie.
     *
     * @param moviesString A json representing a movie.
     * @return Movie
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<Movie> transform(String moviesString) throws JsonSyntaxException {

        List<Movie> movieList = new ArrayList<>();

        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> movieMap = gson.fromJson(moviesString, type);
        Movie movie = new Movie();

        movie.setTitle(movieMap.get("Title"));
        movie.setYear(Integer.parseInt(movieMap.get("Year")));

        Mpaa mpaa = new Mpaa();
        mpaa.setMpaa(movieMap.get("Rated"));
        movie.setMpaa(mpaa);

        movie.setReleaseDate(new Date(movieMap.get("Released")));

        GenreJsonMapper genreJsonMapper = new GenreJsonMapper();
        RealmList<Genre> genres = genreJsonMapper.transform(movieMap.get("Genre"));
        movie.setGenres(genres);

        PersonJsonMapper personJsonMapper = new PersonJsonMapper();
        RealmList<Person> people = personJsonMapper.transform(movieMap.get("Director"), "Director");
        movie.setPeople(people);

        people = personJsonMapper.transform(movieMap.get("Writer"), "Writer");
        for (Person person : people) {
            movie.getPeople().add(person);
        }

        people = personJsonMapper.transform(movieMap.get("Actors"), "Actors");
        for (Person person : people) {
            movie.getPeople().add(person);
        }

        movie.setSynopsis(movieMap.get("Plot"));

        LanguageJsonMapper languageJsonMapper = new LanguageJsonMapper();
        RealmList<Language> languages = languageJsonMapper.transform(movieMap.get("Language"));
        movie.setLanguages(languages);

        Country country = new Country();
        country.setCountry(movieMap.get("Country"));
        movie.setCountry(country);

        movie.setAwards(movieMap.get("Awards"));
        movie.setPoster(movieMap.get("Poster"));
        movie.setMetascore(movieMap.get("Metascore"));
        movie.setImdbID(movieMap.get("imdbID"));
        movie.setType(movieMap.get("Type"));

        movieList.add(movie);

        return movieList;
    }
}