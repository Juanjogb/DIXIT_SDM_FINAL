package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;


public class AvatarActivityRegister extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
    public void setAvatar(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        i.putExtra("avatar", getResources().getResourceEntryName(v.getId()).split("Button")[1]);
        i.putExtra("username",prefs.getString("username",null));
        i.putExtra("email",prefs.getString("email",null));
        i.putExtra("password",prefs.getString("password",null));
        startActivity(i);
        finish();
    }
}