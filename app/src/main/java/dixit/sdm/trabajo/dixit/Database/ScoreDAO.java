package dixit.sdm.trabajo.dixit.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface ScoreDAO {

    @Insert(onConflict = 1)
    void addScore(Score score);

    @Query("DELETE FROM score_table")
    void clearScores();

    @Query("SELECT * FROM score_table ORDER BY score DESC")
    List<Score> getLocalScores();
}