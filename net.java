package tinesandbarbs.tinesandbarbs;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

class net {
    private Context mContext;
    net(Context context)
    {
        this.mContext=context;
    }
    boolean network()
    {
        WifiManager wifiManager=(WifiManager)mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE) ;
        ConnectivityManager check=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = check.getActiveNetworkInfo();
        return (info.getState() ==( NetworkInfo.State.CONNECTED))|(wifiManager.isWifiEnabled());
    }
}
