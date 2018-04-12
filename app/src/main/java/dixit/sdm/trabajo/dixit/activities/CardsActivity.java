package dixit.sdm.trabajo.dixit.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.adapters.CardsAdapter;
import dixit.sdm.trabajo.dixit.helpers.DepthPageTransformer;

public class CardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        ViewPager viewPager = findViewById(R.id.viewPagerCards);
        viewPager.setAdapter(new CardsAdapter(this));
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }
}
