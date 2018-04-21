package dixit.sdm.trabajo.dixit.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dixit.sdm.trabajo.dixit.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
/*    public int[] slide_images = {

    };
*/
    public String[] slide_headings = {
            "Preparacion","Cuentacuentos","Otros jugadores","Puntuacion","Final del turno","Final del juego"
    };

    public String[] slide_descs = {
            "\nCada jugador empieza en la casilla 0 y con 6 cartas en la mano.\nEl resto se encuentran en el mazo.",
            "\nCada turno se elegira a un cuentacuentos que debera elegir una de las cartas de su mano y redactar una frase que tenga que ver con esta.",
            "El resto de jugadores elegira una carta de su mano que mas se adapte a la descripcion del cuentacuentos.\n Despues votaran intentando adivinar que carta de entre todas creen que es la del cuentacuentos.",
            "Si todos los jugadores(o ninguno) han acertado la carta del cuentacuentos, el cuentacuentos recibe 2 puntos.\n" +
                    "\t\tSino tanto el cuentacuentos como los jugadores que han acertado la carta del cuentacuentos reciben 3 puntos.\n" +
                    "\t\tAdemas todos los jugadores(excepto el cuentacuentos) reciben 1 punto por cada voto que reciba su carta.",
            "\nSe descartan las cartas jugadas y la ficha de cada jugador avanzara tantas posiciones como puntos hayan obtenido este turno.\n Despues cada jugador robara hasta tener en mano 6 cartas de nuevo y el rol de cuentacuentos pasara al siguiente jugador.",
            "\nEl juego termina cuando se roba la ultima carta.\n El jugador que mas lejos haya llegado en la escala de puntuacion es el ganador."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        //ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        //slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
