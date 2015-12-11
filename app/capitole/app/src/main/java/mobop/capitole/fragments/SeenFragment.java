package mobop.capitole.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.internal.IOException;

import mobop.capitole.R;
import mobop.capitole.activities.MovieActivity;
import mobop.capitole.adapters.MovieAdapter;
import mobop.capitole.model.Country;
import mobop.capitole.model.Movie;

/**
 * Created by fredmontet on 06/11/15.
 */

public class SeenFragment extends Fragment implements AdapterView.OnItemClickListener{

    private View mView;
    private MovieAdapter mAdapter;
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

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getActivity()).build();

        // Clear the realm from last time
        Realm.deleteRealm(realmConfiguration);

        // Create a new empty instance of Realm
        realm = Realm.getInstance(realmConfiguration);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_seen, container, false);
        mAdapter = new MovieAdapter(getContext());
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
            mAdapter = new MovieAdapter(getActivity());
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


    private List<Movie> loadMovies() {
//        // In this case we're loading from local assets.
//        // NOTE: could alternatively easily load from network
//        InputStream stream;
//        try {
//            stream = getAssets().open("movies.json");
//        } catch (IOException e) {
//            return null;
//        }
//
//        // GSON can parse the data.
//        // Note there is a bug in GSON 2.5 that can cause it to StackOverflow when working with RealmObjects.
//        // To work around this, use the ExclusionStrategy below or downgrade to 1.7.1
//        // See more here: https://code.google.com/p/google-gson/issues/detail?id=440
//        Gson gson = new GsonBuilder()
//                .setExclusionStrategies(new ExclusionStrategy() {
//                    @Override
//                    public boolean shouldSkipField(FieldAttributes f) {
//                        return f.getDeclaringClass().equals(RealmObject.class);
//                    }
//
//                    @Override
//                    public boolean shouldSkipClass(Class<?> clazz) {
//                        return false;
//                    }
//                })
//                .create();
//
//        JsonElement json = new JsonParser().parse(new InputStreamReader(stream));
//        List<Movie> movies = gson.fromJson(json, new TypeToken<List<Movie>>() {}.getType());
//
//        // Open a transaction to store items into the realm
//        // Use copyToRealm() to convert the objects into proper RealmObjects managed by Realm.
//        realm.beginTransaction();
//        Collection<Movie> realmMovies = realm.copyToRealm(movies);
//        realm.commitTransaction();
//
//        return new ArrayList<Movie>(realmMovies);
        return new ArrayList<Movie>();
    }

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

        Intent intent = new Intent(getActivity(), MovieActivity.class);
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
}
