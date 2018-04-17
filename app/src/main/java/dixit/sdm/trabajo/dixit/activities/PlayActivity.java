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
import android.widget.ImageView;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.CreateGameTask;
import dixit.sdm.trabajo.dixit.helpers.JoinGameTask;
import dixit.sdm.trabajo.dixit.helpers.Session;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class PlayActivity extends AppCompatActivity {

    private EditText searchGame;
    private Button play;
    private Button search;
    private ImageView reverso;
    private Button reversoBtn;

    private int[] cartasJuego = new int[]{R.drawable.uno, R.drawable.dos,
            R.drawable.tres, R.drawable.cuatro, R.drawable.cinco, R.drawable.seis,
            R.drawable.siete, R.drawable.ocho, R.drawable.nueve, R.drawable.diez,
            R.drawable.once, R.drawable.doce, R.drawable.trece, R.drawable.catorce,
            R.drawable.quince, R.drawable.dieciseis, R.drawable.diecisiete,
            R.drawable.dieciocho, R.drawable.diecinueve, R.drawable.veinte, R.drawable.veintiuno,
            R.drawable.veintidos, R.drawable.veintitres, R.drawable.veinticuatro, R.drawable.veinticinco,
            R.drawable.veintiseis, R.drawable.veintisiete, R.drawable.veintiocho};

    //partida por defecto 4 jugadores
    private int[] cartasJ1 = new int[5];
    private int[] cartasJ2 = new int[5];
    private int[] cartasJ3 = new int[5];
    private int[] cartasJ4 = new int[5];
    private String[] listaJugadores = new String[3];
    private int[] puntosJugadores = new int[3];
    private int[] cartasMesa = new int[3];
    private int tokenNarrador;
    private String descripcion;
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
        play = findViewById(R.id.play_btnPlay);
        search = findViewById(R.id.play_btnSearch);
        reverso = findViewById(R.id.play_reversoId);
        reversoBtn = findViewById(R.id.play_reverso);

        Typeface face = Typeface.createFromAsset(getAssets(), "greco.ttf");
        play.setTypeface(face);
        search.setTypeface(face);
        searchGame.setTypeface(face);
        reversoBtn.setTypeface(face);

        progressDialog = new ProgressDialog(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            searchGame.setText(b.getString("password"));
            String reversoElegido = b.getString("reverso");
            reverso.setImageResource(getResources().getIdentifier("reverso_" + reversoElegido, "drawable", getPackageName()));
        }

        idPlayer = prefs.getString("username", null);
    }


    public void processFinish(String output) {
        progressDialog.dismiss();
        if (output.indexOf("Error") == -1) {
            Toast.makeText(this, getString(R.string.play_gameCreated), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("id", output);
            startActivity(i);
        } else Toast.makeText(this, output, Toast.LENGTH_SHORT).show();


    }
    public void processFinishJoin(String a) {
        progressDialog.dismiss();
        Toast.makeText(this, getString(R.string.play_gameJoined), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("id", intr_id);
        startActivity(i);
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
        new CreateGameTask(this).execute(new String[]{"GET", BASE_URL + "newGame.php?email=" + s.getEmail() + "&ticket=" + s.getTicket()});
    }

    public void joinGame(View view) {
        intr_id = searchGame.getText().toString();
        if (intr_id.trim().length() == 0)
            Toast.makeText(this, getString(R.string.play_emptyID), Toast.LENGTH_SHORT).show();
        else {
            progressDialog.setMessage(getString(R.string.play_joiningGame));
            new JoinGameTask(this).execute(new String[]{"GET", BASE_URL + "joinGame.php?email=" + s.getEmail() + "&ticket=" + s.getTicket() + "&id=" + intr_id});
        }
    }


}
