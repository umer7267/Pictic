package hd.wallpapers.pictic.Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import hd.wallpapers.pictic.R;
import hd.wallpapers.pictic.Utils.BottomNavigationViewHelper;
import hd.wallpapers.pictic.Utils.GridImageAdapter;
import hd.wallpapers.pictic.Utils.UniversalImageLoader;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 3;
    public static final int NUM_OF_GRID_COL = 3;

    private Context mContext = ProfileActivity.this;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: Starting ProfileActivity");
        setStatusBarColor();
        setupBottomNavigationView();
        setProfileActivityWidgets();
        setProfilePhoto();
        tempGridViewSetup();

        //Open AccountSettingActivity on click in setting icon
        ImageButton settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Opening Setting Activity");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Set the Profile image of the user in the profile activity
     */
    public void setProfilePhoto(){
        Log.d(TAG, "setProfilePhoto: setting Profile Image");
        String imageURL = "https://cdn.pixabay.com/photo/2018/05/03/21/49/android-3372580_960_720.png";
        UniversalImageLoader.setProfileImage(imageURL, profilePhoto, "", null);
    }

    public void setImagesGrid(ArrayList<String> imagesURL){
        ExpandableHeightGridView gridView = findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int perImageWidth = gridWidth/NUM_OF_GRID_COL;
        gridView.setColumnWidth(perImageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imagesURL);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
    }

    private void tempGridViewSetup(){
        ArrayList<String> imagesURL = new ArrayList<>();
        imagesURL.add("https://cdn.pixabay.com/photo/2019/02/22/12/26/water-4013446__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2017/09/03/21/42/butterfly-2712149__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2017/06/10/01/22/horse-2388778__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2018/11/17/17/10/mountains-3821487__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2017/02/19/14/30/water-2079907__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2015/01/13/14/53/urban-598307__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2018/05/01/07/52/landscape-3364921__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2013/10/09/02/26/bridge-192986__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2019/01/20/05/30/guitar-3943201__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2016/12/27/22/54/lizard-1935081__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2018/01/12/10/19/fantasy-3077928__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2016/11/08/05/26/woman-1807533__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2017/05/11/08/48/model-2303361__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2016/10/12/02/56/girl-1733352__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2017/01/20/18/25/girl-looking-away-1995624__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2018/03/12/12/32/woman-3219507__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2012/05/29/00/43/car-49278__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2015/05/28/23/12/auto-788747__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2017/12/28/23/41/car-3046424__340.jpg");
        imagesURL.add("https://cdn.pixabay.com/photo/2017/09/01/20/23/ford-2705402__340.jpg");

        setImagesGrid(imagesURL);
    }

    public void setProfileActivityWidgets(){
        profilePhoto = findViewById(R.id.ProfilePhoto);
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
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    /**
     * Change Status Bar Color
     */
    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window w = getWindow();
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            w.setStatusBarColor(getColor(R.color.profile_statusbar));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
