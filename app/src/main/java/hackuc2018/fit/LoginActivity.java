package hackuc2018.fit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.NonNull;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private FirebaseAuth auth;
    private static final String TAG = "EmailPassword";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializes Firebase objects
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);
    }


    private void createAccount(String email, String password)
    {
        Log.d(TAG, "createAccount:" + email);

        if (!validateForm()) {
            return;
        }

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG, "createUserWithEmail: success");
                            FirebaseUser user = auth.getCurrentUser();
                            //updateUI(user);
                        }
                        else
                        {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    private void signIn(String email, String password)
    {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm())
        {
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            //updateUI(user);
                        }
                    }
                });
    }

    private boolean validateForm()
    {
        boolean valid = true;

        String email = ((TextView) findViewById(R.id.email)).toString();
        if (!fieldsFilled())
        {
            valid = false;
        }

        String password = ((TextView) findViewById(R.id.password)).toString();
        if(!fieldsFilled())
        {
            valid = false;
        }

        return valid;
    }


    //Makes sure that the fields are filled
    private boolean fieldsFilled()
    {
        String username = ((TextView) findViewById(R.id.email)).toString();
        String password = ((TextView) findViewById(R.id.password)).toString();

        return (!username.equals("") && !password.equals(("")));
    }

    @Override
    public void onClick(View v)
    {
        int i = v.getId();
        if(i == R.id.email_sign_in_button)
        {
            signIn((findViewById(R.id.email)).toString(), (findViewById(R.id.password)).toString());
        }
        else if (i == R.id.signUp)
        {
            createAccount((findViewById(R.id.email)).toString(), (findViewById(R.id.password)).toString());
        }
    }
}
