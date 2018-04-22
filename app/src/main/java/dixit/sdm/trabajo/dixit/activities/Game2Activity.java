package dixit.sdm.trabajo.dixit.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.DialogoPersonalizado;
import dixit.sdm.trabajo.dixit.helpers.DialogoPuntos;

public class Game2Activity extends AppCompatActivity{

    private String  id;//Id partida
    private String  reverso;//Reverso elegido
    private ImageView cardJ1, cardJ2, cardJ3, cardJ4;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String score1, score2, score3, score4;
    private boolean puntuado;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        score1 = prefs.getString("score1",null);
        score2 = prefs.getString("score2",null);
        score3 = prefs.getString("score3",null);
        score4 = prefs.getString("score4",null);
        puntuado = prefs.getBoolean("puntuado", false);

        Bundle b = getIntent().getExtras();
        id = b.getString("id");
        reverso = b.getString("reverso");
        ((ImageView)findViewById(R.id.cardJ1)).setImageResource(getResources().getIdentifier("reverso_" + reverso, "drawable", getPackageName()));
        ((ImageView)findViewById(R.id.cardJ2)).setImageResource(getResources().getIdentifier("reverso_" + reverso, "drawable", getPackageName()));
        ((ImageView)findViewById(R.id.cardJ3)).setImageResource(getResources().getIdentifier("reverso_" + reverso, "drawable", getPackageName()));
        ((ImageView)findViewById(R.id.cardJ4)).setImageResource(getResources().getIdentifier("reverso_" + reverso, "drawable", getPackageName()));
        ((TextView)findViewById(R.id.game_idPartida)).setText(id);

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
    public void actualizarScores(){
        score1 = prefs.getString("score1",null);
        score2 = prefs.getString("score2",null);
        score3 = prefs.getString("score3",null);
        score4 = prefs.getString("score4",null);
        puntuado = prefs.getBoolean("puntuado",false);
    }

    public void cardsTableScore(View v) {
        switch (v.getId()) {
            case R.id.cardJ1:
                actualizarScores();
                new DialogoPuntos(this,score1,score2,score3,score4,puntuado);
                if (puntuado)((ImageView)findViewById(R.id.cardJ1)).setEnabled(false);
                break;
            case R.id.cardJ2:
                actualizarScores();
                new DialogoPuntos(this, score1, score2, score3, score4, puntuado);
                if (puntuado)((ImageView)findViewById(R.id.cardJ2)).setEnabled(false);
                break;
            case R.id.cardJ3:
                actualizarScores();
                new DialogoPuntos(this, score1, score2, score3, score4, puntuado);
                if (puntuado)((ImageView)findViewById(R.id.cardJ3)).setEnabled(false);
                break;
            case R.id.cardJ4:
                actualizarScores();
                new DialogoPuntos(this, score1, score2, score3, score4, puntuado);
                if (puntuado)((ImageView)findViewById(R.id.cardJ4)).setEnabled(false);
                break;
        }
    }
}