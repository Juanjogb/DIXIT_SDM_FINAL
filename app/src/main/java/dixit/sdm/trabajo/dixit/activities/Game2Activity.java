package dixit.sdm.trabajo.dixit.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.helpers.DialogoPersonalizado;

public class Game2Activity extends AppCompatActivity{

    private String  id;//Id partida
    private String  reverso;//Reverso elegido
    private ImageView cardJ1, cardJ2, cardJ3, cardJ4;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

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
}