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
    private Button play;
    private Button search;
    private ImageView reverso;
    private Button reversoBtn;
    private String reversoElegido;

    private int[] cartasJuego = new int[]{R.drawable.card_1, R.drawable.card_2,
            R.drawable.card_3, R.drawable.card_4, R.drawable.card_5, R.drawable.card_6,
            R.drawable.card_7, R.drawable.card_8, R.drawable.card_9, R.drawable.card_10,
            R.drawable.card_11, R.drawable.card_12, R.drawable.card_13, R.drawable.card_14,
            R.drawable.card_15, R.drawable.card_16, R.drawable.card_17,
            R.drawable.card_18, R.drawable.card_19, R.drawable.card_20, R.drawable.card_21,
            R.drawable.card_22, R.drawable.card_23, R.drawable.card_24, R.drawable.card_25,
            R.drawable.card_26, R.drawable.card_27, R.drawable.card_28};

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

        progressDialog = new ProgressDialog(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            searchGame.setText(b.getString("password"));
            reversoElegido = b.getString("reverso");
            reverso.setImageResource(getResources().getIdentifier("reverso_" + reversoElegido, "drawable", getPackageName()));
        }

        idPlayer = prefs.getString("username", null);
    }


    public void processFinish(String output) {
        progressDialog.dismiss();
        if (output.indexOf("Error") == -1) {
            editor.putString("score1", null);
            editor.putString("score2", null);
            editor.putString("score3", null);
            editor.putString("score4", null);
            editor.apply();
            Toast.makeText(this, getString(R.string.play_gameCreated), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Game2Activity.class);
            i.putExtra("id", output);
            i.putExtra("reverso",reversoElegido);
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
