package hackuc2018.fit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final EditText username = (EditText)findViewById(R.id.Username);
        final EditText password = (EditText) findViewById(R.id.Password);

        auth = FirebaseAuth.getInstance();

        Button loginButton = (Button) findViewById(R.id.SignUp);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createAccount(username.getText().toString(), password.getText().toString());
            }
        });

    }

    //Makes sure that the fields are filled
    private boolean fieldsFilled()
    {
        final EditText username = (EditText)findViewById(R.id.Username);
        final EditText password = (EditText) findViewById(R.id.Password);

        return (!username.getText().toString().equals("") && !password.getText().toString().equals(""));
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
                            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                            startActivity(intent);

                            //updateUI(user);
                        }
                        else
                        {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
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
}
