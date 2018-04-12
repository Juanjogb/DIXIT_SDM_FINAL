package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;


public class AvatarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, SettingsActivity.class));
        finish();
    }
    public void setAvatar(View v) {
        Intent i = new Intent(this, SettingsActivity.class);
        i.putExtra("avatar", getResources().getResourceEntryName(v.getId()).split("Button")[1]);
        startActivity(i);
        finish();
    }
}
