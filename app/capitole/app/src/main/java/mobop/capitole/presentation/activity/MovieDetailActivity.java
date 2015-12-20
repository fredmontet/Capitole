package mobop.capitole.presentation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import io.realm.Realm;
import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.MovieManager;
import mobop.capitole.domain.model.User;
import mobop.capitole.presentation.fragment.SeenFragment;
import mobop.capitole.domain.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mReleaseDate;
    private MovieManager movieManager;

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        String movieUuid = intent.getStringExtra(SeenFragment.movieUuid);

        User user = Capitole.getInstance().getUser();
        movieManager = new MovieManager(getApplicationContext(), user);
        Movie movie = movieManager.getMovie(movieUuid);

        mTitle = (TextView) findViewById(R.id.mv_title);
        mTitle.setTextSize(40);
        mTitle.setText(movie.getTitle());


        mReleaseDate = (TextView) findViewById(R.id.mv_release_date);
        mReleaseDate.setText(movie.getReleaseDate().toString());

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
