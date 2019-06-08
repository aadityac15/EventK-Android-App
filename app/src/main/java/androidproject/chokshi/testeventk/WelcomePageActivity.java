package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class WelcomePageActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBar actionBar;
    private RecyclerView eventsRecyclerView;
    private EventsRecyclerAdapter eventsAdapter;
    private Button logout;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInAccount account;
    private GoogleSignInClient mGoogleSignInClient;


    public void onCreate(Bundle savedBundleInstance) {

        super.onCreate(savedBundleInstance);
        setContentView(R.layout.welcomepage);
        //Android Notificaton drawer documentation.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //Google SignIn and SignOut documentation.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUser = null;
                mGoogleSignInClient.signOut();
                startActivity(new Intent(WelcomePageActivity.this, FirstPageActivity.class));
            }
        });
        mDrawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        final DrawerLayout finalMDrawerLayout = mDrawerLayout;
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        int id = menuItem.getItemId();
                        if (id == R.id.eventlist) {
                            startActivity(new Intent(WelcomePageActivity.this, EventBriteApiActivity.class));
                        } else if (id == R.id.welcomek) {
                            startActivity(new Intent(WelcomePageActivity.this, WelcomePageActivity.class));
                        } else if (id == R.id.aboutus) {
                            startActivity(new Intent(WelcomePageActivity.this, AboutUsActivity.class));
                        } else if (id == R.id.termsandcond) {
                            startActivity(new Intent(WelcomePageActivity.this, TermsActivity.class));
                        }
                        finalMDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void onBackPressed() {

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
