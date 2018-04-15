package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import dixit.sdm.trabajo.dixit.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mPlayer;
    private ImageButton btnMusica;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int posicion = 0;
    private Boolean onOff= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.bPlay);
        Button btn3 = findViewById(R.id.bScores);
        Button btn4 = findViewById(R.id.bRules);
        Button btn5 = findViewById(R.id.bCards);
        btnMusica = findViewById(R.id.music);

        Typeface face = Typeface.createFromAsset(getAssets(),"greco.ttf");
        btn1.setTypeface(face);
        btn3.setTypeface(face);
        btn4.setTypeface(face);
        btn5.setTypeface(face);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putBoolean("musicaOn",false);
        editor.apply();

        btnMusica.setImageResource(R.drawable.audio_off);
        btnMusica.setOnClickListener(this);


    }


    public void doAction(View v) {
        Intent i = new Intent();
        switch(v.getId()){
            case R.id.bPlay:
                i.setClass(this, PlayActivity.class);
                break;
            case R.id.bRules:
                i.setClass(this, RulesActivity.class);
                break;
            case R.id.bCards:
                i.setClass(this, CardsActivity.class);
                break;
            case R.id.settings:
                i.setClass(this, SettingsActivity.class);
                break;
            case R.id.bScores:
                i.setClass(this, GameActivity.class);
                break;
        }
        startActivity(i);
    }

    @Override
    public void onClick(View v) {

        if (!onOff) {
            onOff = true;
            btnMusica.setImageResource(R.drawable.audio1);
            if (mPlayer != null) {
                mPlayer.release();
            }
            mPlayer = MediaPlayer.create(this, R.raw.dixit_musica);
            mPlayer.seekTo(0);
            mPlayer.start();
            mPlayer.setLooping(true);
        }
        else {
            onOff = false;
            btnMusica.setImageResource(R.drawable.audio_off);
            if (mPlayer != null) {
                mPlayer.stop();
            }
        }
    }
}
