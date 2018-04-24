package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dixit.sdm.trabajo.dixit.R;

public class BarajaActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baraja);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, PlayActivity.class);
        i.putExtra("tmp_searchGame",prefs.getString("tmp_searchGame",null));
        startActivity(i);
        finish();
    }

    public void setReverso(View v) {
        Intent i = new Intent(this, PlayActivity.class);
        i.putExtra("reverso", getResources().getResourceEntryName(v.getId()).split("_")[1]);
        i.putExtra("tmp_searchGame",prefs.getString("tmp_searchGame",null));
        startActivity(i);
        finish();
    }
}
