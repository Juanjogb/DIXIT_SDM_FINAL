package dixit.sdm.trabajo.dixit.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Score.class}, version = 1, exportSchema = false)
public abstract class FriendScoreDatabase extends RoomDatabase {

    private static FriendScoreDatabase friendScoreDatabase;

    public synchronized static FriendScoreDatabase getInstance(Context context) {
        if (friendScoreDatabase == null) {
            friendScoreDatabase = Room
                    .databaseBuilder(context, FriendScoreDatabase.class, "friendScore_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return friendScoreDatabase;
    }

    public abstract ScoreDAO scoreDAO();
}