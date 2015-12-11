package mobop.capitole.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import mobop.capitole.R;
import mobop.capitole.model.Movie;

/**
 * Created by fredmontet on 10/12/15.
 */
public class MovieAdapter  extends BaseAdapter{

    private LayoutInflater inflater;

    private List<Movie> movies = null;

    public MovieAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Movie> details) {
        this.movies = details;
    }

    @Override
    public int getCount() {
        if (movies == null) {
            return 0;
        }
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        if (movies == null || movies.get(position) == null) {
            return null;
        }
        return movies.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.movie_listitem, null);
        }

        Movie movie = movies.get(position);

        if (movie != null) {
            ((TextView) currentView.findViewById(R.id.movie_title)).setText(movie.getTitle());
            ((TextView) currentView.findViewById(R.id.movie_release_date)).setText(movie.getReleaseDate().toString());
        }

        return currentView;
    }

}
