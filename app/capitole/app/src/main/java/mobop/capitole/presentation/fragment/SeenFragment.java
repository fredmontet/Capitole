package mobop.capitole.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.MovieManager;
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
    private MovieManager movieManager;
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
        this.movieManager = new MovieManager(getContext(), Capitole.getInstance().getUser());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_seen, container, false);
        mAdapter = new MovieListAdapter(getContext());
        mListView = (ListView)mView.findViewById(R.id.seenListview);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(SeenFragment.this);
        registerForContextMenu(mListView);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Movie> movies = movieManager.getMoviesSeen();
        mAdapter.setData(movies);
        mAdapter.notifyDataSetChanged();
        mListView.invalidate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.movieManager.close();
    }

    //==============================================================================================
    // Functions
    //==============================================================================================

    public void sendToListToSee(int position){
        Movie clickedMovie = (Movie)mAdapter.getItem(position);

        // Get the movie object of realm matching the uuid
        Movie movie = this.movieManager.getMovie(clickedMovie.getUuid());

        this.movieManager.changeList(movie);

        // Uodate the view
        mAdapter.notifyDataSetChanged();
        mListView.invalidate();


        Snackbar snackbar = Snackbar.make(mView, "Movie sent to list to see", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void remove(int position){
        Movie clickedMovie = (Movie)mAdapter.getItem(position);

        // Get the movie object of realm matching the uuid
        Movie movie = this.movieManager.getMovie(clickedMovie.getUuid());

        // Remove the movie
        this.movieManager.removeFromMoviesSeen(movie);

        // Uodate the view
        mAdapter.notifyDataSetChanged();
        mListView.invalidate();

        Snackbar snackbar = Snackbar.make(mView, "Movie removed", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    //==============================================================================================
    // Interface implementation functions
    //==============================================================================================

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie clickedMovie = (Movie)mAdapter.getItem(position);

        // Get the movie object of realm matching the uuid
        Movie movie = this.movieManager.getMovie(clickedMovie.getUuid());

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(movieUuid, movie.getUuid());
        startActivity(intent);
    }

    //==============================================================================================
    // Context Menu
    //==============================================================================================

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Send to list to see");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Remove this movie");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Send to list to see") {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            sendToListToSee(info.position);

        } else if (item.getTitle() == "Remove this movie") {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            remove(info.position);

        } else {
            return false;
        }
        return true;
    }


}//EOC
