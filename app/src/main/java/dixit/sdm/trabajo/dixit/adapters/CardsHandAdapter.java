package dixit.sdm.trabajo.dixit.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import dixit.sdm.trabajo.dixit.R;

public class CardsHandAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public CardsHandAdapter(Context context) {
        this.context = context;
    }

    Random r = new Random();
    int carta1 = r.nextInt(28 - 1) + 1;
    int carta2 = r.nextInt(28 - 1) + 1;
    int carta3 = r.nextInt(28 - 1) + 1;
    int carta4 = r.nextInt(28 - 1) + 1;
    int carta5 = r.nextInt(28 - 1) + 1;
    int carta6 = r.nextInt(28 - 1) + 1;



    //Arrays
    public int[] slide_image = {
            R.drawable.uno,
            R.drawable.dos,
            R.drawable.tres,
            R.drawable.cuatro,
            R.drawable.cinco,
            R.drawable.seis,
            R.drawable.siete,
            R.drawable.ocho,
            R.drawable.nueve,
            R.drawable.diez,
            R.drawable.once,
            R.drawable.doce,
            R.drawable.trece,
            R.drawable.catorce,
            R.drawable.quince,
            R.drawable.dieciseis,
            R.drawable.diecisiete,
            R.drawable.dieciocho,
            R.drawable.diecinueve,
            R.drawable.veinte,
            R.drawable.veintiuno,
            R.drawable.veintidos,
            R.drawable.veintitres,
            R.drawable.veinticuatro,
            R.drawable.veinticinco,
            R.drawable.veintiseis,
            R.drawable.veintisiete,
            R.drawable.veintiocho,

    };


    @Override
    public int getCount() {
        return slide_image.length;
    }



    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);

        slideImageView.setImageResource(slide_image[position]);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}