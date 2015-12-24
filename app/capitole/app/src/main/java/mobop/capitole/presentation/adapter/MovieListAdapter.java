package mobop.capitole.presentation.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import io.realm.RealmList;
import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.model.Genre;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.net.TmdbApi;

/**
 * Created by fredmontet on 10/12/15.
 */

public class MovieListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private NetworkImageView mNetworkImageView;
    private ImageLoader mImageLoader;
    private List<Movie> movies = null;

    public MovieListAdapter(Context context) {
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

            // Get the poster
            mNetworkImageView = (NetworkImageView)currentView.findViewById(R.id.networkImageView);
            mImageLoader = Capitole.getInstance().getImageLoader();
            TmdbApi tmdbApi = new TmdbApi();
            String posterUrl = tmdbApi.getUrlPoster(movie.getPoster());
            mNetworkImageView.setImageUrl(posterUrl, mImageLoader);

            // Get the Title
            ((TextView) currentView.findViewById(R.id.movie_title)).setText(movie.getTitle());

            // Get the Genres
            RealmList<Genre> genres = movie.getGenres();
            LinearLayout linearLayout = (LinearLayout) currentView.findViewById(R.id.mv_genres);
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lparams.setMargins(0, 0, 10, 0);

            for(int i = 0; i<=3; i++)
            {
                if(genres.get(i) != null){
                    TextView textView = new TextView(Capitole.getInstance().getBaseContext());
                    textView.setText(genres.get(i).getGenre());
                    textView.setBackgroundResource(R.drawable.rounded_corner);
                    textView.setLayoutParams(lparams);
                    textView.setTextColor(ContextCompat.getColor(Capitole.getInstance().getBaseContext(), R.color.red));
                    final float scale = Capitole.getInstance().getBaseContext().getResources().getDisplayMetrics().density;
                    textView.setPadding((int) (6 * scale + 0.5f),(int) (3 * scale + 0.5f),(int) (6 * scale + 0.5f),(int) (3 * scale + 0.5f));
                    linearLayout.addView(textView);
                }else{
                    break;
                }

            }
        }

        return currentView;
    }

}
