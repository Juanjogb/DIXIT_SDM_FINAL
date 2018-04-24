package dixit.sdm.trabajo.dixit.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.DialogoPersonalizado;
import dixit.sdm.trabajo.dixit.helpers.DialogoPuntos;
import dixit.sdm.trabajo.dixit.helpers.GetPlayersInRoomTask;
import dixit.sdm.trabajo.dixit.helpers.Session;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class GameActivity extends AppCompatActivity {

    private String id;//Id partida
    private String reverso;//Reverso elegido
    private String infoGame = "";//Info player
    private ImageView cardJ1, cardJ2, cardJ3;
    private ImageButton game_card1, game_card2, game_card3, game_card4, game_card5, game_card6;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String score1, score2, score3, score4, cartasPlayer, idPartida;
    private Boolean notOwner = false;
    private boolean puntuado;
    private Context context;

    public int tokenNarrador;
    public int turnoJugador;
    public int numeroJugadores;
    public int idJugador;
    Boolean miTurno = false;
    private BroadcastReceiver receiver;
    private int jugadoresDentro = 1;

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter("MSG")
        );
    }


    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String type = intent.getStringExtra("type");
                String data = intent.getStringExtra("data");
                switch(type){

                    case "join":
                        TextView tAux = ((TextView) findViewById(getResources().getIdentifier("textViewJ" + jugadoresDentro++, "id", getPackageName())));
                        tAux.setText(data);
                        break;

                        case "start":
                            Toast.makeText(context, "¡Empezamos! El narrador es: " + data, Toast.LENGTH_SHORT).show();
                            break;
                }
            }
        };
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        score1 = prefs.getString("score1", null);
        score2 = prefs.getString("score2", null);
        score3 = prefs.getString("score3", null);
        score4 = prefs.getString("score4", null);
        puntuado = prefs.getBoolean("puntuado", false);
        cartasPlayer = prefs.getString("cartasPlayer",null);
        idPartida = prefs.getString("idPartida",null);
        reverso = prefs.getString("reverso","original");
        infoGame = prefs.getString("info","");

        Bundle b = getIntent().getExtras();
        //id = b.getString("id");
        //infoGame = b.getString("info","vacio");
        if (b!=null) notOwner = b.getBoolean("giveMe", false);
        if (notOwner) {
            Session s = new Session(this);
            new GetPlayersInRoomTask(this).execute(new String[]{"GET", BASE_URL + "getPlayersInRoom.php?email=" + s.getEmail() + "&ticket=" + s.getTicket() + "&id=" + idPartida});

        }
        String[] mis_cartas = cartasPlayer.split(",");
        if(b!=null && b.getBoolean("regreso")) infoGame = "Has vuelto a tu partida.";
        //else mis_cartas = b.getString("cartas").split(",");
        //reverso = b.getString("reverso");
        ((ImageView) findViewById(R.id.cardJ1)).setImageResource(getResources().getIdentifier("reverso_" + reverso, "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.cardJ2)).setImageResource(getResources().getIdentifier("reverso_" + reverso, "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.cardJ3)).setImageResource(getResources().getIdentifier("reverso_" + reverso, "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.game_card1)).setImageResource(getResources().getIdentifier("card_" + mis_cartas[0], "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.game_card2)).setImageResource(getResources().getIdentifier("card_" + mis_cartas[1], "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.game_card3)).setImageResource(getResources().getIdentifier("card_" + mis_cartas[2], "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.game_card4)).setImageResource(getResources().getIdentifier("card_" + mis_cartas[3], "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.game_card5)).setImageResource(getResources().getIdentifier("card_" + mis_cartas[4], "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.game_card6)).setImageResource(getResources().getIdentifier("card_" + mis_cartas[5], "drawable", getPackageName()));
        ((TextView) findViewById(R.id.game_idPartida)).setText(idPartida);
        ((TextView) findViewById(R.id.game_infoTextView)).setText(infoGame);

        //Arranca maquina de estados
        maquinaEstados();
    }

    public void shareGame(View v) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.game_shareSubject));
        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.game_shareText) + id);
        startActivity(Intent.createChooser(share, getString(R.string.game_shareGame)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void selectCard(View v) {
        switch (v.getId()) {
            case R.id.game_card1:
                new DialogoPersonalizado(this);
                break;
            case R.id.game_card2:
                new DialogoPersonalizado(this);
                break;
            case R.id.game_card3:
                new DialogoPersonalizado(this);
                break;
            case R.id.game_card4:
                new DialogoPersonalizado(this);
                break;
            case R.id.game_card5:
                new DialogoPersonalizado(this);
                break;
            case R.id.game_card6:
                new DialogoPersonalizado(this);
                break;
        }
    }

    public void actualizarScores() {
        score1 = prefs.getString("score1", null);
        score2 = prefs.getString("score2", null);
        score3 = prefs.getString("score3", null);
        score4 = prefs.getString("score4", null);
        puntuado = prefs.getBoolean("puntuado", false);
    }

    public void cardsTableScore(View v) {
        switch (v.getId()) {
            case R.id.cardJ1:
                actualizarScores();
                new DialogoPuntos(this, score1, score2, score3, score4, puntuado);
                if (puntuado) ((ImageView) findViewById(R.id.cardJ1)).setEnabled(false);
                break;
            case R.id.cardJ2:
                actualizarScores();
                new DialogoPuntos(this, score1, score2, score3, score4, puntuado);
                if (puntuado) ((ImageView) findViewById(R.id.cardJ2)).setEnabled(false);
                break;
            case R.id.cardJ3:
                actualizarScores();
                new DialogoPuntos(this, score1, score2, score3, score4, puntuado);
                if (puntuado) ((ImageView) findViewById(R.id.cardJ3)).setEnabled(false);
                break;
        }
    }

    private void maquinaEstados() {

        /*Recibir variable tokenNarrador y turnoJugador del server */

        if (tokenNarrador == 1 && turnoJugador == idJugador) {
            jugadorNarrador();
        } else {
            jugadorEspera();
        }

    }

    private void jugadorEspera() {

        /* Si ya ha sido su turno */
        if (miTurno = true) {

        } else {
            /* Acciones cuando el jugador espera a que los otros jugadores elijan la carta que más se ajusta a la descripción*/
            /* 1. Puede ver sus cartas o visualizar el tablero */

            while (miTurno == false) {
                /* Escucha variable turnoJugador y comprueba si es igual a idJugador*/
                /* Si es su turno miTurno = true */
            }

            //Pasa a jugar
            jugadorJuega();

        }


    }

    private void jugadorJuega() {

        /* Acciones cuando le toca al jugador elegir carta*/
        /* 1. Escucha la descripcion */
        /* 2. Seleccionar una de sus cartas*/

        /*Control del turnoJugador */
        if (turnoJugador == numeroJugadores) {
            turnoJugador = 1;
        } else {
            turnoJugador++;
        }

        //Pasa a esperar
        jugadorEspera();

    }

    private void jugadorNarrador() {

        /* Acciones que realiza el Narrador */
        /* 1. Selecciona una de sus cartas */
        /* 2. Añade una descripción y la envía */

        /*Pasa el turno al siguiente jugador */
        /*Control del turnoJugador */
        if (turnoJugador == numeroJugadores) {
            turnoJugador = 1;
        } else {
            turnoJugador++;
        }

        tokenNarrador = 0;

        //Pasa a esperar
        narradorEspera();

    }


    private void narradorEspera() {

        while (turnoJugador <= numeroJugadores) {
            /*Espera y consulta turnoJugador*/
        }

        calculaPuntos();
    }

    /* Método que ejecturá el narrador cuando todos haya elegidos sus cartas y elegido sus losetas */
    private void calculaPuntos() {
        /* Evalua puntos*/

    }

    public void processFinishGet(String output) {
        Log.d("TTT", output);
        if (output.indexOf("Error") == -1) {
            String[] playersInRoom = output.split(",");
            //jugadoresDentro += playersInRoom.length;
            for (int i = 0; i < playersInRoom.length; i++) {
                TextView tAux = ((TextView) findViewById(getResources().getIdentifier("textViewJ" + (i + 1), "id", getPackageName())));
                tAux.setText(playersInRoom[i]);
            }

        } else Toast.makeText(context, output, Toast.LENGTH_SHORT).show();
    }
}