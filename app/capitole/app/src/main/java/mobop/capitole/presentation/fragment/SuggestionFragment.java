package mobop.capitole.presentation.fragment;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.List;

import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.MovieManager;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.User;
import mobop.capitole.presentation.activity.SuggestionDetailActivity;
import mobop.capitole.presentation.adapter.MovieGridAdapter;

/**
 * Created by fredmontet on 06/11/15.
 */

public class SuggestionFragment extends Fragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    private View mView;
    private MovieGridAdapter mAdapter;
    private GridView mGridView;
    private SwipeRefreshLayout swipeRefreshLayout;
    public final static String tmdbId = "mobop.capitole.activities.movieTmdbId";

    public SuggestionFragment() {
        // Required empty public constructor
    }

    //==============================================================================================
    // Life Cycle
    //==============================================================================================


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_suggestion, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);
        mGridView = (GridView) mView.findViewById(R.id.suggestionGridview);

        swipeRefreshLayout.setOnRefreshListener(this);

        Intent intent = getActivity().getIntent();

        // ACTION_SEARCH Intent
        //=====================
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchMovies(query);
        }else {
            getMoviesSuggestion();
        }

        // Inflate the layout for this fragment
        return mView;
    }

    //==============================================================================================
    // Interface implemented functions
    //==============================================================================================

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie clickedMovie = (Movie)mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), SuggestionDetailActivity.class);
        intent.putExtra(tmdbId, clickedMovie.getTmdbID());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getMoviesSuggestion();
        swipeRefreshLayout.setRefreshing(false);

    }

    //==============================================================================================
    // Functions
    //==============================================================================================

    public void getMoviesSuggestion(){
        
        User user = Capitole.getInstance().getUser();
        MovieManager movieManager = new MovieManager(getContext(), user);

        try {
            movieManager.getSuggestion(new MovieManager.MovieManagerCallback() {

                @Override
                public void onSuccess(List<Movie> response) {

                    //This is the ListView adapter
                    mAdapter = new MovieGridAdapter(getActivity());
                    mAdapter.setData(response);

                    //This is the ListView which will display the list of movies
                    mGridView = (GridView)mView.findViewById(R.id.suggestionGridview);
                    mGridView.setAdapter(mAdapter);
                    mGridView.setOnItemClickListener(SuggestionFragment.this);
                    mAdapter.notifyDataSetChanged();
                    mGridView.invalidate();
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }

    public void searchMovies(String query){

        User user = Capitole.getInstance().getUser();
        MovieManager movieManager = new MovieManager(getContext(), user);

        try {
            movieManager.searchMovieFromApi(query, new MovieManager.MovieManagerCallback() {

                @Override
                public void onSuccess(List<Movie> response) {

                    //This is the ListView adapter
                    mAdapter = new MovieGridAdapter(getActivity());
                    mAdapter.setData(response);

                    //This is the ListView which will display the list of movies
                    mGridView = (GridView)mView.findViewById(R.id.suggestionGridview);
                    mGridView.setAdapter(mAdapter);
                    mGridView.setOnItemClickListener(SuggestionFragment.this);
                    mAdapter.notifyDataSetChanged();
                    mGridView.invalidate();
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}//EOC
