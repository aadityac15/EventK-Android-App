package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class EventInfoActivity extends AppCompatActivity {
    ActionBar actionBar;
    TextView eventTitle, eventDate, eventDesc, eventAddr;
    ImageView imageView;
    Button buttonMaps;

    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_eventinfo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        imageView = findViewById(R.id.eventimg);
        buttonMaps = findViewById(R.id.buttonMap);
        eventTitle = findViewById(R.id.eventtitle);
        eventDate = findViewById(R.id.eventdate);
        eventAddr = findViewById(R.id.eventaddress);
        eventDesc = findViewById(R.id.eventdesc);
        final double latitude = (double) getIntent().getExtras().get("latitude");
        final double longitude = (double) getIntent().getExtras().get("longitude");
        String eventAddress = getIntent().getStringExtra("eventAddress");
        String eventdate = getIntent().getStringExtra("eventdate");
        String imgUrl = getIntent().getStringExtra("imgurl");
        final String title = getIntent().getStringExtra("title");
        String description = (String) getIntent().getExtras().get("description");
        eventTitle.setText(title);
        eventDate.setText(eventdate);
        eventDesc.setText(description);
        eventAddr.setText(eventAddress);
        Glide.with(this).load(imgUrl).into(imageView);
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventInfoActivity.this, MapsActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });


    }
}
