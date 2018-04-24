package dixit.sdm.trabajo.dixit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.CreateGameTask;
import dixit.sdm.trabajo.dixit.helpers.JoinGameTask;
import dixit.sdm.trabajo.dixit.helpers.Session;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class PlayActivity extends AppCompatActivity {

    private EditText searchGame;
    private ImageView reverso;
    private String reversoElegido;


    //partida por defecto 4 jugadores
    private Session s;
    private String idPlayer;
    private String intr_id;
    private ProgressDialog progressDialog;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        s = new Session(this);
        searchGame = findViewById(R.id.play_searchGame);
        reverso = findViewById(R.id.play_reversoId);
        progressDialog = new ProgressDialog(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            searchGame.setText(b.getString("tmp_searchGame"));
            reversoElegido = b.getString("reverso");
            reverso.setImageResource(getResources().getIdentifier("reverso_" + reversoElegido, "drawable", getPackageName()));
        }
        idPlayer = prefs.getString("username", null);
    }


    public void processFinish(String output) {
        progressDialog.dismiss();
        if (output.indexOf("Error") == -1) {
            String[] res = output.split("#");
            editor.putString("score1", null);
            editor.putString("score2", null);
            editor.putString("score3", null);
            editor.putString("score4", null);
            editor.putString("idPartida", res[0]);
            editor.putString("cartasPlayer", res[1]);
            editor.putString("reverso", reversoElegido);
            editor.putString("info", "Esperando al resto de jugadores");
            editor.apply();
            Toast.makeText(this, getString(R.string.play_gameCreated), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, GameActivity.class);
            //i.putExtra("id", res[0]);
            //i.putExtra("cartas", res[1]);
            //i.putExtra("reverso", reversoElegido);
            //i.putExtra("info", "Esperando al resto de jugadores");
            startActivity(i);
        } else Toast.makeText(this, output, Toast.LENGTH_SHORT).show();


    }

    public void processFinishJoin(String a) {
        progressDialog.dismiss();
        String aux = a;
        if (a.indexOf("Error") == -1) {
            Toast.makeText(this, getString(R.string.play_gameJoined), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, GameActivity.class);
            //i.putExtra("id", intr_id);
            i.putExtra("giveMe", true);
            //i.putExtra("cartas", aux);
            startActivity(i);
        } else {
            Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, GameActivity.class);
            //i.putExtra("id", intr_id);
            i.putExtra("giveMe", false);
            i.putExtra("regreso",true);
            startActivity(i);
        }
    }

    public void doReverso(View v) {
        String tmp_searchGame = searchGame.getText().toString();
        editor.putString("tmp_searchGame", tmp_searchGame);
        editor.apply();
        startActivity(new Intent(this, BarajaActivity.class));
        finish();
    }

    public void createGame(View view) {
        progressDialog.setMessage(getString(R.string.play_creatingGame));
        progressDialog.show();
        new CreateGameTask(this).execute(new String[]{"GET", BASE_URL + "newGame.php?email=" + s.getEmail() + "&ticket=" + s.getTicket()});
    }

    public void joinGame(View view) {
        intr_id = searchGame.getText().toString();
        if (intr_id.trim().length() == 0)
            Toast.makeText(this, getString(R.string.play_emptyID), Toast.LENGTH_SHORT).show();
        else {
            progressDialog.setMessage(getString(R.string.play_joiningGame));
            progressDialog.show();
            new JoinGameTask(this).execute(new String[]{"GET", BASE_URL + "joinGame.php?email=" + s.getEmail() + "&ticket=" + s.getTicket() + "&id=" + intr_id});
        }
    }


}
