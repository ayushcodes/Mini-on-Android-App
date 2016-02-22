// IoTHackDay Project - Made by Team IITH
// App by : Ayush Pateria

package hack.ayush.iothackday;


import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class dashb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashb);

/*
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        final String TAG = "myapp";

        final WifiManager mWifiManager = (WifiManager) getSystemService(WIFI_SERVICE);




        if(mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {

            // register WiFi scan results receiver
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

            registerReceiver(new BroadcastReceiver() {
                @Override


                public void onReceive(Context context, Intent intent) {

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    String ssid = getWifiName(context);
                    List<ScanResult> results = mWifiManager.getScanResults();
                    final int N = results.size();

                    Log.v(TAG, "Wi-Fi Scan Results ... Count:" + N);
                    JSONArray wifiRes = new JSONArray();
                    try {
                        for (int i = 0; i < N; ++i) {
                            JSONObject wifi = new JSONObject();
                            wifi.put("SSID", results.get(i).SSID);
                            wifi.put("BSSID", results.get(i).BSSID);
                            wifi.put("level", results.get(i).level);
                            if(ssid.replace("\"","").equals(results.get(i).SSID))
                                wifi.put("status", "connected");
                            else
                                wifi.put("status", "disconnected");
                            wifiRes.put(wifi);


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("json", wifiRes.toString());

                }


            }, filter);
            // start WiFi Scan
            //    mWifiManager.startScan();
*/
        }



    public String getWifiName(Context context) {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (manager.isWifiEnabled()) {
            WifiInfo wifiInfo = manager.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    return wifiInfo.getSSID();
                }
            }
        }
        return null;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent mainIntent = new Intent(this, SetPref.class);
            startActivity(mainIntent);
            return true;

        }
/*
        if (id == R.id.action_stat) {
            Intent mainIntent = new Intent(this, stats.class);
            startActivity(mainIntent);
            return true;

        }
  */
        return super.onOptionsItemSelected(item);
    }
}
