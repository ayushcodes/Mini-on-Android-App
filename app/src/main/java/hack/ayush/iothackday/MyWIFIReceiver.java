package hack.ayush.iothackday;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class MyWIFIReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

/*

        Intent i1 = new Intent();
        i1.setClassName("hack.ayush.iothackday", "hack.ayush.iothackday.control");
        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i1);
*/


        WifiManager mWifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);

        String ssid = mWifiManager.getConnectionInfo().getSSID();
        List<ScanResult> results = mWifiManager.getScanResults();
        final int N = results.size();

        Log.v("Minion", "Wi-Fi Scan Results ... Count:" + N);
        JSONArray wifiRes = new JSONArray();
        try {
            for (int i = 0; i < 5; ++i) {
                JSONObject wifi = new JSONObject();
                wifi.put("SSID", results.get(i).SSID);
                wifi.put("BSSID", results.get(i).BSSID);
                wifi.put("level", results.get(i).level);
                if (ssid.replace("\"", "").equals(results.get(i).SSID))
                    wifi.put("status", "connected");
                else
                    wifi.put("status", "disconnected");
                wifiRes.put(wifi);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("json", wifiRes.toString());

        // Send this JSON to server.

        // Receive a response and open control activity. Send BSSID ID and UI elements in bundle.


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final String IP = prefs.getString("IP", null);

        //final String port = prefs.getString("port", null);
        final String port = "12346";
        if (N != 0) {


            try {

                String outMsg = wifiRes.toString() + System.getProperty("line.separator");
                ;


                Log.d("IP:Post", IP + ":" + port);

                Log.d("outMsg", outMsg);
                Socket s = new Socket(IP, Integer.valueOf(port));
s.setSoTimeout(3*1000);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));


                out.write(outMsg);

                out.flush();

                Log.i("TcpClient", "sent: " + outMsg);

                //accept server response

                String inMsg = in.readLine() + System.getProperty("line.separator");

                Log.i("TcpClient", "received: " + inMsg);

                //close connection

                s.close();

                if(inMsg.equals("80:e0:1d:85:6d:80"))
                {
                    Notification(context, "You have been connected to your room.");
                    Intent mainIntent = new Intent(context, control.class);
                    context.startActivity(mainIntent);
             //       Intent i = new Intent();
              //      i.setClassName("hack.ayush.iothackday", "hack.ayush.iothackday.control");
              //      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               //     context.startActivity(i);
                }

            } catch (UnknownHostException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }



        }


    }


/*
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("www.example.com");


        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("username", "test_user"));


        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }

*/

    public void Notification(Context context, String message) {




        // Set Notification Title
        String strtitle = "Mini-on";
        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(context, control.class);
        // Send data to NotificationView Class
        intent.putExtra("title", strtitle);
        intent.putExtra("text", message);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context)


                        // Set Ticker Message
                .setTicker(message)
                        // Set Title
                .setContentTitle("Mini-on")
                        // Set Text
                .setContentText(message)
                        // Add an Action Button below Notification
                .addAction(R.drawable.homeicon, "Mini-on", pIntent)
                        // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                        // Dismiss Notification
                .setAutoCancel(true);

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());

    }


}




