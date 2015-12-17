package hack.ayush.iothackday;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by akpateria on 04-10-2015.
 */

public class WifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(context);
        String ssid1 = prefs.getString("ssid", null);
        Log.d("ssid1", ssid1);

        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if(info != null) {
            if(info.isConnected()) {
                // Do your work.

                // e.g. To check the Network Name or other info:
                WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ssid = wifiInfo.getSSID();
                ssid = ssid.replace("\"","");
                Log.d("ssid", ssid);
                if(ssid1.equals(ssid))
                {
                    // do something here
                    Log.d("connected", ssid1);

                }
            }
        }



    }

}