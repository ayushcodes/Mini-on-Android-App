// IoTHackDay Project - Made by Team IITH
// App by : Ayush Pateria

import android.support.v7.app.AppCompatActivity;

/*
package hack.ayush.iothackday;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class control_ extends AppCompatActivity {

    Switch[] s = new Switch[10];
    Boolean[] state = new Boolean[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        s[1] = (Switch) findViewById(R.id.sw1);
        s[2] = (Switch) findViewById(R.id.sw2);
        s[3] = (Switch) findViewById(R.id.sw3);
        s[4] = (Switch) findViewById(R.id.sw4);
        s[5] = (Switch) findViewById(R.id.sw5);
        s[6] = (Switch) findViewById(R.id.sw6);
        s[7] = (Switch) findViewById(R.id.sw7);
        s[8] = (Switch) findViewById(R.id.sw8);
        s[9] = (Switch) findViewById(R.id.sw9);

        state[1] = true;
        state[2] = true;
        state[3] = true;
        state[4] = false;
        state[5] = false;
        state[6] = false;
        state[7] = false;
        state[8] = false;
        state[9] = false;

        for(int i = 1; i < 10; i++) {
            final int j = i;


            s[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    if (isChecked) {
                        state[j] = true;
                    } else {
                        state[j] = false;
                    }

                }
            });

        }

    }

    public void projector_click(View v) {



    }

    public  void submit_click(View v) {


        SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(this);
        final  String IP = prefs.getString("IP", null);

        final  String port = prefs.getString("port", null);


        Toast.makeText(this, "Running", Toast.LENGTH_LONG).show();

        new Thread(new Runnable() {
            public void run() {


                try {

                    String outMsg = "";



                    for(int i = 1; i < 10; i++) {
                        if (state[i] == true)
                            outMsg += s[i].getText() + ",";
                    }

                    try {
                        outMsg = outMsg.substring(0, outMsg.length()-1);
                    } catch (Exception e) {
                       outMsg += "null";
                    }

                    outMsg += ";";
                    for(int i = 1; i < 4; i++) {
                        if (state[i] == false)
                            outMsg += s[i].getText() + ",";
                    }

                    if(outMsg.charAt(outMsg.length() - 1) == ';')
                        outMsg = outMsg.substring(0, outMsg.length()-1) + ";null" + System.getProperty("line.separator");
                    else
                        outMsg = outMsg.substring(0, outMsg.length()-1) + System.getProperty("line.separator");

                    Log.d("IP:Post", IP+":"+port);

                    Log.d("outMsg", outMsg);
                    Socket s = new Socket(IP, Integer.valueOf(port));

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

                } catch (UnknownHostException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }
        }).start();



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

//        if (id == R.id.action_stat) {
  //          Intent mainIntent = new Intent(this, stats.class);
    //        startActivity(mainIntent);
      //      return true;

       // }

        return super.onOptionsItemSelected(item);
    }
}
*/