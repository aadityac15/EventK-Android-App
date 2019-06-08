package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
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

public class SignupActivtiy extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    Button signup;
    TextView textView;
    String email, password;
    String TAG = "SignupActivity";
    android.support.v7.app.ActionBar actionBar;
    private FirebaseAuth mAuth;

    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.textView3);
        inputEmail = findViewById(R.id.email_id);
        inputPassword = findViewById(R.id.password);
        inputEmail.setInputType(InputType.TYPE_CLASS_TEXT);
        inputPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupk();
            }
        });

    }

    //Firebase Login Setup Documentation
    private void signupk() {
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();
        //textView.setText(password);


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignupActivtiy.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivtiy.this, SignInActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivtiy.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

}
