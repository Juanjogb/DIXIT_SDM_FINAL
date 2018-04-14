package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;


public class AvatarActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, SettingsActivity.class));
        finish();
    }
    public void setAvatar(View v) {
        Intent i = new Intent(this, SettingsActivity.class);
        i.putExtra("avatar", getResources().getResourceEntryName(v.getId()).split("Button")[1]);
        i.putExtra("username",prefs.getString("tmp_username",null));
        i.putExtra("password",prefs.getString("tmp_password",null));
        startActivity(i);
        finish();
    }
}
