package mobop.capitole;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import mobop.capitole.domain.model.User;

/**
 * Created by fredmontet on 19/12/15.
 */
public class Capitole extends Application {

    private RequestQueue mRequestQueue;
    private static Capitole mInstance;
    private ImageLoader mImageLoader;
    private static Context mContext;
    private static String mUserUuid;
    private static Realm realm;
    public static final String TAG = Capitole.class.getName();

    //==============================================================================================
    // Life Cycle
    //==============================================================================================

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this.getBaseContext();

        // Realm configuration
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(mContext)
                .name("capitole.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        // Clear the realm from last time
        // If uncommented, throw an IllegalStateException...
        //Realm.deleteRealm(realmConfiguration);

        this.realm = Realm.getInstance(realmConfiguration);

        // Get the user
        SharedPreferences sharedPref = mContext.getSharedPreferences(getString(R.string.capitole_prefs), Context.MODE_PRIVATE);
        String userUuid = sharedPref.getString("userUuid", null);
        if(userUuid == null){
            this.mUserUuid = this.setUser().getUuid();
        }else{
            this.mUserUuid = userUuid;
        }

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
     * Get the Application user
     * @return The User
     */
    public User getUser(){
        return this.realm.where(User.class).equalTo("uuid", mUserUuid).findFirst();
    }

    /**
     * TODO Change this for real user management once
     * Set the Application user
     * @return The User
     */
    public User setUser(){
        this.realm.beginTransaction();
        User user = realm.createObject(User.class); // Create a new object
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setUuid(UUID.randomUUID().toString());
        this.realm.commitTransaction();

        SharedPreferences sharedPref = mContext.getSharedPreferences(getString(R.string.capitole_prefs), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userUuid", user.getUuid());
        editor.commit();

        return user;
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
     * @return the ImageLoader
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
