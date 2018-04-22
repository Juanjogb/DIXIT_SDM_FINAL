package dixit.sdm.trabajo.dixit.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import dixit.sdm.trabajo.dixit.R;

public class CardsHandAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public CardsHandAdapter(Context context) {
        this.context = context;
    }

    Random r = new Random();
    int carta1 = r.nextInt(51 - 1) + 1;
    int carta2 = r.nextInt(51 - 1) + 1;
    int carta3 = r.nextInt(51 - 1) + 1;
    int carta4 = r.nextInt(51 - 1) + 1;
    int carta5 = r.nextInt(51 - 1) + 1;
    int carta6 = r.nextInt(51 - 1) + 1;



    //Arrays
    public int[] slide_image = {
            R.drawable.card_1,
            R.drawable.card_2,
            R.drawable.card_3,
            R.drawable.card_4,
            R.drawable.card_5,
            R.drawable.card_6,
            R.drawable.card_7,
            R.drawable.card_8,
            R.drawable.card_9,
            R.drawable.card_10,
            R.drawable.card_11,
            R.drawable.card_12,
            R.drawable.card_13,
            R.drawable.card_14,
            R.drawable.card_15,
            R.drawable.card_16,
            R.drawable.card_17,
            R.drawable.card_18,
            R.drawable.card_19,
            R.drawable.card_20,
            R.drawable.card_21,
            R.drawable.card_22,
            R.drawable.card_23,
            R.drawable.card_24,
            R.drawable.card_25,
            R.drawable.card_26,
            R.drawable.card_27,
            R.drawable.card_28,R.drawable.card_29, R.drawable.card_30, R.drawable.card_31,R.drawable.card_32,
            R.drawable.card_33, R.drawable.card_34, R.drawable.card_35,R.drawable.card_36,
            R.drawable.card_37, R.drawable.card_38, R.drawable.card_39,
            R.drawable.card_40, R.drawable.card_41, R.drawable.card_42,R.drawable.card_43,
            R.drawable.card_44, R.drawable.card_45, R.drawable.card_46,R.drawable.card_47,
            R.drawable.card_48, R.drawable.card_49, R.drawable.card_50,R.drawable.card_51

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