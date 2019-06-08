package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(700);
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
