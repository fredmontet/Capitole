package mobop.capitole.domain;

import android.app.Application;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import io.realm.Realm;

import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import mobop.capitole.Capitole;
import mobop.capitole.domain.mapper.tmdb.MovieJsonMapper;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.User;
import mobop.capitole.domain.net.ApiQuery;

/**
 * Created by fredmontet on 13/12/15.
 */
public class MovieManager extends Application{

    private Realm realm;
    private User user;
    private Context context;
    private MovieJsonMapper movieJsonMapper;

    public MovieManager(Context context, User user){
        this.user = user;
        this.context = context;
        this.movieJsonMapper = new MovieJsonMapper();


        // Configure Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this.context)
                .name("capitole.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        // Clear the realm from last time
        // If uncommented, throw an IllegalStateException...
        //Realm.deleteRealm(realmConfiguration);

        // Create a new empty instance of Realm
        this.realm = Realm.getInstance(realmConfiguration);
    }

    public Movie getMovie(String movieId){
        return this.realm.where(Movie.class).equalTo("uuid", movieId).findFirst();
    }

    public void getMovieFromApi(String tmdbId, final MovieManagerCallback callback) throws MalformedURLException {
        ApiQuery api = new ApiQuery(this.context);
        api.getMovieById(tmdbId, new ApiQuery.ApiQueryCallback() {
            @Override
            public void onSuccess(JSONArray response) {
                List<Movie> movie = null;
                try {
                    movie = movieJsonMapper.transformDetails(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                callback.onSuccess(movie);
            }
        });
    }

    public List<Movie> getMoviesSeen(){
        return this.user.getMoviesSeen();
    }

    public List<Movie> getMoviesToSee(){
        return this.user.getMoviesToSee();
    }

    public void getSuggestion(final MovieManagerCallback callback) throws MalformedURLException {
        ApiQuery api = new ApiQuery(this.context);
        api.getMovieSuggestion(new ApiQuery.ApiQueryCallback() {
            @Override
            public void onSuccess(JSONArray response) {
                List<Movie> suggestion = null;
                try {
                    suggestion = movieJsonMapper.transform(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                callback.onSuccess(suggestion);
            }
        });

    }

    public boolean addToMoviesSeen(Movie movie){

        boolean inToSeeList = false;
        for(Movie movieToSee : this.user.getMoviesToSee()) {
            if(movieToSee.getTmdbID().equals(movie.getTmdbID())){
                inToSeeList = true;
                break;
            }
        }

        boolean inSeenList = false;
        for(Movie movieSeen : this.user.getMoviesSeen()) {
            if(movieSeen.getTmdbID().equals(movie.getTmdbID())){
                inSeenList = true;
                break;
            }
        }

        if(inToSeeList || inSeenList){
            return false;
        }else{
            this.realm.beginTransaction();
            realm.copyToRealmOrUpdate(movie);
            this.user.getMoviesSeen().add(movie);
            this.realm.commitTransaction();
            return true;
        }
    }

    public boolean addToMoviesToSee(Movie movie){

        boolean inToSeeList = false;
        for(Movie movieToSee : this.user.getMoviesToSee()) {
            if(movieToSee.getTmdbID().equals(movie.getTmdbID())){
                inToSeeList = true;
                break;
            }
        }

        boolean inSeenList = false;
        for(Movie movieSeen : this.user.getMoviesSeen()) {
            if(movieSeen.getTmdbID().equals(movie.getTmdbID())){
                inSeenList = true;
                break;
            }
        }

        if(inToSeeList || inSeenList){
            return false;
        }else{
            this.realm.beginTransaction();
            realm.copyToRealmOrUpdate(movie);
            this.user.getMoviesToSee().add(movie);
            this.realm.commitTransaction();
            return true;
        }
    }

    public boolean removeFromMoviesSeen(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesSeen().remove(movie);
        movie.removeFromRealm();
        realm.commitTransaction();
        return res;
    }

    public boolean removeFromMoviesToSee(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesToSee().remove(movie);
        movie.removeFromRealm();
        realm.commitTransaction();
        return res;
    }

    public void changeList(Movie movie){
        if(this.user.getMoviesToSee().contains(movie)){
            realm.beginTransaction();
            this.user.getMoviesSeen().add(movie);
            this.user.getMoviesToSee().remove(movie);
            realm.copyToRealmOrUpdate(movie);
            realm.commitTransaction();
        }else if(this.user.getMoviesSeen().contains(movie)){
            realm.beginTransaction();
            this.user.getMoviesToSee().add(movie);
            this.user.getMoviesSeen().remove(movie);
            realm.copyToRealmOrUpdate(movie);
            realm.commitTransaction();
        }
    }

    /**
     * Function to close the MovieManager
     * In fact, it just close the realm...
     */
    public void close(){
        this.realm.close(); // Remember to close Realm when done.
    }

    /**
     * Callback interface
     */
    public interface MovieManagerCallback{
        void onSuccess(List<Movie> response);
    }

}
