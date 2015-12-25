package mobop.capitole.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import mobop.capitole.domain.model.User;
import mobop.capitole.domain.net.TmdbApi;
import mobop.capitole.presentation.fragment.SeenFragment;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.presentation.fragment.ToSeeFragment;

public class MovieToSeeDetailActivity extends AppCompatActivity {

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
    private final int TAB_TOSEE = 1;

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_tosee_detail);

        this.mRelativeLayout = (ScrollView)findViewById(R.id.tosee_details_scrollview);

        // Toolbar section
        //================

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // Intent section
        //================

        Intent intent = getIntent();
        String movieUuid = intent.getStringExtra(ToSeeFragment.movieUuid);

        // Capitole section
        //=================

        User user = Capitole.getInstance().getUser();
        movieManager = new MovieManager(getApplicationContext(), user);
        this.mMovie = movieManager.getMovie(movieUuid);

        // Movie Title
        mActionBar.setTitle("Movie To See");

        // Get the poster
        mNetworkImageView = (NetworkImageView)findViewById(R.id.networkImageView);
        mImageLoader = Capitole.getInstance().getImageLoader();
        TmdbApi tmdbApi = new TmdbApi();
        String posterUrl = tmdbApi.getUrlPoster(this.mMovie.getPoster());
        mNetworkImageView.setImageUrl(posterUrl, mImageLoader);

        // Movie Title
        mTitle = (TextView) findViewById(R.id.mv_title);
        mTitle.setText(this.mMovie.getTitle());

        // Release Date
        mReleaseDate = (TextView) findViewById(R.id.mv_release_date);
        String releaseDate = DateFormat.getDateInstance().format(this.mMovie.getReleaseDate());
        mReleaseDate.setText("Released the " + releaseDate);

        // Budget
        mBudget = (TextView) findViewById(R.id.mv_budget);
        mBudget.setText(this.mMovie.getBudget());

        // Language
        mLanguage = (TextView)findViewById(R.id.mv_language);
        RealmList<Language> languages = this.mMovie.getLanguages();
        mLanguage.setText(languages.get(0).getLanguage());

        // Synopsis
        mSynopsis = (TextView)findViewById(R.id.mv_synopsis);
        mSynopsis.setText(this.mMovie.getSynopsis());

        // Genres
        RealmList<Genre> genres = this.mMovie.getGenres();
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
    // Action Bar Menu
    //==============================================================================================

    // TODO pourrait être enlevé...
    @Override
    public void onBackPressed(){
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_tosee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_rate:

                this.movieManager.changeList(this.mMovie);

                // TODO Start rating dialog here


                break;
            case R.id.action_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(MovieToSeeDetailActivity.this);
                builder.setMessage("Do you really want to delete this movie?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        movieManager.removeFromMoviesToSee(mMovie);
                        final Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.putExtra(SWITCH_TAB, TAB_TOSEE);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                
                break;

            default:
                break;
        }

        return true;
    }
}
