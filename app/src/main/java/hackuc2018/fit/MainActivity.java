package hackuc2018.fit;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private String username = "";
    private FirebaseAuth.AuthStateListener authStateListener;

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

