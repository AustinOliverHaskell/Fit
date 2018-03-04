package hackuc2018.fit;


import android.content.Intent;

import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;





/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth auth;
    private static final String TAG = "EmailPassword";




    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializes Firebase objects
        auth = FirebaseAuth.getInstance();

        LinearLayout signuptext = findViewById(R.id.signUp);
        signuptext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }

        });

        Button loginButton = findViewById(R.id.email_sign_in_button);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final EditText username = findViewById(R.id.email);
                final EditText password = findViewById(R.id.password);
                signIn(username.getText().toString(), password.getText().toString());
            }
        });

    }


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);
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
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            //updateUI(user);
                        }
                        else
                        {
                            TextView printsError = findViewById(R.id.Error);
                            printsError.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private boolean validateForm()
    {
        boolean valid = true;

        if (!fieldsFilled())
        {
            valid = false;
        }

        return valid;
    }


    //Makes sure that the fields are filled
    private boolean fieldsFilled()
    {
        final EditText username = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);

        return (!username.getText().toString().equals("") && !password.getText().toString().equals(""));
    }
}

