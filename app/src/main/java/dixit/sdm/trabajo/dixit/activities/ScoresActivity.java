package dixit.sdm.trabajo.dixit.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import dixit.sdm.trabajo.dixit.Database.FriendScoreDatabase;
import dixit.sdm.trabajo.dixit.Database.Score;
import dixit.sdm.trabajo.dixit.Database.ScoreDatabase;
import dixit.sdm.trabajo.dixit.R;
import dixit.sdm.trabajo.dixit.adapters.ScoreAdapter;

public class ScoresActivity extends AppCompatActivity {

    ScoreAdapter localScoreAdapter;
    List<Score> localScoresList;

    ScoreAdapter friendScoreAdapter;
    List<Score> friendScoresList;
    SharedPreferences sharedPreferences = null;

    private class ClearScoreThread extends Thread {
        private ClearScoreThread() {

        }

        public void run() {
            ScoreDatabase.getInstance(ScoresActivity.this).scoreDAO().clearScores();
            FriendScoreDatabase.getInstance(ScoresActivity.this).scoreDAO().clearScores();
        }
    }

    private static class LocalTask extends AsyncTask<Void, Void, List<Score>> {

        private WeakReference<ScoresActivity> activity;
        LocalTask(ScoresActivity activity) {
            this.activity = new WeakReference(activity);
        }

        protected List<Score> doInBackground(Void... voids) {
            return ScoreDatabase.getInstance((Context) this.activity.get()).scoreDAO().getLocalScores();

        }

        protected void onPostExecute(List<Score> scores) {
            ((ScoresActivity) this.activity.get()).updateLocalScores(scores);
            //((ScoresActivity) this.activity.get()).updateFriendScores(scores);
        }

    }
    private static class FriendTask extends AsyncTask<Void, Void, List<Score>> {

        private WeakReference<ScoresActivity> activity;
        FriendTask(ScoresActivity activity) {
            this.activity = new WeakReference(activity);
        }

        protected List<Score> doInBackground(Void... voids) {
            return FriendScoreDatabase.getInstance((Context) this.activity.get()).scoreDAO().getLocalScores();

        }

        protected void onPostExecute(List<Score> scores) {
            //((ScoresActivity) this.activity.get()).updateLocalScores(scores);
            ((ScoresActivity) this.activity.get()).updateFriendScores(scores);
        }

    }

    private void updateLocalScores(List<Score> scores) {
        localScoresList = scores;

        //Create Adapter
        localScoreAdapter = new ScoreAdapter(this, R.layout.score_list_row, localScoresList);
        ((ListView) findViewById(R.id.lvLocal)).setAdapter(localScoreAdapter);
    }

    private void updateFriendScores(List<Score> scores) {
        friendScoresList = scores;

        //Create Adapter
        friendScoreAdapter = new ScoreAdapter(this, R.layout.score_list_row, friendScoresList);
        ((ListView) findViewById(R.id.lvFriends)).setAdapter(friendScoreAdapter);
    }

    /*
    private static class ScoresTask extends AsyncTask<String, Void, List<Score>> {
        private WeakReference<ScoresActivity> activity;


        ScoresTask(ScoresActivity activity) {
            this.activity = new WeakReference(activity);

        }

        protected List<Score> doInBackground(String... params) {

            List<Score> arraylist = new ArrayList();

            try {
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https");
                builder.authority("wwtbamandroid.appspot.com");
                builder.appendPath("rest");
                builder.appendPath("highscores");
                builder.appendQueryParameter("name", params[0]);
                HttpsURLConnection connection = (HttpsURLConnection) new URL(builder.build().toString()).openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                //Loop gson object to find HighScores
                for (HighScore hs : ((HighScoreList) new Gson().fromJson(new InputStreamReader(connection.getInputStream()), HighScoreList.class)).getScores()) {
                    arraylist.add(new Score(hs.getName(), Integer.parseInt(hs.getScoring())));
                }
            } catch (JsonParseException e) {
                Log.d("DEBUG", "JSONParseException:" + e.getMessage());
            } catch (MalformedURLException e2) {
                Log.d("DEBUG", "MalformedURLException: " + e2.getMessage());
            } catch (ProtocolException e3) {
                Log.d("DEBUG", "ProtocolException:" + e3.getMessage());
            } catch (IOException e4) {
                Log.d("DEBUG", "IOException:" + e4.getMessage());
            }

            return arraylist;
        }

        protected void onPostExecute(List<Score> result) {
            super.onPostExecute(result);
            //Updates listView Scores
            ((ListView) ((ScoresActivity) this.activity.get()).findViewById(R.id.lvFriends)).setAdapter(new ScoreAdapter((Context) this.activity.get(), R.layout.score_list_row, result));
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences("my_settings", Context.MODE_PRIVATE);

        //Tabs Control
        TabHost host = (TabHost) findViewById(R.id.my_tabhost);
        host.setup();

        //--- Tab1 ---
        TabHost.TabSpec spec = host.newTabSpec("local");
        // Tab Indicator specified as Label and Icon
        spec.setIndicator(getString(R.string.local),
                getResources().getDrawable(R.drawable.ic_person_outline));
        //spec.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_person_black_24dp,0, 0);

        // ?- spec.setContent(R.id.my_scroll_view1);
        spec.setContent(R.id.lvLocal);
        host.addTab(spec);

        //--- Tab2 --- Faltaria añadir parte online
        spec = host.newTabSpec("friends");
        // Tab Indicator specified as Label and Icon
        spec.setIndicator(getString(R.string.friends),
                getResources().getDrawable(R.drawable.ic_people_outline));

        spec.setContent(R.id.lvFriends);
        host.addTab(spec);


        String username = sharedPreferences.getString("username", "");
        //String username = getDefaults("username", this);
        new LocalTask(this).execute(new Void[0]);
        NetworkInfo networkInfo = ((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            Toast.makeText(this, "ERROR de red, no se ha podido instanciar la red", Toast.LENGTH_LONG).show();
        } else {
           // new ScoresTask(this).execute(new String[]{username, null, null});
        }

        new FriendTask(this).execute(new Void[0]);
        NetworkInfo networkInformation = ((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo == null || !networkInformation.isConnected()) {
            Toast.makeText(this, "ERROR de red, no se ha podido instanciar la red", Toast.LENGTH_LONG).show();
        } else {
            // new ScoresTask(this).execute(new String[]{username, null, null});
        }

    }


}
