package dixit.sdm.trabajo.dixit.activities;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dixit.sdm.trabajo.dixit.R;

import dixit.sdm.trabajo.dixit.adapters.SliderAdapter;


public class RulesActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private SliderAdapter sliderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        String head1 = getResources().getString(R.string.head1);
        String head2 = getResources().getString(R.string.head2);
        String head3 = getResources().getString(R.string.head3);
        String head4 = getResources().getString(R.string.head4);
        String head5 = getResources().getString(R.string.head5);
        String head6 = getResources().getString(R.string.head6);

        String desc1 = getResources().getString(R.string.desc1);
        String desc2 = getResources().getString(R.string.desc2);
        String desc3 = getResources().getString(R.string.desc3);
        String desc4 = getResources().getString(R.string.desc4);
        String desc5 = getResources().getString(R.string.desc5);
        String desc6 = getResources().getString(R.string.desc6);

        String[] slides_heading = {head1, head2, head3, head4, head5, head6};
        String[] slides_descs = {desc1, desc2, desc3, desc4, desc5, desc6};



        sliderAdapter = new SliderAdapter(this, slides_heading, slides_descs);
        mSlideViewPager.setAdapter(sliderAdapter);
    }
}