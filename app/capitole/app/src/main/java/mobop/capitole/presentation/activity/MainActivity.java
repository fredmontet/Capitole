package mobop.capitole.presentation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mobop.capitole.R;
import mobop.capitole.presentation.adapter.ViewPagerAdapter;
import mobop.capitole.presentation.fragment.SeenFragment;
import mobop.capitole.presentation.fragment.SuggestionFragment;
import mobop.capitole.presentation.fragment.ToSeeFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SeenFragment(), getString(R.string.seen_title));
        adapter.addFragment(new SuggestionFragment(), getString(R.string.suggestion_title));
        adapter.addFragment(new ToSeeFragment(), getString(R.string.tosee_title));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1); // Set suggestionFragment when app is opening
    }


    //==============================================================================================
    // Functions
    //==============================================================================================




}
