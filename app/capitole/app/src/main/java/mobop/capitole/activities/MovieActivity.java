package mobop.capitole.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmResults;
import mobop.capitole.R;
import mobop.capitole.fragments.SeenFragment;
import mobop.capitole.model.Movie;

public class MovieActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mReleaseDate;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        String movieUuid = intent.getStringExtra(SeenFragment.movieUuid);

        // Get a Realm instance for this thread
        realm = Realm.getInstance(getApplicationContext());
        Movie movie = realm.where(Movie.class).equalTo("uuid", movieUuid).findFirst();


        mTitle = (TextView) findViewById(R.id.mv_title);
        mTitle.setTextSize(40);
        mTitle.setText(movie.getTitle());


        mReleaseDate = (TextView) findViewById(R.id.mv_release_date);
        mReleaseDate.setText(movie.getReleaseDate().toString());

    }

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
