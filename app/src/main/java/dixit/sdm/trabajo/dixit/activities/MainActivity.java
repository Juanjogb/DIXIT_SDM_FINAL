package dixit.sdm.trabajo.dixit.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dixit.sdm.trabajo.dixit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.bPlayer);
        Button btn2 = findViewById(R.id.bOnline);
        Button btn3 = findViewById(R.id.bScores);
        Button btn4 = findViewById(R.id.bRules);
        Button btn5 = findViewById(R.id.bCards);

        Typeface face = Typeface.createFromAsset(getAssets(),"greco.ttf");
        btn1.setTypeface(face);
        btn2.setTypeface(face);
        btn3.setTypeface(face);
        btn4.setTypeface(face);
        btn5.setTypeface(face);
    }


    public void doAction(View v) {
        Intent i = new Intent();
        switch(v.getId()){
            case R.id.bPlayer:
                i.setClass(this, PlayerActivity.class);
                break;
            case R.id.bOnline:
                i.setClass(this, OnlineActivity.class);
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
        }
        startActivity(i);
    }
}
