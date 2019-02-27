package hd.wallpapers.pictic.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import hd.wallpapers.pictic.R;
import hd.wallpapers.pictic.Utils.SectionStatePagerAdapter;
import hd.wallpapers.pictic.Utils.UniversalImageLoader;

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";

    private ImageView profileImage;
    private CoordinatorLayout editProfileFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        profileImage = view.findViewById(R.id.ProfilePhoto);
        setProfileImage();

        //back arrow to navigating back to account settings
        ImageView backarrow = view.findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to account settings");
                AccountSettingsActivity.removeViewPager();
            }
        });
        return view;
    }

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: Setting Profile Image");
        String imageURL = "https://cdn.pixabay.com/photo/2018/05/03/21/49/android-3372580_960_720.png";
        UniversalImageLoader.setProfileImage(imageURL, profileImage, "", null);
    }

}
