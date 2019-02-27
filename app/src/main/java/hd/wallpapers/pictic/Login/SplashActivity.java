package hd.wallpapers.pictic.Login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import hd.wallpapers.pictic.R;
import hd.wallpapers.pictic.Utils.CheckInternetAsync;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hideStatusBar();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new CheckInternetAsync(SplashActivity.this).execute();
            }
        }, 2000);
    }

    public void hideStatusBar(){
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
