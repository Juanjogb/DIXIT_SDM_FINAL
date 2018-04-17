package dixit.sdm.trabajo.dixit.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private String email;
    private String ticket;
    private String username;
    private String avatar;
    private SharedPreferences prefs;

    public Session(Context ctx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        email = prefs.getString("email", null);
        ticket = prefs.getString("ticket", null);
        username = prefs.getString("username", null);
        avatar = prefs.getString("avatar", null);
    }

    //Para debug
    public String[] getSession() {
        return new String[]{getEmail(), getTicket(), getUsername(), getAvatar()};
    }

    public boolean isEmpty(){
        return email == null;
    }
    public void save(){

    }
    public void destroy(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("email");
        editor.remove("avatar");
        editor.remove("ticket");
        editor.remove("username");
        editor.apply();
    }
    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getTicket() {
        return this.ticket;
    }
}
