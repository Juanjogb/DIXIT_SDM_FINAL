package dixit.sdm.trabajo.dixit.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.adapters.RulesAdapter;
import dixit.sdm.trabajo.dixit.helpers.DepthPageTransformer;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        ViewPager viewPager = findViewById(R.id.viewPagerRules);
        viewPager.setAdapter(new RulesAdapter(this));
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }
}
