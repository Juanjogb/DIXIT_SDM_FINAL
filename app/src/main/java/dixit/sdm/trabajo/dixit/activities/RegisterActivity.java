package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.ImageButton;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.LoginTask;
import dixit.sdm.trabajo.dixit.helpers.RegisterTask;
import dixit.sdm.trabajo.dixit.helpers.SHA1;
import dixit.sdm.trabajo.dixit.helpers.SaveChangesTask;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class RegisterActivity extends AppCompatActivity {

    //Referencias a los campos del register activity
    private EditText get_username;
    private EditText get_email;
    private EditText get_password;
    private ImageButton get_avatar;

    //Campos del register activity
    private String username;
    private String email;
    private String password;
    private String avatar;

    //Campos de sharedpreferences
    private String tmp_username;
    private String tmp_email;
    private String tmp_password;
    private String tmp_avatar;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        get_username = findViewById(R.id.register_username);
        get_email = findViewById(R.id.register_email);
        get_password = findViewById(R.id.register_password);
        get_avatar = findViewById(R.id.register_avatar);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        tmp_username = prefs.getString("tmp_username",null);
        tmp_email = prefs.getString("tmp_email", null);
        tmp_password = prefs.getString("tmp_password", null);
        tmp_avatar = prefs.getString("tmp_avatar","1");

        progressDialog = new ProgressDialog(this);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            get_username.setText(tmp_username);
            get_email.setText(tmp_email);
            get_password.setText(tmp_password);
            avatar = b.getString("tmp_avatar");
        } else {
            avatar = "1";
        }
        get_avatar.setImageResource(getResources().getIdentifier("ava" + avatar, "drawable", getPackageName()));
    }

    public void selectAvatar(View view) {
        username = get_username.getText().toString();
        email = get_email.getText().toString();
        password = get_password.getText().toString();
        editor.putString("tmp_username",username);
        editor.putString("tmp_email",email);
        editor.putString("tmp_password",password);
        editor.apply();
        Intent i = new Intent(this, AvatarActivity.class);
        i.putExtra("activity", "register");
        startActivity(i);
        finish();
    }

    public void processFinish(String output) {

        progressDialog.dismiss();
        if (output.indexOf("Error") == -1) {
            //Registro correcto, pasamos a la main activity
            editor.putString("email", email);
            editor.putString("ticket", output);
            editor.putString("username", username);
            editor.putString("avatar", avatar);
            editor.apply();
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            editor.remove("email");
            editor.remove("ticket");
            editor.apply();
        }
    }

    public void registerUser(View v) {

        username = get_username.getText().toString();
        email = get_email.getText().toString();
        password = get_password.getText().toString();
        if (email.matches("") || password.matches("")) {
            Toast.makeText(this, getString(R.string.register_empty), Toast.LENGTH_SHORT).show();
        } else {
            try {
                progressDialog.setMessage(getString(R.string.register_auto));
                progressDialog.show();
                new RegisterTask(this).execute(new String[]{"GET", BASE_URL + "register.php?username="+ username +  "&email=" + email + "&password=" + (new SHA1()).SHA1(password)+ "&avatar=" + avatar});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
