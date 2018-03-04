package hackuc2018.fit;

import android.util.Log;

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
    private FirebaseDatabase db;

    private String username;

    // ----- Private fields from database-----
    private UserData userData;

    /**
     *  Default constructor
     *
     *  @param username - Username to be reference for all subsequent database calls
     */
    public DBManager(String username)
    {
        // Get a ref to the database
        db = FirebaseDatabase.getInstance();

        DatabaseReference ref = db.getReference().child("Users/Austin");

        this.username = username;
        this.userData = new UserData();

        ref.addValueEventListener(generateListener());
    }

    public int getScore()
    {
        return userData.getScore();
    }

    public void setValue(String path, Object value)
    {
        DatabaseReference r = db.getReference().child(path);
        r.setValue(value);
    }


    public void push(String location)

    {

    }

    private void pull(DataSnapshot snap)
    {
        Log.d("Datasnapshot", snap.toString());
    }

    private ValueEventListener generateListener()
    {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pull(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Log.d("Database Error", "Failed to get data");
            }
        };

        return postListener;
    }

}
