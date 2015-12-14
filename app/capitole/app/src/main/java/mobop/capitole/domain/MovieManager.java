package mobop.capitole.domain;

import android.content.Context;

import java.util.Collection;

import io.realm.Realm;

import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.User;

/**
 * Created by fredmontet on 13/12/15.
 */
public class MovieManager {

    private Realm realm;
    private User user;

    MovieManager(Context context, User user){
        this.realm = Realm.getInstance(context);
        this.user = user;
    }

    private Movie getMovie(String movieId){
        return realm.where(Movie.class).equalTo("uuid", movieId).findFirst();
    }

    private Collection<Movie> getMoviesSeen(){
        return this.user.getMoviesSeen();
    }

    private Collection<Movie> getMoviesToSee(){
        return this.user.getMoviesToSee();
    }

    private Collection<Movie> getSuggestion(){
        // TODO change this for the real suggestions!!!
        return this.user.getMoviesToSee();
    }

    private boolean addToMoviesSeen(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesSeen().add(movie);
        realm.commitTransaction();
        return res;
    }

    private boolean addToMoviesToSee(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesToSee().add(movie);
        realm.commitTransaction();
        return res;
    }

    private boolean removeFromMoviesSeen(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesSeen().remove(movie);
        realm.commitTransaction();
        return res;
    }

    private boolean removeFromMoviesToSee(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesToSee().remove(movie);
        realm.commitTransaction();
        return res;
    }



}
