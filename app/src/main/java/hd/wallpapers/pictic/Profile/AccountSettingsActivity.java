package hd.wallpapers.pictic.Profile;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import hd.wallpapers.pictic.R;
import hd.wallpapers.pictic.Utils.BottomNavigationViewHelper;
import hd.wallpapers.pictic.Utils.SectionStatePagerAdapter;

public class AccountSettingsActivity extends AppCompatActivity {
    private static final String TAG = "AccountSettingsActivity";

    private Context mContext = AccountSettingsActivity.this;
    private SectionStatePagerAdapter pagerAdapter;
    private static ViewPager mViewPager;
    public static RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        Log.d(TAG, "onCreate: Activity Started");
        mViewPager = findViewById(R.id.container);
        mRelativeLayout = findViewById(R.id.relLayout1);
        setupSettingsList();
        setupBottomNavigationView();
        setupFragments();

        //setup the backarrow to navigate to profile activity on click
        ImageView backarrow = findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Going Back to Profile Activity");
                finish();
            }
        });

    }

    /**
     * Display a corresponding fragment
     */
    private void setupFragments(){
        pagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmentToList(new EditProfileFragment(), getString(R.string.Edit_Profile_Info)); //fragment 0
        pagerAdapter.addFragmentToList(new HelpFragment(), getString(R.string.Help)); //fragment 1
        pagerAdapter.addFragmentToList(new AdsFragment(), getString(R.string.Ads)); //fragment 2
        pagerAdapter.addFragmentToList(new PrivacyPolicyFragment(), getString(R.string.Privacy_Policy)); //fragment 3
        pagerAdapter.addFragmentToList(new TermsOfUseFragment(), getString(R.string.Terms_of_Use)); //fragment 4
    }

    /**
     * Nevigating to different setting fragments
     * @param fragmentNumber
     */
    private void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: Navigating to Fragment Number: " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setVisibility(View.VISIBLE);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public static void removeViewPager(){
        mViewPager.setVisibility(View.GONE);
        mRelativeLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Setup the whole Setting List
     */
    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: Setting up list");
        ExpandableHeightListView listView = findViewById(R.id.listView);
        ExpandableHeightListView listView1 = findViewById(R.id.listView1);
        ExpandableHeightListView listView2 = findViewById(R.id.listView2);
        ExpandableHeightListView listView3 = findViewById(R.id.listView3);

        ArrayList<String> options = new ArrayList<>();
        ArrayList<String> options1 = new ArrayList<>();
        ArrayList<String> options2 = new ArrayList<>();
        ArrayList<String> options3 = new ArrayList<>();

        options.add(getString(R.string.Edit_Profile_Info));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
        listView.setExpanded(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: listView");
                setViewPager(position);
            }
        });

        options1.add(getString(R.string.Help));
        options1.add(getString(R.string.Report_a_Problem));

        ArrayAdapter adapter1 = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options1);
        listView1.setAdapter(adapter1);
        listView1.setExpanded(true);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: listView1");
                if (position == 1){
                    Toast.makeText(mContext, "Report a Problem Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    setViewPager(position+1);
                }
            }
        });

        options2.add(getString(R.string.Ads));
        options2.add(getString(R.string.Privacy_Policy));
        options2.add(getString(R.string.Terms_of_Use));

        ArrayAdapter adapter2 = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options2);
        listView2.setAdapter(adapter2);
        listView2.setExpanded(true);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: listView2");
                setViewPager(position+2);
            }
        });

        options3.add(getString(R.string.Sign_Out));
        options3.add(getString(R.string.Login_with_another_Account));

        ArrayAdapter adapter3 = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options3);
        listView3.setAdapter(adapter3);
        listView3.setExpanded(true);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: listView3");
                Toast.makeText(mContext, "Item Clicked" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * BottomNavigationView Setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
    }
}
