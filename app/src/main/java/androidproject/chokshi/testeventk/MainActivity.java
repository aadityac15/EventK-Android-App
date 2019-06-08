package androidproject.chokshi.testeventk;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "MainActivity";
    protected DrawerLayout mDrawerLayout;
    protected ActionBar actionBar;
    private BroadcastReceiver broadcastReceiver;
    private String mUsername;
    private SharedPreferences mSharedPreferences;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private RecyclerView eventsRecyclerView;
    private EventsRecyclerAdapter eventsAdapter;
    private View view;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        mDrawerLayout = findViewById(R.id.drawerLayout);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        final DrawerLayout finalMDrawerLayout = mDrawerLayout;
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        finalMDrawerLayout.closeDrawers();

                        int id = menuItem.getItemId();
                        if (id == R.id.eventlist) {
                            startActivity(new Intent(MainActivity.this, EventBriteApiActivity.class));
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUsername = "anonymous";
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUser = null;
        if (mUser == null) {
            startActivity(new Intent(this, FirstPageActivity.class));
            finish();

        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API).build();


    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void onClick(View view) {

    }

    public View getView() {
        return view;
    }
}
