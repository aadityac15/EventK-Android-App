package androidproject.chokshi.testeventk;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
//Youtube tutorial : https://youtu.be/jvaYsA5u0Ww (Introduction to Volley) was referred.

public class MySingleton {
    private static MySingleton mySingleton;
    private static Context mContext;
    private RequestQueue requestQueue;


    private MySingleton(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mySingleton == null) {
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public RequestQueue getRequestQueue() {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        requestQueue.add(request);


    }
}
