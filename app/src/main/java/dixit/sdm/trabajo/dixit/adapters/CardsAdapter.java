package dixit.sdm.trabajo.dixit.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dixit.sdm.trabajo.dixit.R;

public class CardsAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mImageIds = new int[]{R.drawable.uno, R.drawable.dos,
            R.drawable.tres, R.drawable.cuatro, R.drawable.cinco, R.drawable.seis,
            R.drawable.siete, R.drawable.ocho, R.drawable.nueve, R.drawable.diez,
            R.drawable.once, R.drawable.doce, R.drawable.trece, R.drawable.catorce,
            R.drawable.quince, R.drawable.dieciseis, R.drawable.diecisiete,
            R.drawable.dieciocho, R.drawable.diecinueve, R.drawable.veinte,R.drawable.veintiuno,
            R.drawable.veintidos, R.drawable.veintitres, R.drawable.veinticuatro,R.drawable.veinticinco,
            R.drawable.veintiseis, R.drawable.veintisiete, R.drawable.veintiocho};


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
