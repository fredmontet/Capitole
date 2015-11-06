package mobop.capitole;

/**
 * Created by fredmontet on 05/11/15.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobop.capitole.FilmFragment;
import mobop.capitole.R;

/**
 * Class for the Tabs on main activity
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    protected Context mContext;
    private static int NUM_ITEMS = 3;

    public TabsPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.mContext = context;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int index) {
        switch (index) {
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

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
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
