package dixit.sdm.trabajo.dixit.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.adapters.RulesAdapter;
import dixit.sdm.trabajo.dixit.helpers.DepthPageTransformer;

public class RulesActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    //private LinearLayout mDotLayout;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        //mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);



    }
}
