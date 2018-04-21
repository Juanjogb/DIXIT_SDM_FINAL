package dixit.sdm.trabajo.dixit.helpers;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import dixit.sdm.trabajo.dixit.activities.MainActivity;

public class SendTokenTask extends AsyncTask<String[], Void, String> {

    private WeakReference<MainActivity> activity;

    public SendTokenTask(MainActivity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    protected String doInBackground(String[]... params) {
        try {
            URL url = new URL(params[0][1]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod(params[0][0]);
            return readStream(urlConnection.getInputStream());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String a) {

    }

    // Convierte un InputStream a String

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}