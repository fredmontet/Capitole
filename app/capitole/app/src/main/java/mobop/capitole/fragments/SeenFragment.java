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

import mobop.capitole.R;
import mobop.capitole.activities.MovieActivity;

/**
 * Created by fredmontet on 06/11/15.
 */

public class SeenFragment extends Fragment {

    String[] movies = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2","Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };

    View mView;
    ArrayAdapter<String> mAdapter;
    ListView mListView;
    FloatingActionButton mFabSeen;
    public final static String movieId = "mobop.capitole.activities.movieId";


    public SeenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_seen, container, false);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, movies);
        mListView = (ListView)mView.findViewById(R.id.seenListview);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence text = ((TextView) view).getText();
                Intent intent = new Intent(getActivity(), MovieActivity.class);
                intent.putExtra(movieId, text.toString());
                startActivity(intent);
            }
        });

        FloatingActionButton fabSeen = (FloatingActionButton) mView.findViewById(R.id.fabSeen);
        fabSeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hello World", Toast.LENGTH_SHORT).show();
            }
        });


        // Inflate the layout for this fragment
        return mView;
    }
}
