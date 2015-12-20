package mobop.capitole.domain.net;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import mobop.capitole.Capitole;

/**
 * Api Connection class used to retrieve data from the cloud.
 */
public class ApiConnection{

  private String url;
  private Context context;

  public ApiConnection(String url, Context context){
    this.url = url;
    this.context = context;
  }

  /**
   * Get the json from an API
   */
  public void getJson(final VolleyCallback callback){

    JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                  callback.onSuccess(response);
                }
              }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                  Toast.makeText(context, "Unable to fetch data", Toast.LENGTH_SHORT).show();

                }
            });

    // Access the RequestQueue through your singleton class.
    Capitole.getInstance().add(jsObjRequest);

  }

  public interface VolleyCallback {
    void onSuccess(JSONObject response);
  }

}


