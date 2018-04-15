package dixit.sdm.trabajo.dixit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.LoginTask;
import dixit.sdm.trabajo.dixit.helpers.SHA1;
import dixit.sdm.trabajo.dixit.helpers.CheckTicketTask;

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

    //Referencias a los dos campos del login activity
    private EditText get_email;
    private EditText get_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        get_email = findViewById(R.id.login_email);
        get_password = findViewById(R.id.login_password);

        progressDialog = new ProgressDialog(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        //Si tenemos algun par usuario/ticket en shared preferences validamos en segundo plano
        //y redirigimos al main activity
        tmp_email = prefs.getString("email", null);
        tmp_ticket = prefs.getString("ticket", null);
        if (tmp_email != null && tmp_ticket != null) {
            //Dialog de iniciando sesion
            progressDialog.setMessage(getString(R.string.login_auto));
            progressDialog.show();
            try {
                new CheckTicketTask(this).execute(new String[]{"GET", BASE_URL + "checkTicket.php?email=" + tmp_email + "&ticket=" + tmp_ticket});
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
        if (output.indexOf("Error") == -1) {
            //Guardar el ticket que nos han devuelto (si venimos de login) osea un if no existe en shared nada guardamos
            if (tmp_email == null && tmp_ticket == null) {
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
        }
        else {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            editor.remove("email");
            editor.remove("ticket");
            editor.apply();
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
