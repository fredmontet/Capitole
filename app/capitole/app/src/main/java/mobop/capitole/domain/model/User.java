package mobop.capitole.domain.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  User model for the Capitole database
 */
public class User extends RealmObject{

    @PrimaryKey
    private String uuid;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String city;
    private String gender;

    // One to one
    private Preference preference;

    // Many to many
    private RealmList<Rating> ratings;
    private RealmList<Comment> comments;

    // The Capitole associations
    private RealmList<Movie> moviesSeen;
    private RealmList<Movie> moviesToSee;
    private RealmList<Movie> moviesSuggestion;

    // Constructor
    public User(){
        this.ratings = new RealmList<>();
        this.comments = new RealmList<>();
        this.moviesSeen = new RealmList<>();
        this.moviesToSee = new RealmList<>();
        this.moviesSuggestion = new RealmList<>();
    }

    // Methods
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public RealmList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(RealmList<Rating> ratings) {
        this.ratings = ratings;
    }

    public RealmList<Comment> getComments() {
        return comments;
    }

    public void setComments(RealmList<Comment> comments) {
        this.comments = comments;
    }

    public RealmList<Movie> getMoviesSeen() {
        return moviesSeen;
    }

    public void setMoviesSeen(RealmList<Movie> moviesSeen) {
        this.moviesSeen = moviesSeen;
    }

    public RealmList<Movie> getMoviesToSee() {
        return moviesToSee;
    }

    public void setMoviesToSee(RealmList<Movie> moviesToSee) {
        this.moviesToSee = moviesToSee;
    }

    public RealmList<Movie> getMoviesSuggestion() {
        return moviesSuggestion;
    }

    public void setMoviesSuggestion(RealmList<Movie> moviesSuggestion) {
        this.moviesSuggestion = moviesSuggestion;
    }
}
