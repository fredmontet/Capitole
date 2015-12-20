package mobop.capitole.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;

import mobop.capitole.Capitole;
import mobop.capitole.R;
import mobop.capitole.domain.MovieManager;
import mobop.capitole.domain.model.Movie;
import mobop.capitole.domain.model.User;
import mobop.capitole.presentation.activity.MovieDetailActivity;
import mobop.capitole.presentation.adapter.MovieListAdapter;

/**
 * Created by fredmontet on 06/11/15.
 */

public class SuggestionFragment extends Fragment implements AdapterView.OnItemClickListener{


    View mView;
    private MovieListAdapter mAdapter;
    GridView mGridView;
    public final static String movieId = "mobop.capitole.activities.movieId";


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
        mGridView = (GridView) mView.findViewById(R.id.suggestionGridview);

        User user = Capitole.getInstance().getUser();
        MovieManager movieManager = new MovieManager(getContext(), user);

        try {
            movieManager.getSuggestion(new MovieManager.MovieManagerCallback() {
                @Override
                public void onSuccess(List<Movie> response) {

                    //This is the ListView adapter
                    mAdapter = new MovieListAdapter(getActivity());
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


        // Inflate the layout for this fragment
        return mView;
    }

    //==============================================================================================
    // Functions
    //==============================================================================================

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie modifiedMovie = (Movie)mAdapter.getItem(position);

        // Get the movie object of realm matching the uuid
        //TODO Get the movie information from the online API

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        //TODO Send the informations in the input extra
        //intent.putExtra(movieUuid, movie.getUuid());
        startActivity(intent);
    }


}//EOC
