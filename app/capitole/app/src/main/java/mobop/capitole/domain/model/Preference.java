package mobop.capitole.domain.model;

import io.realm.RealmObject;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Preference extends RealmObject{

    private String genres;
    private String people;
    private String movies;
    private String languages;
    private String countries;

    // Methods
    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }
}
