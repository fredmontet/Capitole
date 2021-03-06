package mobop.capitole.model;

import java.util.Date;
import java.util.UUID;
import javax.xml.datatype.Duration;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Movie extends RealmObject{

    @PrimaryKey
    private String uuid;
    private String title;
    private String tagline;
    private String synopsis;
    private long length;
    private String trailerlink;
    private String website;
    private Date releaseDate;
    private int budget;

    // Many to many
    private RealmList<Person> people;
    private RealmList<Keyword> keywords;
    private RealmList<Language> languages;
    private RealmList<Genre> genres;

    // Many to one
    private Country country;
    private Mpaa mpaa;

    // Methods
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getTrailerlink() {
        return trailerlink;
    }

    public void setTrailerlink(String trailerlink) {
        this.trailerlink = trailerlink;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public RealmList<Person> getPeople() {
        return people;
    }

    public void setPeople(RealmList<Person> people) {
        this.people = people;
    }

    public RealmList<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(RealmList<Keyword> keywords) {
        this.keywords = keywords;
    }

    public RealmList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(RealmList<Language> languages) {
        this.languages = languages;
    }

    public RealmList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<Genre> genres) {
        this.genres = genres;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Mpaa getMpaa() {
        return mpaa;
    }

    public void setMpaa(Mpaa mpaa) {
        this.mpaa = mpaa;
    }
}
