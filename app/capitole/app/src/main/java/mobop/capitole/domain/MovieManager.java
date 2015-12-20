package mobop.capitole.domain;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;

import io.realm.Realm;

import mobop.capitole.domain.omdbMapper.MovieJsonMapper;
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
        this.realm = Realm.getInstance(this.context);
        this.movieJsonMapper = new MovieJsonMapper();
    }

    public Movie getMovie(String movieId){
        return realm.where(Movie.class).equalTo("uuid", movieId).findFirst();
    }

    public Collection<Movie> getMoviesSeen(){
        return this.user.getMoviesSeen();
    }

    public Collection<Movie> getMoviesToSee(){
        return this.user.getMoviesToSee();
    }

    public void getSuggestion(final MovieManagerCallback callback) throws MalformedURLException {

        // TestToast
        Toast.makeText(context, "getSuggestions()", Toast.LENGTH_SHORT).show();

        // TODO change this for the real suggestions!!!
        ApiQuery api = new ApiQuery(this.context);
        api.getMoviesByTitle("titanic", "", "full", new ApiQuery.ApiQueryCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                List<Movie> suggestion = movieJsonMapper.transform(response.toString());
                callback.onSuccess(suggestion);
            }
        });

    }

    public boolean addToMoviesSeen(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesSeen().add(movie);
        realm.commitTransaction();
        return res;
    }

    public boolean addToMoviesToSee(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesToSee().add(movie);
        realm.commitTransaction();
        return res;
    }

    public boolean removeFromMoviesSeen(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesSeen().remove(movie);
        realm.commitTransaction();
        return res;
    }

    public boolean removeFromMoviesToSee(Movie movie){
        realm.beginTransaction();
        boolean res = this.user.getMoviesToSee().remove(movie);
        realm.commitTransaction();
        return res;
    }

    /**
     * Simple callback interface
     */
    public interface MovieManagerCallback{
        void onSuccess(List<Movie> response);
    }




}
