package mobop.capitole;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by fredmontet on 19/12/15.
 */
public class Capitole extends Application {

    private RequestQueue mRequestQueue;
    private static Capitole mInstance;
    public static final String TAG = Capitole.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * Singleton main method. Provides the global static instance of the helper class.
     * @return The Capitole instance.
     */
    public static synchronized Capitole getInstance() {
        return mInstance;
    }

    /**
     * Provides the general Volley request queue.
     */
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * Adds the request to the general queue.
     * @param req The object Request
     * @param <T> The type of the request result.
     */
    public <T> void add(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * Cancels all the pending requests.
     */
    public void cancel() {
        mRequestQueue.cancelAll(TAG);
    }

}