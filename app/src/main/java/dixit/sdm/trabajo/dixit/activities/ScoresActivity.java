package dixit.sdm.trabajo.dixit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import dixit.sdm.trabajo.dixit.R;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        //Tabs Control
        TabHost host = (TabHost) findViewById(R.id.my_tabhost);
        host.setup();

        //--- Tab1 ---
        TabHost.TabSpec spec = host.newTabSpec("mitab1");
        //Tab Indicator specified as Label and Icon
        spec.setIndicator("Local", getResources().getDrawable(R.drawable.ic_person_outline));
        spec.setContent(R.id.tab1);
        host.addTab(spec);

        //--- Tab2 ---
        spec = host.newTabSpec("mitab2");
        // Tab Indicator specified as Label and Icon
        spec.setIndicator("Amigos", getResources().getDrawable(R.drawable.ic_people_outline));
        spec.setContent(R.id.tab2);

        host.addTab(spec);

    }
}
