package androidproject.chokshi.testeventk;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class SingleListActivity extends Activity {
    private RecyclerView eventsRecyclerView;
    private EventsRecyclerAdapter eventsAdapter;

    protected void onCreate(Bundle getSavedInstance) {
        super.onCreate(getSavedInstance);
        setContentView(R.layout.list);
    }
}
