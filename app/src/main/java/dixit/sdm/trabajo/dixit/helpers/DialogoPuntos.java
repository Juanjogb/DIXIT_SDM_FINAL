package dixit.sdm.trabajo.dixit.helpers;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import dixit.sdm.trabajo.dixit.R;

public class DialogoPuntos {

    final Context contexto;
    final RadioButton rb1, rb2, rb3, rb4;

    public DialogoPuntos(Context context, final String score1, String score2, String score3, String score4, boolean puntuado){
        final Dialog dialog = new Dialog(context);
        contexto = context;
        dialog.setContentView(R.layout.dialog_puntos);

        Button ok = dialog.findViewById(R.id.dialog_score_ok);
        rb1 = dialog.findViewById(R.id.radioButton1);
        rb2 = dialog.findViewById(R.id.radioButton2);
        rb3 = dialog.findViewById(R.id.radioButton3);
        rb4 = dialog.findViewById(R.id.radioButton4);
        if(score1 != null) rb1.setEnabled(false);
        if(score2 != null) rb2.setEnabled(false);
        if(score3 != null) rb3.setEnabled(false);
        if(score4 != null) rb4.setEnabled(false);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardar en sharedPreference la puntuacion elegida para no repetir puntuacion en otra carta
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(contexto);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("puntuado", false);
                editor.commit();
                if (rb1.isChecked() == true) {
                    editor.putString("score1","ok");
                    editor.putBoolean("puntuado",true);
                    editor.commit();
                }
                else if (rb2.isChecked() == true) {
                    editor.putString("score2","ok");
                    editor.putBoolean("puntuado",true);
                    editor.commit();
                }
                    else if (rb3.isChecked() == true) {
                    editor.putString("score3","ok");
                    editor.putBoolean("puntuado",true);
                    editor.commit();
                }
                        else if(rb4.isChecked() == true) {
                    editor.putString("score4","ok");
                    editor.putBoolean("puntuado",true);
                    editor.commit();
                }
                            else Toast.makeText(contexto,"Revisa las puntuaciones",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
