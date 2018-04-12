package dixit.sdm.trabajo.dixit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.SaveChangesTask;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class SettingsActivity extends AppCompatActivity {
    private TextView email;
    private EditText username;
    private EditText password;
    private ImageButton avatar;
    private String avatarToSave;
    private ProgressDialog progressDialog;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String arUsername = "";
    private String arPassword = "";
    private String arAvatar = "";
    private String saved_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        email = findViewById(R.id.settings_email);
        username = findViewById(R.id.settings_username);
        password = findViewById(R.id.settings_password);
        avatar = findViewById(R.id.settings_avatar);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        String saved_username = prefs.getString("username", null);
        saved_email = prefs.getString("email", null);
        email.setText(saved_email);
        username.setText(saved_username);
        progressDialog = new ProgressDialog(this);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            avatarToSave = b.getString("avatar");
            avatar.setImageResource(getResources().getIdentifier("ava" + avatarToSave, "drawable", getPackageName()));
        } else {
            String saved_avatar = prefs.getString("avatar", null);
            avatar.setImageResource(getResources().getIdentifier("ava" + saved_avatar, "drawable", getPackageName()));
            avatarToSave = saved_avatar;
        }
    }

    public void changeAvatar(View v) {
        startActivity(new Intent(this, AvatarActivity.class));
        finish();
    }

    public void processFinish(String output) {
        progressDialog.dismiss();
        editor.putString("username", arUsername);
        editor.putString("avatar", arAvatar);
        editor.apply();
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
    }

    public void saveChanges(View v) {
        progressDialog.setMessage(getString(R.string.settings_saving));
        progressDialog.show();
        String urlBuild = "";
        arUsername = username.getText().toString();
        arPassword = password.getText().toString();
        arAvatar = avatarToSave;
        urlBuild = "saveChanges.php?email=" + saved_email + "&ticket=" + prefs.getString("ticket", "") + "&username=" + arUsername + "&password=" + arPassword + "&avatar=" + avatarToSave;
        new SaveChangesTask(this).execute(new String[]{"GET", BASE_URL + urlBuild});

    }
}