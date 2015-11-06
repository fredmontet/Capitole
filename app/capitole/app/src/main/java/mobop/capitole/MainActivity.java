package mobop.capitole;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The main activity
 */
public class MainActivity extends AppCompatActivity
//        implements ActionBar.TabListener
{

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private ActionBar mActionBar;
    // Tab titles
    private String[] tabs = { "Top Rated", "Games", "Movies" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        mViewPager = (ViewPager) findViewById(R.id.pager);
        //mActionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        mViewPager.setAdapter(mAdapter);
        mActionBar.setHomeButtonEnabled(false);

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
}




