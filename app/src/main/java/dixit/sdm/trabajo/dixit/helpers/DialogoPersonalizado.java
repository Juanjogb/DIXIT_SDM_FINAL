package dixit.sdm.trabajo.dixit.helpers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import dixit.sdm.trabajo.dixit.R;

public class DialogoPersonalizado  {

    public DialogoPersonalizado( Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_card);

        Button ok = dialog.findViewById(R.id.dialog_ok);
        Button cancel = dialog.findViewById(R.id.dialog_no);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}