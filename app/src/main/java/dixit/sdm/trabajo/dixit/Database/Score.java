package dixit.sdm.trabajo.dixit.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Usuario on 01/03/2018.
 */

@Entity(tableName = "score_table",indices = {@Index(unique = true, value = {"name", "score"})})
public class Score {


    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "score")
    private int score;

    public Score(){

    }

    public Score(@NonNull String name, @NonNull int score){

        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @NonNull
    public int getScore() {
        return score;
    }

    public void setScore(@NonNull int score) {
        this.score = score;
    }
}
