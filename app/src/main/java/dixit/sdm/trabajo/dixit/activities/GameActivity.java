package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dixit.sdm.trabajo.dixit.R;

public class GameActivity extends AppCompatActivity {

    private String  id;//Id partida

    public int tokenNarrador;
    public int turnoJugador;
    public int numeroJugadores;
    public int idJugador;
    Boolean miTurno = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle b = getIntent().getExtras();
        id = b.getString("id");
        ((TextView)findViewById(R.id.game_idPartida)).setText(id);

        //Arranca maquina de estados
        maquinaEstados();
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
        if (miTurno = true ) {

        } else {
            /* Acciones cuando el jugador espera a que los otros jugadores elijan la carta que más se ajusta a la descripción*/
            /* 1. Puede ver sus cartas o visualizar el tablero */

            while(miTurno == false) {
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

    public void shareGame(View v) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.game_shareSubject));
        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.game_shareText) + id);
        startActivity(Intent.createChooser(share, getString(R.string.game_shareGame)));
    }
}
