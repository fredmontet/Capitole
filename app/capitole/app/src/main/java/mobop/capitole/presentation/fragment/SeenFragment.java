package mobop.capitole.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import mobop.capitole.R;
import mobop.capitole.presentation.activity.MovieDetailActivity;
import mobop.capitole.presentation.adapter.MovieListAdapter;
import mobop.capitole.domain.model.Movie;

/**
 * Created by fredmontet on 06/11/15.
 */

public class SeenFragment extends Fragment implements AdapterView.OnItemClickListener{

    private View mView;
    private MovieListAdapter mAdapter;
    private ListView mListView;
    private FloatingActionButton mFabSeen;
    private Realm realm;
    public final static String movieUuid = "mobop.capitole.activities.movieUuid";

    public SeenFragment() {
        // Required empty public constructor
    }


    //==============================================================================================
    // Life Cycle
    //==============================================================================================


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getContext())
                .name("capitole.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        // Clear the realm from last time
        Realm.deleteRealm(realmConfiguration);

        // Create a new empty instance of Realm
        realm = Realm.getInstance(realmConfiguration);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_seen, container, false);
        mAdapter = new MovieListAdapter(getContext());
        mListView = (ListView)mView.findViewById(R.id.seenListview);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(SeenFragment.this);
        mFabSeen = (FloatingActionButton) mView.findViewById(R.id.fabSeen);
        mFabSeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMovie();
            }
        });

        // Inflate the layout for this fragment
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Load from file "movies.json" first time
        if(mAdapter == null) {

            //List<Movie> movies = loadMovies();
            RealmResults<Movie> movies = realm.allObjects(Movie.class);

            //This is the ListView adapter
            mAdapter = new MovieListAdapter(getActivity());
            mAdapter.setData(movies);

            //This is the ListView which will display the list of movies
            mListView = (ListView) mView.findViewById(R.id.seenListview);
            mListView.setAdapter(mAdapter);
            mListView.setOnItemClickListener(SeenFragment.this);
            mAdapter.notifyDataSetChanged();
            mListView.invalidate();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }


    //==============================================================================================
    // Data functions
    //==============================================================================================


    public void updateMovies() {
        // Pull all the cities from the realm
        RealmResults<Movie> movies = realm.where(Movie.class).findAll();

        // Put these items in the Adapter
        mAdapter.setData(movies);
        mAdapter.notifyDataSetChanged();
        mListView.invalidate();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie modifiedCity = (Movie)mAdapter.getItem(position);

        // Get the movie object of realm matching the uuid
        Movie movie = realm.where(Movie.class).equalTo("uuid", modifiedCity.getUuid()).findFirst();

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(movieUuid, movie.getUuid());
        startActivity(intent);
    }

    /**
     * Create a dummy movie to test realm add to db
     */
    public void addMovie() {

        Toast.makeText(getActivity(), "addMovie()", Toast.LENGTH_SHORT).show();

        realm.beginTransaction();
        Movie movie = realm.createObject(Movie.class);
        movie.setUuid(UUID.randomUUID().toString());
        movie.setTitle("Heidi court dans les montagnes");
        movie.setReleaseDate(new Date());
        realm.commitTransaction();

        updateMovies();
    }
}//EOC
