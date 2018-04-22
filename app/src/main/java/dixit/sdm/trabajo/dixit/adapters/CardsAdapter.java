package dixit.sdm.trabajo.dixit.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dixit.sdm.trabajo.dixit.R;

public class CardsAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mImageIds = new int[]{R.drawable.card_1, R.drawable.card_2,
            R.drawable.card_3, R.drawable.card_4, R.drawable.card_5, R.drawable.card_6,
            R.drawable.card_7, R.drawable.card_8, R.drawable.card_9, R.drawable.card_10,
            R.drawable.card_11, R.drawable.card_12, R.drawable.card_13, R.drawable.card_14,
            R.drawable.card_15, R.drawable.card_16, R.drawable.card_17,
            R.drawable.card_18, R.drawable.card_19, R.drawable.card_20,R.drawable.card_21,
            R.drawable.card_22, R.drawable.card_23, R.drawable.card_24,R.drawable.card_25,
            R.drawable.card_26, R.drawable.card_27, R.drawable.card_28,
            R.drawable.card_29, R.drawable.card_30, R.drawable.card_31,R.drawable.card_32,
            R.drawable.card_33, R.drawable.card_34, R.drawable.card_35,R.drawable.card_36,
            R.drawable.card_37, R.drawable.card_38, R.drawable.card_39,
            R.drawable.card_40, R.drawable.card_41, R.drawable.card_42,R.drawable.card_43,
            R.drawable.card_44, R.drawable.card_45, R.drawable.card_46,R.drawable.card_47,
            R.drawable.card_48, R.drawable.card_49, R.drawable.card_50,R.drawable.card_51};


    public CardsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
