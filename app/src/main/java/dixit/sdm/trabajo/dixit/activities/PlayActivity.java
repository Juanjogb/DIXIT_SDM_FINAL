package dixit.sdm.trabajo.dixit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.CheckTicketTask;
import dixit.sdm.trabajo.dixit.helpers.CreateGameTask;
import dixit.sdm.trabajo.dixit.helpers.SHA1;
import dixit.sdm.trabajo.dixit.helpers.SearchGameTask;

import static dixit.sdm.trabajo.dixit.R.id.play_btnPlay;
import static dixit.sdm.trabajo.dixit.R.id.play_newGame;
import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class PlayActivity extends AppCompatActivity {

    private EditText newGame;
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
            R.drawable.dieciocho, R.drawable.diecinueve, R.drawable.veinte,R.drawable.veintiuno,
            R.drawable.veintidos, R.drawable.veintitres, R.drawable.veinticuatro,R.drawable.veinticinco,
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

    private String idPlayer;

    private ProgressDialog progressDialog;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        newGame = findViewById(R.id.play_newGame);
        searchGame = findViewById(R.id.play_searchGame);
        play = findViewById(R.id.play_btnPlay);
        search = findViewById(R.id.play_btnSearch);
        reverso = findViewById(R.id.play_reversoId);
        reversoBtn = findViewById(R.id.play_reverso);

        Typeface face = Typeface.createFromAsset(getAssets(),"greco.ttf");
        play.setTypeface(face);
        search.setTypeface(face);
        newGame.setTypeface(face);
        searchGame.setTypeface(face);
        reversoBtn.setTypeface(face);

        progressDialog = new ProgressDialog(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            newGame.setText(b.getString("tmp_newGame"));
            searchGame.setText(b.getString("password"));
            String reversoElegido = b.getString("reverso");
            reverso.setImageResource(getResources().getIdentifier("reverso_" + reversoElegido, "drawable", getPackageName()));
        }

        idPlayer = prefs.getString("username", null);
    }


    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.play_btnPlay:
                //crear nueva partida con el identificador que le hemos pasado y lanzar game activity
                crearPartida(newGame.getText().toString());
                i.setClass(this, GameActivity.class);
                break;
            case R.id.play_btnSearch:
                //buscamos la partida con el identificador que hemos puesto y si la encontramos lanzamos game activity
                buscarPartida(searchGame.getText().toString());
                i.setClass(this, GameActivity.class);
                break;
        }
        startActivity(i);
    }

    private void crearPartida(String idNew) {

        //comprobamos que no existe otra partida con el mismo id
        //comprobarPartidaTask  ---> se realiza la comprobacion en segundo plano

        listaJugadores[0] = idPlayer;

        //rellenamos las cartas del jugador 1
        cartasJ1[0] = (int) (Math.random() * cartasJuego.length);
        for (int i = 1; i < cartasJ1.length; i++){
            cartasJ1[i] = (int) (Math.random() * cartasJuego.length);
            for(int j = 0; j < 1; j++){
                if(cartasJ1[i] == cartasJ1[j])
                    i--;
            }
        }
        tokenNarrador = 0;
        descripcion = "";

        //Toast.makeText(this,cartasJuego[2],Toast.LENGTH_SHORT).show(); //res/drawable/tres.jpg
        progressDialog.setMessage(getString(R.string.create_game));
        progressDialog.show();
        try {
            new CreateGameTask(this).execute(new String[]{"GET", BASE_URL + "createGame.php?idPartida=" + idNew + /*"&cartasJuego=" + cartasJuego +*/ "&cartasJ1" + cartasJ1 +
                                                                    "&cartasJ2" + cartasJ2 + "&cartasJ3" + cartasJ3 + "&cartasJ4" + cartasJ4 + "&cartasMesa" + cartasMesa +
                                                                    "&listaJugadores" + listaJugadores + "&puntosJugadores" + puntosJugadores + "&tokenNarrador" + tokenNarrador + "&descripcion" + descripcion});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processFinish(String output) {
        progressDialog.dismiss();
        if (output.indexOf("Error") == -1) { //Se a creado la partida correctamente o se a encontrado

            if(output.indexOf("Creado") != -1){ //Creando partida

            }else { //Encontrada partida
                String[] res = output.split("#");
                editor.putString("idPartida",res[0]);
                editor.putString("cartasJ1",res[1]);


            }
            Intent i = new Intent(this, GameActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
        else {//La partida ya existe o no se ha encontrado // el mensaje depende del boton que apretemos
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
        }
    }


    private void buscarPartida(String idSearch) {

        progressDialog.setMessage(getString(R.string.search_game));
        progressDialog.show();
        try {
            new SearchGameTask(this).execute(new String[]{"GET", BASE_URL + "searchGame.php?idPartida=" + idSearch + "&idPlayer" + idPlayer});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void doReverso(View v) {
        String tmp_newGame = newGame.getText().toString();
        String tmp_serachGame = searchGame.getText().toString();
        editor.putString("tmp_newGame",tmp_newGame);
        editor.putString("tmp_serachGame",tmp_serachGame);
        editor.apply();
        startActivity(new Intent(this, BarajaActivity.class));
        finish();
    }
}
