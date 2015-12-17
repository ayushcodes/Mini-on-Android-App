// IoTHackDay Project - Made by Team IITH
// App by : Ayush Pateria

package hack.ayush.iothackday;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class control extends AppCompatActivity {

   FrameLayout room, power, fan, settings;
    int fanspeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

       room = (FrameLayout) findViewById(R.id.FrameLayout3);
        power = (FrameLayout) findViewById(R.id.FrameLayout4);
        fan = (FrameLayout) findViewById(R.id.FrameLayout5);

        settings = (FrameLayout) findViewById(R.id.FrameLayout6);

        SeekBar fanSpeed = (SeekBar) findViewById(R.id.seekBar);

        fanSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(control.this, "Fan Speed: " + progressChanged,
                        Toast.LENGTH_SHORT).show();
                fanspeed=progressChanged;
            }
        });

    }


    public void room_click(View v) {
        int color = Color.TRANSPARENT;
        Drawable background = room.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if (color == Color.parseColor("#ffdb00"))
        {
            room.setBackgroundColor(Color.GREEN);
        }
        else
        {
            room.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }
    public void power_click(View v) {

        int color = Color.TRANSPARENT;
        Drawable background = power.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if(color == Color.parseColor("#ffdb00"))
        {
            power.setBackgroundColor(Color.GREEN);
        }
        else
        {
            power.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();


    }
    public void fan_click(View v) {


        int color = Color.TRANSPARENT;
        Drawable background = fan.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if(color == Color.parseColor("#ffdb00"))
        {
            fan.setBackgroundColor(Color.GREEN);
        }
        else
        {
            fan.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }  public void settings_click(View v) {

        Intent mainIntent = new Intent(this, SetPref.class);
        startActivity(mainIntent);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void send_req()
    {


        SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(this);
        final  String IP = prefs.getString("IP", null);

        final  String port = prefs.getString("port", null);



        new Thread(new Runnable() {
            public void run() {


                try {

                    String outMsg = "";
                    String true_comp = "";
                    String false_comp = "";



                    int color = Color.TRANSPARENT;
                    Drawable background = fan.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "fan,";
                    }
                    else
                    {
                        true_comp += "fan-"+fanspeed+",";
                    }


                    color = Color.TRANSPARENT;
                    background = room.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "room-light,";
                    }
                    else
                    {
                        true_comp += "room-light,";
                    }


                    color = Color.TRANSPARENT;
                     background = power.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "power-switch,";
                    }
                    else
                    {
                        true_comp += "power-switch,";
                    }


                    if(true_comp == "")
                        true_comp = "null";
                    else
                        true_comp = true_comp.substring(0, true_comp.length()-1);
                    if(false_comp == "")
                        false_comp = "null";
                    else
                        false_comp = false_comp.substring(0, false_comp.length()-1);

                    outMsg = true_comp+";"+false_comp+ System.getProperty("line.separator");
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
