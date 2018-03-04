package hackuc2018.fit;

import java.util.ArrayList;

/**
 * Created by Austin on 3/3/18.
 */

public class UserData
{
    ArrayList<Workout> workout;
    private int score;

    UserData()
    {
        workout = new ArrayList<Workout>();
        score = 0;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
