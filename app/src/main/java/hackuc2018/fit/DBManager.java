package hackuc2018.fit;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Austin on 3/3/18.
 */

public class DBManager
{
    private FirebaseDatabase db;

    private String username;
    private Map<String, Object> data;

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

        DatabaseReference ref = db.getReference().child("Users/"+username);

        this.username = username;
        this.userData = new UserData();

        ref.addValueEventListener(generateListener());
    }

    public void setField(String key, Object value)
    {
        data.put(key, value);
    }

    public Object getField(String Key)
    {
        return data.get(Key);
    }

    public int getScore()
    {
        return userData.getScore();
    }

    public void push(String location)
    {
        // Update Changes
        DatabaseReference r = db.getReference().child(location);
        Log.d("Map", data.toString());

        r.setValue(data);
    }

    private void pull(DataSnapshot snap)
    {

        String str = snap.toString();
        str = str.replace("DataSnapshot ", "");
        str = str.replace("*value", "{"+username);
        str = str.replace("=", ":");
        str = str.replaceAll("([A-Za-z0-9]+)", "\"$1\"");

        data = readJSON(str);

        Log.d("Datasnapshot", str);
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

    private Map<String, Object> readJSON(String str)
    {
        Map<String, Object> map = null;

        try {

            ObjectMapper mapper = new ObjectMapper();

            map = new HashMap<String, Object>();

            // convert JSON string to Map
            map = mapper.readValue(str, new TypeReference<Map<String, Object>>(){});

            System.out.println(map);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

}
