package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class TermsActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBar actionBar;

    protected void onCreate(Bundle savedBundleInstance) {

        super.onCreate(savedBundleInstance);
        setContentView(R.layout.terms_conditions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //Google Navigation Drawer Documentation.
        mDrawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        final DrawerLayout finalMDrawerLayout = mDrawerLayout;
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        int id = menuItem.getItemId();
                        if (id == R.id.eventlist) {
                            startActivity(new Intent(TermsActivity.this, EventBriteApiActivity.class));
                        } else if (id == R.id.welcomek) {
                            startActivity(new Intent(TermsActivity.this, WelcomePageActivity.class));
                        } else if (id == R.id.aboutus) {
                            startActivity(new Intent(TermsActivity.this, AboutUsActivity.class));
                        } else if (id == R.id.termsandcond) {
                            startActivity(new Intent(TermsActivity.this, TermsActivity.class));
                        }
                        finalMDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        if (item.isChecked()) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}

