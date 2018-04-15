package dixit.sdm.trabajo.dixit.activities;

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
            "Cada jugador empieza en la casilla 0 y con 6 cartas en la mano. El resto se encuentran en el mazo",
            "Cada turno se elegirá a un cuentacuentos que deberá elegir una de las cartas de su mano y redactar una frase que tenga que ver con esta",
            "El resto de jugadores elegira una carta de su mano que más se adapte a la descripcion del cuentacuentos. Despues votaran intentando adivinar que carta de entre todas creen que es la del cuentacuentos",
            "Si todos los jugadores(o ninguno) han acertado la carta del cuentacuentos, el cuentacuentos recibe 2 puntos.\n" +
                    "\t\tSino tanto el cuentacuentos como los jugadores que han acertado la carta del cuentacuentos reciben 3 puntos.\n" +
                    "\t\tAdemás todos los jugadores(excepto el cuentacuentos) reciben 1 punto por cada voto que reciba su carta",
            "Se descartan las cartas jugadas y la ficha de cada jugador avanzara tantas posiciones como puntos hayan obtenido este turno. Despues cada jugador robara hasta tener en mano 6 cartas de nuevo y el rol de cuentacuentos pasara al siguiente jugador",
            "El juego termina cuando se roba la última carta.El jugador que más lejos haya llegado en la escala de puntuación es el gandaor"
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
