package hd.wallpapers.pictic.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import hd.wallpapers.pictic.Home.HomeActivity;
import hd.wallpapers.pictic.Message.MessageActivity;
import hd.wallpapers.pictic.Profile.ProfileActivity;
import hd.wallpapers.pictic.R;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, final BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home: //index 0
                        Intent intent1 = new Intent(context, HomeActivity.class); //ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_upload: //index 1
                        //Build a Dialogue or fragment to upload image
                        menuItem.setChecked(true); //ACTIVITY_NUM = 1
                        Toast.makeText(context, "Upload icon Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ic_message: //index 2
                        Intent intent2 = new Intent(context, MessageActivity.class); //ACTIVITY_NUM = 2
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_profile: //index 3
                        Intent intent3 = new Intent(context, ProfileActivity.class); //ACTIVITY_NUM = 3
                        context.startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }
}
