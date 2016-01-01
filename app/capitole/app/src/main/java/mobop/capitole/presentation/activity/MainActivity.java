package mobop.capitole.presentation.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import mobop.capitole.R;
import mobop.capitole.presentation.adapter.ViewPagerAdapter;
import mobop.capitole.presentation.fragment.SeenFragment;
import mobop.capitole.presentation.fragment.SuggestionFragment;
import mobop.capitole.presentation.fragment.ToSeeFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    public final static String SWITCH_TAB = "mobop.capitole.activities.switch_tab";

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        final Intent intent = getIntent();

        // SWITCH_TAB Intent
        //==================
        if (intent.hasExtra(SWITCH_TAB)) {
            final int tab = intent.getIntExtra(SWITCH_TAB, 0);
            switchToTab(mViewPager, tab); // switch to tab2 in this example
        }
    }

    //==============================================================================================
    // Functions
    //==============================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //return true;
        return super.onCreateOptionsMenu(menu);
    }

    private void switchToTab(ViewPager viewPager, int tab) {
        viewPager.setCurrentItem(tab);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SuggestionFragment(), getString(R.string.suggestion_title));
        adapter.addFragment(new ToSeeFragment(), getString(R.string.tosee_title));
        adapter.addFragment(new SeenFragment(), getString(R.string.seen_title));
        viewPager.setAdapter(adapter);
    }

}
