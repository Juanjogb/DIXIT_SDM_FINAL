package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dixit.sdm.trabajo.dixit.R;


public class AvatarActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private String activity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        activity = getIntent().getExtras().get("activity").toString();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onBackPressed() {
        switch (activity){
            case "register":
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case "settings":
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        finish();
    }

    public void setAvatar(View v) {
        Intent i = null;
        switch (activity){
            case "register":
                i = new Intent(this, RegisterActivity.class);

                break;
            case "settings":
                i = new Intent(this, SettingsActivity.class);
                break;
        }
        i.putExtra("tmp_avatar", getResources().getResourceEntryName(v.getId()).split("Button")[1]);
        i.putExtra("tmp_username", prefs.getString("tmp_username", null));
        i.putExtra("tmp_password", prefs.getString("tmp_password", null));
        if(activity.equals("register")) i.putExtra("tmp_email", prefs.getString("tmp_email", null));
        startActivity(i);
        finish();
    }
}
