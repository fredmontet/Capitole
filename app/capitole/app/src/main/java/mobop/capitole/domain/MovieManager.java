package mobop.capitole.domain;

import android.content.Context;

import java.net.MalformedURLException;
import java.util.Collection;

import io.realm.Realm;

import mobop.capitole.domain.mapper.OmdbMapper;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.User;
import mobop.capitole.domain.net.ApiQuerier;

/**
 * Created by fredmontet on 13/12/15.
 */
public class MovieManager {

    private Realm realm;
    private User user;
    private Context context;
    private OmdbMapper omdbMapper;

    MovieManager(Context context, User user){
        this.user = user;
        this.context = context;
        this.realm = Realm.getInstance(this.context);
        this.omdbMapper = new OmdbMapper();
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

    private Collection<Movie> getSuggestion() throws MalformedURLException {
        // TODO change this for the real suggestions!!!
        ApiQuerier api = new ApiQuerier(this.context);
        String json = api.getMoviesByTitle("titanic", "", "full");
        Collection<Movie> suggestion = omdbMapper.transform(json);
        return suggestion;
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
