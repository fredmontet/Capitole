package mobop.capitole.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import mobop.capitole.R;
import mobop.capitole.presentation.activity.MovieDetailActivity;


/**
 * Created by fredmontet on 06/11/15.
 */

public class SuggestionFragment extends Fragment {

    String[] movies = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2","Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2","Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2","Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"  };

    View mView;
    ArrayAdapter<String> mAdapter;
    GridView mGridView;
    public final static String movieId = "mobop.capitole.activities.movieId";


    public SuggestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_suggestion, container, false);
        mGridView = (GridView) mView.findViewById(R.id.movie_gridview);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, movies);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence text = ((TextView) view).getText();
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(movieId, text.toString());
                startActivity(intent);
            }
        });

        return mView;
    }
}
