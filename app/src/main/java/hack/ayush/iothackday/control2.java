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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class control2 extends AppCompatActivity {

   FrameLayout light1, light2, ac1, ac2, door, projector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control2);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

       light1  = (FrameLayout) findViewById(R.id.light1);
        light2 = (FrameLayout) findViewById(R.id.light2);
        ac1 = (FrameLayout) findViewById(R.id.ac1);
        ac2 = (FrameLayout) findViewById(R.id.ac2);
        door = (FrameLayout) findViewById(R.id.door);
        projector = (FrameLayout) findViewById(R.id.proj);




    }


    public void light1_click(View v) {
        int color = Color.TRANSPARENT;
        Drawable background = light1.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if (color == Color.parseColor("#ffdb00"))
        {
            light1.setBackgroundColor(Color.GREEN);
        }
        else
        {
            light1.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }

    public void light2_click(View v) {
        int color = Color.TRANSPARENT;
        Drawable background = light2.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if (color == Color.parseColor("#ffdb00"))
        {
            light2.setBackgroundColor(Color.GREEN);
        }
        else
        {
            light2.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }

    public void ac1_click(View v) {
        int color = Color.TRANSPARENT;
        Drawable background = ac1.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if (color == Color.parseColor("#ffdb00"))
        {
            ac1.setBackgroundColor(Color.GREEN);
        }
        else
        {
            ac1.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }


    public void ac2_click(View v) {
        int color = Color.TRANSPARENT;
        Drawable background = ac2.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if (color == Color.parseColor("#ffdb00"))
        {
            ac2.setBackgroundColor(Color.GREEN);
        }
        else
        {
            ac2.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }


    public void door_click(View v) {
        int color = Color.TRANSPARENT;
        Drawable background = door.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if (color == Color.parseColor("#ffdb00"))
        {
            door.setBackgroundColor(Color.GREEN);
        }
        else
        {
            door.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }


    public void projector_click(View v) {
        int color = Color.TRANSPARENT;
        Drawable background = projector.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        if (color == Color.parseColor("#ffdb00"))
        {
            projector.setBackgroundColor(Color.GREEN);
        }
        else
        {
            projector.setBackgroundColor(Color.parseColor("#ffdb00"));
        }

        send_req();
    }
    public void settings_click(View v) {

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
                    Drawable background = light1.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "light1,";
                    }
                    else
                    {
                        true_comp += "light1,";
                    }

                    color = Color.TRANSPARENT;
                    background = light2.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "light2,";
                    }
                    else
                    {
                        true_comp += "light2,";
                    }

                    color = Color.TRANSPARENT;
                    background = ac1.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "ac1,";
                    }
                    else
                    {
                        true_comp += "ac1,";
                    }

                    color = Color.TRANSPARENT;
                    background = ac2.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "ac2,";
                    }
                    else
                    {
                        true_comp += "ac2,";
                    }


                    color = Color.TRANSPARENT;
                    background = door.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "door,";
                    }
                    else
                    {
                        true_comp += "door,";
                    }


                    color = Color.TRANSPARENT;
                    background = projector.getBackground();
                    if (background instanceof ColorDrawable)
                        color = ((ColorDrawable) background).getColor();
                    if(color == Color.parseColor("#ffdb00"))
                    {
                        false_comp += "projector,";
                    }
                    else
                    {
                        true_comp += "projector,";
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
