package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.CheckTicketTask;
import dixit.sdm.trabajo.dixit.helpers.MyFirebaseInstanceIDService;
import dixit.sdm.trabajo.dixit.helpers.SendTokenTask;
import dixit.sdm.trabajo.dixit.helpers.Session;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mPlayer;
    private ImageButton btnMusica;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Boolean onOff = false;
    private Session s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = new Session(this);
        btnMusica = findViewById(R.id.music);
        FirebaseApp.initializeApp(this);
        //FirebaseInstanceId.getInstance().getToken();
        MyFirebaseInstanceIDService fb = new MyFirebaseInstanceIDService();
        new SendTokenTask(this).execute(new String[]{"GET", BASE_URL + "sendToken.php?email=" + s.getEmail() + "&ticket=" + s.getTicket() + "&token=" + fb.token()});
        Typeface face = Typeface.createFromAsset(getAssets(), "greco.ttf");
        ((Button) (findViewById(R.id.bPlay))).setTypeface(face);
        ((Button) (findViewById(R.id.bScores))).setTypeface(face);
        ((Button) (findViewById(R.id.bRules))).setTypeface(face);
        ((Button) (findViewById(R.id.bCards))).setTypeface(face);
        ((Button) (findViewById(R.id.bExit))).setTypeface(face);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putBoolean("musicaOn", false);
        editor.apply();

        btnMusica.setImageResource(R.drawable.audio_off);
        btnMusica.setOnClickListener(this);

    }


    public void doAction(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
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
                i.setClass(this, ScoresActivity.class);
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
        } else {
            onOff = false;
            btnMusica.setImageResource(R.drawable.audio_off);
            if (mPlayer != null) {
                mPlayer.stop();
            }
        }
    }

    public void goMain(View v) {
        s.destroy();
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}
