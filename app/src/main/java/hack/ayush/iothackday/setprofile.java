package hack.ayush.iothackday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class setprofile extends AppCompatActivity {

    EditText t1, t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setprofile);
        t1 = (EditText) findViewById(R.id.editText);
        t2 = (EditText) findViewById(R.id.editText3);
    }

    public void continue_click(View v) {


        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(this).edit();


      //  SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("user_name", t1.getText().toString());
        editor.putString("user_email", t2.getText().toString());
        editor.apply();


        Intent mainIntent = new Intent(this, setprofile2.class);
        mainIntent.putExtra("user_name", t1.getText().toString());
        mainIntent.putExtra("user_email", t2.getText().toString());
        startActivity(mainIntent);
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
