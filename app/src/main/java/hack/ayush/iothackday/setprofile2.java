package hack.ayush.iothackday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.UUID;

public class setprofile2 extends AppCompatActivity {

    EditText t1, t2;
    TextView tv1;
    Switch s1, s2;
    SeekBar sb1;
    Boolean TV = false;
    Boolean MUSIC = false;
    Spinner spinner1, spinner2;
    int intensity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setprofile2);
  //      t1 = (EditText) findViewById(R.id.editText2);
        t2 = (EditText) findViewById(R.id.editText4);
    //    tv1 = (TextView) findViewById(R.id.textView7);
        s1 = (Switch) findViewById(R.id.switch1);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        s2 = (Switch) findViewById(R.id.switch2);
     //   sb1 = (SeekBar) findViewById(R.id.seekBar);

    /*    sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
        //        Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
          //      Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv1.setText(progress + "/" + seekBar.getMax());
                intensity = progress;
            //    Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });
   */
        //set the switch to ON
        s1.setChecked(false);
        //attach a listener to check for changes in state
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
               TV = true;
                }else{
                TV = false;
                }

            }
        });


        s2.setChecked(false);
        //attach a listener to check for changes in state
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    MUSIC = true;
                }else{
                    MUSIC = false;
                }

            }
        });



    }





    public void save_click(View v) {
Log.d("sdfs", String.valueOf(spinner1.getSelectedItem()));
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(this).edit();
        String ID = UUID.randomUUID().toString();
        editor.putString("temp", String.valueOf(spinner1.getSelectedItem()) );
        editor.putBoolean("TV", TV);
        editor.putBoolean("Music", MUSIC);
        editor.putString("intensity", String.valueOf(spinner2.getSelectedItem()) );
        editor.putString("ssid", t2.getText().toString());
        editor.putString("ID", ID);
        editor.putBoolean("active", true);
        editor.putBoolean("discovered", false);
        editor.apply();

/*

        SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(this);
        final  String name = prefs.getString("user_name", null);
        final    String email = prefs.getString("user_email", null);
      final String ID1 = ID;
        final   String temp = prefs.getString("temp", null);
        final   Boolean tv = prefs.getBoolean("TV", false);
        final   Boolean ms = prefs.getBoolean("Music", false);
        final     String intensity = prefs.getString("intensity", null);

        final     String ssid = prefs.getString("ssid", null);

*/
        Intent mainIntent = new Intent(this, dashboard.class);
        startActivity(mainIntent);
        finish();


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

        return super.onOptionsItemSelected(item);
    }
}
