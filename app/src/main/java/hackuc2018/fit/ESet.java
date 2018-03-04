package hackuc2018.fit;

/**
 * Created by austin on 3/4/18.
 */

public class ESet
{
    private int reps;
    private float weight;

    ESet()
    {
        this.reps = 0;
        this.weight = 0.0f;
    }

    // ----- Getters and Setters -----
    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    // -------------------------------

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append(reps);
        str.append(" ");
        str.append(weight);

        return str.toString();
    }
}
