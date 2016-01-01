package mobop.capitole.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
import mobop.capitole.domain.model.Movie;
import mobop.capitole.presentation.fragment.ToSeeFragment;

public class MovieToSeeDetailActivity extends AppCompatActivity {

    private MovieManager movieManager;
    private Movie mMovie;
    private ActionBar mActionBar;
    private Toolbar mToolbar;
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
        mNetworkImageView = (NetworkImageView) findViewById(R.id.networkImageView);
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
        mLanguage = (TextView) findViewById(R.id.mv_language);
        RealmList<Language> languages = this.mMovie.getLanguages();
        mLanguage.setText(languages.get(0).getLanguage());

        // Synopsis
        mSynopsis = (TextView) findViewById(R.id.mv_synopsis);
        mSynopsis.setText(this.mMovie.getSynopsis());

        // Genres
        RealmList<Genre> genres = this.mMovie.getGenres();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mv_genres);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < genres.size() - 1; i++) {
            TextView textView = new TextView(getBaseContext());
            textView.setText(genres.get(i).getGenre());
            textView.setBackgroundResource(R.drawable.rounded_corner);
            textView.setLayoutParams(lparams);
            textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.red));
            final float scale = getBaseContext().getResources().getDisplayMetrics().density;
            textView.setPadding((int) (6 * scale + 0.5f), (int) (3 * scale + 0.5f), (int) (6 * scale + 0.5f), (int) (3 * scale + 0.5f));
            linearLayout.addView(textView);
        }
    }

    //==============================================================================================
    // Action Bar Menu
    //==============================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_tosee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Rate action button
            //===================

            case R.id.action_rate:
                AlertDialog.Builder builder_rate = new AlertDialog.Builder(MovieToSeeDetailActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialog_view = inflater.inflate(R.layout.dialog_movie_rate, null);
                builder_rate.setView(dialog_view);
                builder_rate.setMessage("How good was the movie?");

                // User clicked OK button
                builder_rate.setPositiveButton("Rate", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        final RatingBar movieRating = (RatingBar) dialog_view.findViewById(R.id.movieRating);
                        final TextView movieComment = (TextView) dialog_view.findViewById(R.id.movieComment);

                        movieManager.rateMovie(mMovie, movieRating.getRating(), movieComment.getText().toString());
                        movieManager.changeList(mMovie);
                        final Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.putExtra(SWITCH_TAB, TAB_TOSEE);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "Movie is now in seen list!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                // User cancelled the dialog
                builder_rate.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog_rate = builder_rate.create();
                dialog_rate.show();
                break;


            //  Delete action button
            //======================

            case R.id.action_delete:
                AlertDialog.Builder builder_delete = new AlertDialog.Builder(MovieToSeeDetailActivity.this);
                builder_delete.setMessage("Do you really want to delete this movie?");

                // User clicked OK button
                builder_delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        movieManager.removeFromMoviesToSee(mMovie);
                        final Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.putExtra(SWITCH_TAB, TAB_TOSEE);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                // User cancelled the dialog
                builder_delete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog_delete = builder_delete.create();
                dialog_delete.show();
                break;

            default:
                break;
        }

        return true;
    }
}
