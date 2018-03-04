package hackuc2018.fit;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by austin on 3/3/18.
 */

public class Excercise
{
    private String name;
    private String catagory;
    private ArrayList<ESet> sets;
    private int reps;
    private float weight;
    private Date date;

    public Excercise()
    {
        this.name = null;
        this.sets = new ArrayList<ESet>();
        this.reps = 0;
        this.weight = 0.0f;
        this.date = null;
        this.catagory = null;
    }

    public Excercise(String str)
    {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public void addSet(ESet set)
    {
        this.sets.add(set);
    }

    public void removeSet(int index)
    {
        if (index < sets.size() && index >= 0)
        {
            this.sets.remove(index);
        }
    }
    // -------------------------------

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append(name);
        str.append(" ");
        str.append(reps);
        str.append(" ");
        str.append(weight);
        str.append(" ");
        str.append(date);
        str.append(" ");
        str.append(catagory);

        for (ESet set : sets)
        {
            str.append(" ");
            str.append(set);
        }

        return str.toString();
    }

}
