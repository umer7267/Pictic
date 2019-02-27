package hd.wallpapers.pictic.Home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import hd.wallpapers.pictic.R;
import hd.wallpapers.pictic.Utils.BottomNavigationViewHelper;
import hd.wallpapers.pictic.Utils.SectionPagerAdapter;
import hd.wallpapers.pictic.Utils.UniversalImageLoader;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    public static final int ACTIVITY_NUM = 0;

    private Context mContext = HomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: Creating HomeActivity");
        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();
    }

    /**
     * Setting up 3 Tabs: Search, Home, Notification
     */
    private void setupViewPager(){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment()); //index 0
        adapter.addFragment(new HomeFragment()); //index 1
        adapter.addFragment(new NotificationFragment()); //index 2
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_search);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_homelogo);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notification);
    }

    /**
     * BottomNavigationView Setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    /**
     * Initialize ImageLoader settings before loading the image
     */
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
}
