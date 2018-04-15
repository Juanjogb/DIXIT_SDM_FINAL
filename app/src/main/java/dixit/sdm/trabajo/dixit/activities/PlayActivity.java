package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dixit.sdm.trabajo.dixit.R;

import static dixit.sdm.trabajo.dixit.R.id.play_btnPlay;

public class PlayActivity extends AppCompatActivity {

    private EditText newGame;
    private EditText searchGame;
    private Button play;
    private Button search;

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

        Typeface face = Typeface.createFromAsset(getAssets(),"greco.ttf");
        play.setTypeface(face);
        search.setTypeface(face);
        newGame.setTypeface(face);
        searchGame.setTypeface(face);
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

    private void crearPartida(String s) {

    }


    private void buscarPartida(String s) {

    }


}
