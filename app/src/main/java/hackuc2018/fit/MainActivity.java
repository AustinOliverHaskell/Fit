package hackuc2018.fit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private String username = "";
    private FirebaseAuth.AuthStateListener authStateListener;

    DBManager db = new DBManager("Austin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        startFirebase();

        if (auth.getCurrentUser() == null)
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            auth.removeAuthStateListener(authStateListener);
            startActivity(intent);
        }
        else
        {
            username = auth.getCurrentUser().getEmail();
        }

        TextView tv = (TextView) findViewById(R.id.score);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.push("Austin");
            }
        });

    }

    private FirebaseAuth.AuthStateListener generateAuthListener()
    {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // The user is singed in
                } else {
                    // The user is signed out
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    auth.removeAuthStateListener(authStateListener);
                    MainActivity.this.finish();
                    startActivity(intent);
                }
            }
        };
    }

    public void startFirebase()
    {
        // Fill in our firebase refrences
        auth = FirebaseAuth.getInstance();

        // Listener to check if the user logs out
        authStateListener = generateAuthListener();
    }
}

