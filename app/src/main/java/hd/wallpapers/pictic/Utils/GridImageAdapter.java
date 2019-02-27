package hd.wallpapers.pictic.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import hd.wallpapers.pictic.R;

public class GridImageAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutResources;
    private String append;
    private ArrayList<String> imagesURL;

    public GridImageAdapter(Context context, int layoutResources, String append, ArrayList<String> imagesURL) {
        super(context, layoutResources, imagesURL);
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        this.layoutResources = layoutResources;
        this.append = append;
        this.imagesURL = imagesURL;
    }

    private static class ViewHolder{
        SquareImageView gridImage;
        ProgressBar gridProgressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        /*
        ViewHolder build pattern (Similar to RecyclerView)
         */

        final ViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(layoutResources, parent, false);
            holder = new ViewHolder();
            holder.gridProgressBar = convertView.findViewById(R.id.gridProgressBar);
            holder.gridImage = convertView.findViewById(R.id.gridImageView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        String imgURL = getItem(position);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(append + imgURL, holder.gridImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (holder.gridProgressBar != null){
                    holder.gridProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (holder.gridProgressBar != null){
                    holder.gridProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (holder.gridProgressBar != null){
                    holder.gridProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (holder.gridProgressBar != null){
                    holder.gridProgressBar.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }
}
