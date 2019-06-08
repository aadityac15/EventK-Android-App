package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//Youtube tutorial : https://youtu.be/jvaYsA5u0Ww (Introduction to Volley) was referred.

public class EventBriteApiActivity extends AppCompatActivity {

    protected ActionBar actionBar;
    protected DrawerLayout mDrawerLayout;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private Button btn1;
    private String TAG = "EventBriteApi";
    private String url = "https://www.eventbriteapi.com/v3/events/search/?token=GRRLOXY6O4QPGS535Y7P&expand=organizer,venue&sort_by=date&location.latitude=37.7648&location.longitude=-122.463";
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView eventsRecyclerView;
    private EventsRecyclerAdapter eventsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbritek);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn1 = findViewById(R.id.eventbutton);
        //NavigationDrawer Android documentation.
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
                            startActivity(new Intent(EventBriteApiActivity.this, EventBriteApiActivity.class));
                        } else if (id == R.id.welcomek) {
                            startActivity(new Intent(EventBriteApiActivity.this, WelcomePageActivity.class));
                        } else if (id == R.id.aboutus) {
                            startActivity(new Intent(EventBriteApiActivity.this, AboutUsActivity.class));
                        } else if (id == R.id.termsandcond) {
                            startActivity(new Intent(EventBriteApiActivity.this, TermsActivity.class));
                        }
                        finalMDrawerLayout.closeDrawers();
                        return true;
                    }


                });

        requestQueue = Volley.newRequestQueue(this);
        fetchEvents();
    }
//https://stackoverflow.com/questions/5015844/parsing-json-object-in-java StackOverflow answer.
    private void fetchEvents() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                    JSONArray eventsJsonArr = response.getJSONArray("events");
                    for (int i = 0; i < eventsJsonArr.length(); i++) {
                        try {
                            JSONObject eventJsonObject = eventsJsonArr.optJSONObject(i);
                            String eventName = eventJsonObject.optJSONObject("name").getString("text");
                            String eventDescription = eventJsonObject.optJSONObject("description").getString("text");
                            long eventId = eventJsonObject.optLong("id");
                            String eventUrl = eventJsonObject.optString("url");
                            JSONObject startTimeJsonObject = eventJsonObject.optJSONObject("start");
                            String localStartTime = startTimeJsonObject.optString("local");
                            Date eventStartDate = simpleDateFormat.parse(localStartTime);
                            JSONObject endTimeJsonObject = eventJsonObject.optJSONObject("end");
                            String localEndTime = endTimeJsonObject.optString("local");
                            Date eventEndDate = simpleDateFormat.parse(localEndTime);
                            JSONObject venueJsonObject = eventJsonObject.optJSONObject("venue");
                            JSONObject addressJsonObject = venueJsonObject.optJSONObject("address");
                            double eventLatitude = addressJsonObject.optDouble("latitude");
                            double eventLongitude = addressJsonObject.optDouble("longitude");
                            String eventAddress = addressJsonObject.optString("localized_address_display");
                            JSONObject logoJsonObject = eventJsonObject.optJSONObject("logo");
                            String eventImgUrl = logoJsonObject.optString("url");
                            Event event = new Event(eventName, eventStartDate, eventImgUrl, eventLatitude, eventLongitude, eventDescription, eventAddress);
                            eventList.add(event);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(TAG, "Something went wrong: " + e.getMessage());
                        }

                    }
                    eventsAdapter = new EventsRecyclerAdapter(eventList);
                    eventsRecyclerView.setAdapter(eventsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.d(TAG, obj.toString());
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
                Toast.makeText(EventBriteApiActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(EventBriteApiActivity.this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
