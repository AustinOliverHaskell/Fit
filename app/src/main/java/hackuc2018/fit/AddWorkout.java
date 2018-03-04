package hackuc2018.fit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddWorkout extends AppCompatActivity {

    private DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        db = new DBManager("Austin");

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText sets = (EditText) findViewById(R.id.Sets);
        final EditText reps = (EditText) findViewById(R.id.Reps);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = "Austin";

                // All fields are filled
                if(!hasUserLeftFieldBlank())
                {

                    if (reps.getText().toString().matches("\\d+") && (sets.getText().toString().matches("\\d+")))
                    {
                        addToWorkoutForUser(username, name.getText().toString(), Integer.parseInt(reps.getText().toString()), Integer.parseInt(sets.getText().toString()));

                        Intent intent = new Intent(AddWorkout.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        TextView tv = (TextView) findViewById(R.id.error_text);
                        tv.setText("Reps and Sets must be whole numbers");
                        tv.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    TextView tv = (TextView) findViewById(R.id.error_text);
                    tv.setText("All Fields are Required");
                    tv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void addToWorkoutForUser(String username, String ExsName, int Repcount, int SetNumber)
    {
        db.setValue(username + "/value/Workouts/" + ExsName + "/reps", Repcount);
        db.setValue(username + "/value/Workouts/" + ExsName + "/sets", SetNumber);
    }

    private boolean hasUserLeftFieldBlank()
    {
        boolean retVal;

        boolean name = ((EditText) findViewById(R.id.name)).getText().toString().equals("");
        boolean sets = ((EditText) findViewById(R.id.Sets)).getText().toString().equals("");
        boolean reps = ((EditText) findViewById(R.id.Reps)).getText().toString().equals("");

        retVal = (name || sets || reps);

        return retVal;
    }
}
