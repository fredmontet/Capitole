package mobop.capitole;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The main activity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    ViewPager mViewPager;
    FragmentPagerAdapter adapterViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new TabsPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(adapterViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Inner class for the Tabs on main activity
     */
    public static class TabsPagerAdapter extends FragmentPagerAdapter {
        protected Context mContext;
        private static int NUM_ITEMS = 3;

        public TabsPagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            this.mContext = context;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FilmFragment.newInstance(0);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FilmFragment.newInstance(1);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return FilmFragment.newInstance(2);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return mContext.getResources().getString(R.string.tab_seen);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return mContext.getResources().getString(R.string.tab_suggestion);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return mContext.getResources().getString(R.string.tab_tosee);
                default:
                    return null;
            }
        }

    }
}




