package hackuc2018.fit;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by Austin on 3/3/18.
 */

public class DBManager
{
    private DatabaseReference db;

    private String username;

    // ----- Private fields from database-----
    private int score;

    /**
     *  Default constructor
     *
     *  @param username - Username to be reference for all subsequent database calls
     */
    public DBManager(String username)
    {
        // Get a ref to the database
        db = FirebaseDatabase.getInstance().getReference();

        this.username = username;

        refresh();
    }

    public int getScore()
    {
        return this.score;
    }

    public void refresh()
    {
        // TODO: Pull from database
    }
}
