package dixit.sdm.trabajo.dixit.helpers;

import android.app.Activity;
import android.app.ActivityManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.List;

import dixit.sdm.trabajo.dixit.activities.MainActivity;

import static dixit.sdm.trabajo.dixit.helpers.Const.BASE_URL;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", "Refreshed token: " + refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
    }
    public String token(){
        return FirebaseInstanceId.getInstance().getToken();
    }
}
