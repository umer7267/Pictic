package hd.wallpapers.pictic.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import hd.wallpapers.pictic.R;

public class UniversalImageLoader {
    private static final int DEFAULTIMAGE = R.drawable.profilepicdemo;
    private Context mContext;

    public UniversalImageLoader(Context context) {
        mContext = context;
    }

    public ImageLoaderConfiguration getConfig(){

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(DEFAULTIMAGE)
                .showImageForEmptyUri(DEFAULTIMAGE)
                .showImageOnFail(DEFAULTIMAGE)
                .cacheOnDisk(true).cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100*1024*1024).build();

        return configuration;
    }

    /**
     * Use to load Profile Image from the Database
     * Don't be used to set images in gridview or listview
     * @param imgURL
     * @param imageView
     * @param append
     * @param progressBar
     */
    public static void setProfileImage(String imgURL, ImageView imageView, String append, final ProgressBar progressBar){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(append + imgURL, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (progressBar != null){
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
