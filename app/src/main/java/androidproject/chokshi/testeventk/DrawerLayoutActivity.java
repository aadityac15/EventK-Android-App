package androidproject.chokshi.testeventk;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

public class DrawerLayoutActivity extends AppCompatActivity {
    protected DrawerLayout mDrawerLayout;

    protected void onCreate(Bundle SavedBundleInstance) {
        super.onCreate(SavedBundleInstance);
        setContentView(R.layout.drawer_layout);
    }
}
