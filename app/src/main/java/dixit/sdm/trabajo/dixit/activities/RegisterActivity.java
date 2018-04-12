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
        
        tmp_username = prefs.getString("username",null);
        tmp_email = prefs.getString("email", null);
        tmp_password = prefs.getString("password", null);
        tmp_avatar = prefs.getString("avatar","1");

        progressDialog = new ProgressDialog(this);
    }

    public void registerUser(View view) {
        
        username = get_username.getText().toString();
        email = get_email.getText().toString();
        password = (get_password.getText().toString());
        avatar = "1";//falta seleccionar el avatar
        if (email.matches("") || password.matches("")) {
            Toast.makeText(this, getString(R.string.register_empty), Toast.LENGTH_SHORT).show();
        } else {
            try {
                progressDialog.setMessage(getString(R.string.login_auto));
                progressDialog.show();
                new RegisterTask(this).execute(new String[]{"GET", BASE_URL + "register.php?username="+ username +  "&email=" + email + "&password=" + (new SHA1()).SHA1(password)+ "&avatar" + avatar});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void selectAvatar(View view) {
        startActivity(new Intent(this, AvatarActivity.class));

    }

    public void processFinish(String a) {
    }
}
