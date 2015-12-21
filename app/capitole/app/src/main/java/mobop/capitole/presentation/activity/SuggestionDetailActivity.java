package mobop.capitole.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.util.List;

import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.MovieManager;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.User;
import mobop.capitole.presentation.fragment.SuggestionFragment;

public class SuggestionDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mReleaseDate;
    private MovieManager movieManager;

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_detail);
        Intent intent = getIntent();
        String tmdbId = intent.getStringExtra(SuggestionFragment.tmdbId);

        User user = Capitole.getInstance().getUser();
        movieManager = new MovieManager(getApplicationContext(), user);

        try {
            movieManager.getMovieFromApi(tmdbId, new MovieManager.MovieManagerCallback() {
                @Override
                public void onSuccess(List<Movie> response) {

                    // TODO If possible change this to avoid making the get(0)
                    Movie movie = response.get(0);

                    // TODO Use the movie object
                    mTitle = (TextView) findViewById(R.id.mv_title);
                    mTitle.setTextSize(40);
                    mTitle.setText(movie.getTitle());

                    mReleaseDate = (TextView) findViewById(R.id.mv_release_date);
                    mReleaseDate.setText(movie.getReleaseDate().toString());
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    //==============================================================================================
    // Functions
    //==============================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
