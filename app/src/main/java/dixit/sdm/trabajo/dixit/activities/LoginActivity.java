package dixit.sdm.trabajo.dixit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.CheckTicketTask;
import dixit.sdm.trabajo.dixit.helpers.LoginTask;
import dixit.sdm.trabajo.dixit.helpers.SHA1;
import dixit.sdm.trabajo.dixit.helpers.Session;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    //Campos de sharedpreferences
    private String tmp_email;
    private String tmp_ticket;

    //Campos del login activity
    private String email;
    private String password;
    private String token;
    //Referencias a los dos campos del login activity
    private EditText get_email;
    private EditText get_password;

    private Button login;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        get_email = findViewById(R.id.login_email);
        get_password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.login_goRegister);
        progressDialog = new ProgressDialog(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        Typeface face = Typeface.createFromAsset(getAssets(), "greco.ttf");
        login.setTypeface(face);
        register.setTypeface(face);

        //Si tenemos algun par usuario/ticket en shared preferences validamos en segundo plano
        //y redirigimos al main activity
        Session s = new Session(this);
        if (!s.isEmpty()) {
            //Dialog de iniciando sesion
            progressDialog.setMessage(getString(R.string.login_auto));
            progressDialog.show();
            try {
                new CheckTicketTask(this).execute(new String[]{"GET", BASE_URL + "checkTicket.php?email=" + s.getEmail() + "&ticket=" + s.getTicket()});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        (findViewById(R.id.login_goRegister)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    public void processFinish(String output) {
        progressDialog.dismiss();
        Session s = new Session(this);
        if (output.indexOf("Error") == -1) {
            //Guardar el ticket que nos han devuelto (si venimos de login) osea un if no existe en shared nada guardamos
            if (s.isEmpty()) {
                String[] res = output.split("#");
                editor.putString("email", email);
                editor.putString("ticket", res[0]);
                editor.putString("username", res[1]);
                editor.putString("avatar", res[2]);
                editor.apply();
            }

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            s.destroy();
        }
    }

    public void goLogin(View view) {
        email = get_email.getText().toString();
        password = get_password.getText().toString();
        if (email.matches("") || password.matches("")) {
            Toast.makeText(this, getString(R.string.login_empty), Toast.LENGTH_SHORT).show();
        } else {
            try {
                progressDialog.setMessage(getString(R.string.login_auto));
                progressDialog.show();
                new LoginTask(this).execute(new String[]{"GET", BASE_URL + "login.php?email=" + email + "&password=" + (new SHA1()).SHA1(password)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
