package hd.wallpapers.pictic.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import hd.wallpapers.pictic.Home.HomeActivity;

public class CheckInternetAsync extends AsyncTask<String, Void, Integer> {

    Context context;

    public CheckInternetAsync(Context context){
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress("www.google.com", 80), 1500);
                return 1;
            } catch (IOException e) {
                // Either we have a timeout or unreachable host or failed DNS lookup
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1){
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
        else if (integer == 0){
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
    }
}
