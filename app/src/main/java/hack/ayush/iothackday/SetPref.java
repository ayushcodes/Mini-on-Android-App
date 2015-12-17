package hack.ayush.iothackday;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SetPref extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
