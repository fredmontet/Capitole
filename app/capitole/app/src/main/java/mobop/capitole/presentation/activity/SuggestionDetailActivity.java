package mobop.capitole.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

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
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private TextView mReleaseDate;
    private MovieManager movieManager;

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_detail);

        // Toolbar section
        //================

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // Intent section
        //================

        Intent intent = getIntent();
        String tmdbId = intent.getStringExtra(SuggestionFragment.tmdbId);

        // FAB Section
        //============

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

        // Capitole section
        //=================
        User user = Capitole.getInstance().getUser();
        movieManager = new MovieManager(getApplicationContext(), user);

        try {
            movieManager.getMovieFromApi(tmdbId, new MovieManager.MovieManagerCallback() {
                @Override
                public void onSuccess(List<Movie> response) {

                    // TODO If possible change this to avoid making the get(0)
                    final Movie movie = response.get(0);

                    // TODO Use the movie object
                    mActionBar.setTitle(movie.getTitle());
                    mReleaseDate = (TextView) findViewById(R.id.mv_release_date);
                    mReleaseDate.setText(movie.getReleaseDate().toString());

                    findViewById(R.id.action_to_see).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            movieManager.addToMoviesToSee(movie);
                        }
                    });

                    findViewById(R.id.action_seen).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            movieManager.addToMoviesSeen(movie);
                        }
                    });

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
