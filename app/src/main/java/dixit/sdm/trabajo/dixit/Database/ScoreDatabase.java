package dixit.sdm.trabajo.dixit.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Score.class}, version = 1, exportSchema = false)
public abstract class ScoreDatabase extends RoomDatabase {

    private static ScoreDatabase scoreDatabase;

    public synchronized static ScoreDatabase getInstance(Context context) {
        if (scoreDatabase == null) {
            scoreDatabase = Room
                    .databaseBuilder(context, ScoreDatabase.class, "score_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return scoreDatabase;
    }

    public abstract ScoreDAO scoreDAO();
}
