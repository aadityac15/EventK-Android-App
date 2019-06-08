package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class FirstPageActivity extends AppCompatActivity implements View.OnClickListener {
    Button login1, signup1;
    android.support.v7.app.ActionBar actionBar;

    protected void onCreate(Bundle savedBundleInstance) {

        super.onCreate(savedBundleInstance);
        setContentView(R.layout.first_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        assert actionBar != null;
        login1 = findViewById(R.id.loginbutton1);
        signup1 = findViewById(R.id.signupbutton1);
        login1.setOnClickListener(this);
        signup1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginbutton1:
                startActivity(new Intent(this, SignInActivity.class));
                break;
            case R.id.signupbutton1:
                startActivity(new Intent(this, SignupActivtiy.class));
                break;
        }


    }
}
