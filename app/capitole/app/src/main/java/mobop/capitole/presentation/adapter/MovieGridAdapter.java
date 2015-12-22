package mobop.capitole.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.net.OmdbApi;
import mobop.capitole.domain.net.TmdbApi;

/**
 * Created by fredmontet on 10/12/15.
 */
public class MovieGridAdapter extends BaseAdapter{


    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    private static final String IMAGE_URL =
            "http://developer.android.com/images/training/system-ui.png";

    private LayoutInflater inflater;

    private List<Movie> movies = null;

    public MovieGridAdapter(Context context) {
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
            currentView = inflater.inflate(R.layout.movie_griditem, null);
        }

        Movie movie = movies.get(position);

        if (movie != null) {

            // Get the NetworkImageView that will display the image.
            mNetworkImageView = (NetworkImageView)currentView.findViewById(R.id.networkImageView);

            // Get the ImageLoader through your singleton class.
            mImageLoader = Capitole.getInstance().getImageLoader();

            // Set the URL of the image that should be loaded into this view, and
            // specify the ImageLoader that will be used to make the request.
            TmdbApi tmdbApi = new TmdbApi();
            String posterUrl = tmdbApi.getUrlPoster(movie.getPoster());

            mNetworkImageView.setImageUrl(posterUrl, mImageLoader);


            // ((TextView) currentView.findViewById(R.id.movie_title)).setText(movie.getTitle());
            // ((TextView) currentView.findViewById(R.id.movie_release_date)).setText(movie.getReleaseDate().toString());
        }

        return currentView;
    }

}
