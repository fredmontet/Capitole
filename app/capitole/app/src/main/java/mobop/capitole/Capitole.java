package mobop.capitole;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import mobop.capitole.domain.model.User;

/**
 * Created by fredmontet on 19/12/15.
 */
public class Capitole extends Application {

    private RequestQueue mRequestQueue;
    private static Capitole mInstance;
    private ImageLoader mImageLoader;
    private static Context mContext;
    private static User mUser;
    public static final String TAG = Capitole.class.getName();

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mUser = this.getUser();
        mContext = this.getBaseContext();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    //==============================================================================================
    // Functions
    //==============================================================================================

    /**
     * Singleton main method. Provides the global static instance of the helper class.
     * @return The Capitole instance.
     */
    public static synchronized Capitole getInstance() {
        return mInstance;
    }

    /**
     * Handle the Application user
     * @return The User
     */
    public User getUser(){
        if(this.mUser != null){
            return this.mUser;
        }else{
            return new User();
        }
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
     * Getter for the Volley Image Loader
     * @return
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * Cancels all the pending requests.
     */
    public void cancel() {
        mRequestQueue.cancelAll(TAG);
    }

}
