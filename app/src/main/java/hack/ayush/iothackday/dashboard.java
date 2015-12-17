// IoTHackDay Project - Made by Team IITH
// App by : Ayush Pateria

package hack.ayush.iothackday;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class dashboard extends AppCompatActivity {

TextView tv1, tv2;
    Button smart, run;
    Boolean active1 = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        //send output msg
        SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(this);
        final  String name = prefs.getString("user_name", null);
        final    String email = prefs.getString("user_email", null);
        final    String ID = prefs.getString("ID", null);
        final   String temp = prefs.getString("temp", null);
        final   Boolean tv = prefs.getBoolean("TV", false);
        final   Boolean active = prefs.getBoolean("active", true);
        active1 = active;
        final   Boolean ms = prefs.getBoolean("Music", false);
        final     String intensity = prefs.getString("intensity", null);

     //   final     String ssid = prefs.getString("ssid", null);


        final String TAG = "myapp";

        final WifiManager mWifiManager = (WifiManager) getSystemService(WIFI_SERVICE);




        if(mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {

            // register WiFi scan results receiver
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

            registerReceiver(new BroadcastReceiver() {
                @Override


                public void onReceive(Context context, Intent intent) {

                    SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(context);
                  String  ssid = prefs.getString("ssid", null).trim();
                     Boolean disc = prefs.getBoolean("discovered", false);
                    Boolean found = false;


    List<ScanResult> results = mWifiManager.getScanResults();
                    final int N = results.size();

                    Log.v(TAG, "Wi-Fi Scan Results ... Count:" + N);
                    Log.v(TAG, "disc and found" + disc.toString()+found.toString());

                    try {
                        for (int i = 0; i < N; ++i) {

                            if (ssid.equals(results.get(i).SSID)) {
                            found = true;
                            break;
                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(disc == false && found == true) {
                        SharedPreferences.Editor editor = PreferenceManager
                                .getDefaultSharedPreferences(context).edit();

                        editor.putBoolean("discovered", true);
                        editor.apply();



                        Log.d("hello123", "the code will run here");
                         String name = prefs.getString("user_name", null);
                            String email = prefs.getString("user_email", null);
                            String ID = prefs.getString("ID", null);
                           String temp = prefs.getString("temp", null);
                           Boolean tv = prefs.getBoolean("TV", false);
                           Boolean ms = prefs.getBoolean("Music", false);
                             String intensity = prefs.getString("intensity", null);



if(active1) {

    try {

        Socket s = new Socket("192.168.6.119", 11000);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));


        String outMsg = ID + "," + name + "," + email + "," + temp + "," + tv + "," + ms + "," + intensity + "," + ssid + System.getProperty("line.separator");

        out.write(outMsg);

        out.flush();

        Log.i("TcpClient", "sent: " + outMsg);

        //accept server response

        String inMsg = in.readLine() + System.getProperty("line.separator");

        Log.i("TcpClient", "received: " + inMsg);

        //close connection

        s.close();

    } catch (UnknownHostException e) {

        e.printStackTrace();

    } catch (IOException e) {

        e.printStackTrace();

    }

}


                    }

                    if(disc == true && found == false) {
                        SharedPreferences.Editor editor = PreferenceManager
                                .getDefaultSharedPreferences(context).edit();

                        editor.putBoolean("discovered", false);
                        editor.apply();


                        Log.d("hello123", "the user is out");

                        // The user is out

                    }




                }



            }, filter);
                         // start WiFi Scan
        //    mWifiManager.startScan();

        }
        tv1 = (TextView) findViewById(R.id.textView10);
        tv2 = (TextView) findViewById(R.id.textView12);
        smart = (Button) findViewById(R.id.button3);
        tv1.setText("Hello, " + name);
        if(!active1) {
            tv2.setText("ain't smart :(");
            smart.setText("SMARTIFY IT!");
        }
    }


    public  void runtrig(View v) {
        SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(this);
        final  String name = prefs.getString("user_name", null);
        final    String email = prefs.getString("user_email", null);
        final    String ID = prefs.getString("ID", null);
        final   String temp = prefs.getString("temp", null);
        final   Boolean tv = prefs.getBoolean("TV", false);
        final   Boolean ms = prefs.getBoolean("Music", false);
        final     String intensity = prefs.getString("intensity", null);

        final     String ssid = prefs.getString("ssid", null);


        Toast.makeText(this, "Running", Toast.LENGTH_LONG).show();

        new Thread(new Runnable() {
            public void run() {


                try {

                    Socket s = new Socket("192.168.6.119", 11000);

                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));





                    String outMsg =  ID+","+name+","+email+","+temp+","+tv+","+ms+","+intensity+","+ssid+System.getProperty("line.separator");

                    out.write(outMsg);

                    out.flush();

                    Log.i("TcpClient", "sent: " + outMsg);

                    //accept server response

                    String inMsg = in.readLine() + System.getProperty("line.separator");

                    Log.i("TcpClient", "received: " + inMsg);

                    //close connection

                    s.close();

                } catch (UnknownHostException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }
        }).start();



    }


    public void unsmart(View v) {
        if(active1 == true)
        {
        active1 = false;
            tv2.setText("ain't smart :(");
            smart.setText("SMARTIFY IT!");

        }
        else
        {
            active1 = true;
            tv2.setText("is smartified! Wohoo!");
            smart.setText("UNSMART MY HOME");
        }


        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(this).edit();
        editor.putBoolean("active", active1);
        editor.apply();


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
