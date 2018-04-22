package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import dixit.sdm.trabajo.dixit.R;

public class GameActivity extends AppCompatActivity {

    private String  id;//Id partida

    private Button ok;
    private int[] mImageIds = new int[]{R.drawable.card_1, R.drawable.card_2,
            R.drawable.card_3, R.drawable.card_4, R.drawable.card_5, R.drawable.card_6,
            R.drawable.card_7, R.drawable.card_8, R.drawable.card_9, R.drawable.card_10,
            R.drawable.card_11, R.drawable.card_12, R.drawable.card_13, R.drawable.card_14,
            R.drawable.card_15, R.drawable.card_16, R.drawable.card_17,
            R.drawable.card_18, R.drawable.card_19, R.drawable.card_20,R.drawable.card_21,
            R.drawable.card_22, R.drawable.card_23, R.drawable.card_24,R.drawable.card_25,
            R.drawable.card_26, R.drawable.card_27, R.drawable.card_28,
            R.drawable.card_29, R.drawable.card_30, R.drawable.card_31,R.drawable.card_32,
            R.drawable.card_33, R.drawable.card_34, R.drawable.card_35,R.drawable.card_36,
            R.drawable.card_37, R.drawable.card_38, R.drawable.card_39,
            R.drawable.card_40, R.drawable.card_41, R.drawable.card_42,R.drawable.card_43,
            R.drawable.card_44, R.drawable.card_45, R.drawable.card_46,R.drawable.card_47,
            R.drawable.card_48, R.drawable.card_49, R.drawable.card_50,R.drawable.card_51};


    public int[] cardsJ1 = new int[6];
    public int[] cardsJ2= new int[6];
    public int[] cardsJ3= new int[6];
    public int[] cardsJ4= new int[6];
    private ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
    private ArrayList<Integer> listaNumerosTotales = new ArrayList<Integer>();

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


        ok = findViewById(R.id.game_btn);
        //Generar cartas J1
        cargarManos(cardsJ1);
        ((ImageButton)(findViewById(R.id.game_card1))).setImageResource(mImageIds[cardsJ1[0]]);
        ((ImageButton)(findViewById(R.id.game_card2))).setImageResource(mImageIds[cardsJ1[1]]);
        ((ImageButton)(findViewById(R.id.game_card3))).setImageResource(mImageIds[cardsJ1[2]]);
        ((ImageButton)(findViewById(R.id.game_card4))).setImageResource(mImageIds[cardsJ1[3]]);
        ((ImageButton)(findViewById(R.id.game_card5))).setImageResource(mImageIds[cardsJ1[4]]);
        ((ImageButton)(findViewById(R.id.game_card6))).setImageResource(mImageIds[cardsJ1[5]]);


        //Generar cartas J2
        cargarManos(cardsJ2);
        ((ImageButton)(findViewById(R.id.game_card7))).setImageResource(mImageIds[cardsJ2[0]]);
        ((ImageButton)(findViewById(R.id.game_card8))).setImageResource(mImageIds[cardsJ2[1]]);
        ((ImageButton)(findViewById(R.id.game_card9))).setImageResource(mImageIds[cardsJ2[2]]);
        ((ImageButton)(findViewById(R.id.game_card10))).setImageResource(mImageIds[cardsJ2[3]]);
        ((ImageButton)(findViewById(R.id.game_card11))).setImageResource(mImageIds[cardsJ2[4]]);
        ((ImageButton)(findViewById(R.id.game_card12))).setImageResource(mImageIds[cardsJ2[5]]);

        //Generar cartas J3
        cargarManos(cardsJ3);
        ((ImageButton)(findViewById(R.id.game_card13))).setImageResource(mImageIds[cardsJ3[0]]);
        ((ImageButton)(findViewById(R.id.game_card14))).setImageResource(mImageIds[cardsJ3[1]]);
        ((ImageButton)(findViewById(R.id.game_card15))).setImageResource(mImageIds[cardsJ3[2]]);
        ((ImageButton)(findViewById(R.id.game_card16))).setImageResource(mImageIds[cardsJ3[3]]);
        ((ImageButton)(findViewById(R.id.game_card17))).setImageResource(mImageIds[cardsJ3[4]]);
        ((ImageButton)(findViewById(R.id.game_card18))).setImageResource(mImageIds[cardsJ3[5]]);

        //Generar cartas J4
        cargarManos(cardsJ4);
        ((ImageButton)(findViewById(R.id.game_card19))).setImageResource(mImageIds[cardsJ4[0]]);
        ((ImageButton)(findViewById(R.id.game_card20))).setImageResource(mImageIds[cardsJ4[1]]);
        ((ImageButton)(findViewById(R.id.game_card21))).setImageResource(mImageIds[cardsJ4[2]]);
        ((ImageButton)(findViewById(R.id.game_card22))).setImageResource(mImageIds[cardsJ4[3]]);
        ((ImageButton)(findViewById(R.id.game_card23))).setImageResource(mImageIds[cardsJ4[4]]);
        ((ImageButton)(findViewById(R.id.game_card24))).setImageResource(mImageIds[cardsJ4[5]]);


        //Arranca maquina de estados
        maquinaEstados();
    }

    //rellena las cartas de los 4 jugadores
    private void cargarManos(int[] cartas){
        boolean fin = false;
        int card;
        //Generar cartas J1
        while (!fin) {
            card = generarMano();
            if (card == -1) fin = true;
        }
        int i=0;
        while(!listaNumeros.isEmpty()) {
            cartas[i] = listaNumeros.remove(0);
            i++;
        }
    }


//Genera mano de un jugador
    private int generarMano() {
        if(listaNumeros.size() < 6) {
            int num = numeroAleatorio();
            if (listaNumerosTotales.contains(num) ) {
                return generarMano();
            } else {
                if (listaNumeros.isEmpty()) {
                    listaNumeros.add(num);
                    listaNumerosTotales.add(num);
                    return num;
                }
                else {
                    if (listaNumeros.contains(num) ) {
                        return generarMano();
                    }
                    else {
                    listaNumeros.add(num);
                    listaNumerosTotales.add(num);
                    return num;
                    }
                }
            }
        }
        return -1;
    }

    private int numeroAleatorio() {
        return (int)(Math.random()*(mImageIds.length));
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