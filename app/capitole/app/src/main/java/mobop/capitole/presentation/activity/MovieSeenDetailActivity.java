package mobop.capitole.presentation.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.text.DateFormat;

import io.realm.RealmList;
import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.MovieManager;
import mobop.capitole.domain.model.Genre;
import mobop.capitole.domain.model.Language;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.User;
import mobop.capitole.domain.net.TmdbApi;
import mobop.capitole.presentation.fragment.SeenFragment;
import mobop.capitole.presentation.fragment.ToSeeFragment;

public class MovieSeenDetailActivity extends AppCompatActivity {

    private MovieManager movieManager;
    private Movie mMovie;

    private ActionBar mActionBar;
    private Toolbar mToolbar;
    private ScrollView mRelativeLayout;

    private NetworkImageView mNetworkImageView;
    private ImageLoader mImageLoader;
    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mLanguage;
    private TextView mSynopsis;
    private TextView mBudget;
    public final static String SWITCH_TAB = "mobop.capitole.activities.switch_tab";
    private final int TAB_TOSEE = 2;

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_seen_detail);

        this.mRelativeLayout = (ScrollView)findViewById(R.id.seen_details_scrollview);

        // Toolbar section
        //================

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // Intent section
        //================

        Intent intent = getIntent();
        String movieUuid = intent.getStringExtra(SeenFragment.movieUuid);

        // Capitole section
        //=================

        User user = Capitole.getInstance().getUser();
        movieManager = new MovieManager(getApplicationContext(), user);
        final Movie movie = movieManager.getMovie(movieUuid);

        // Movie Title
        mActionBar.setTitle("Movie Seen");

        // Get the poster
        mNetworkImageView = (NetworkImageView)findViewById(R.id.networkImageView);
        mImageLoader = Capitole.getInstance().getImageLoader();
        TmdbApi tmdbApi = new TmdbApi();
        String posterUrl = tmdbApi.getUrlPoster(movie.getPoster());
        mNetworkImageView.setImageUrl(posterUrl, mImageLoader);

        // Movie Title
        mTitle = (TextView) findViewById(R.id.mv_title);
        mTitle.setText(movie.getTitle());

        // Release Date
        mReleaseDate = (TextView) findViewById(R.id.mv_release_date);
        String releaseDate = DateFormat.getDateInstance().format(movie.getReleaseDate());
        mReleaseDate.setText("Released the "+releaseDate);

        // Budget
        mBudget = (TextView) findViewById(R.id.mv_budget);
        mBudget.setText(movie.getBudget());

        // Language
        mLanguage = (TextView)findViewById(R.id.mv_language);
        RealmList<Language> languages = movie.getLanguages();
        mLanguage.setText(languages.get(0).getLanguage());

        // Synopsis
        mSynopsis = (TextView)findViewById(R.id.mv_synopsis);
        mSynopsis.setText(movie.getSynopsis());

        // Genres
        RealmList<Genre> genres = movie.getGenres();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mv_genres);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int i = 0; i<genres.size()-1; i++)
        {
            TextView textView = new TextView(getBaseContext());
            textView.setText(genres.get(i).getGenre());
            textView.setBackgroundResource(R.drawable.rounded_corner);
            textView.setLayoutParams(lparams);
            textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.red));
            final float scale = getBaseContext().getResources().getDisplayMetrics().density;
            textView.setPadding((int) (6 * scale + 0.5f),(int) (3 * scale + 0.5f),(int) (6 * scale + 0.5f),(int) (3 * scale + 0.5f));
            linearLayout.addView(textView);
        }
    }

    //==============================================================================================
    // Functions
    //==============================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_seen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_rating:
                Toast.makeText(this, "Action rate selected", Toast.LENGTH_SHORT).show();

                // TODO Start rating dialog here

                break;
            case R.id.action_delete:

                movieManager.removeFromMoviesSeen(this.mMovie);
                final Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(SWITCH_TAB, TAB_TOSEE);
                startActivity(intent);


                break;

            default:
                break;
        }

        return true;
    }
}
