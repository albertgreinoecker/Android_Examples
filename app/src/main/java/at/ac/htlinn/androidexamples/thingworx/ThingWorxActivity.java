package at.ac.htlinn.androidexamples.thingworx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import at.ac.htlinn.androidexamples.R;
import at.ac.htlinn.androidexamples.concurrent.asynctask.SimpleAsyncTaskActivity;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ThingWorxActivity extends AppCompatActivity {
    private OkHttpClient client = null;
    final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    String appKey = "";
    String baseURl = "";
    String thing = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        client = new OkHttpClient();
        appKey = getString(R.string.app_key);
        baseURl = getString(R.string.url);
        thing = getString(R.string.thing);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing_worx);
    }
    
    public void writeOnServer(View view)
    {
        PutServerAsyncTask task  = new PutServerAsyncTask();
        task.execute();
    }
    public void getFromServer(View view)
    {
        GetServerAsyncTask task  = new GetServerAsyncTask();
        task.execute();
    }

    private class PutServerAsyncTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            String sendKey = ((EditText)findViewById(R.id.key_to_server)).getText().toString();
            String sendValue = ((EditText)findViewById(R.id.value_to_server)).getText().toString();
            JSONObject jsonObject = new JSONObject();

            String url = String.format("%s/Thingworx/Things/%s/Properties/*", baseURl, thing); //Add wildcard to request for manipulation
            try {
                jsonObject.put(sendKey, sendValue);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON); //payLoad

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("appkey", appKey)
                    .put(body)
                    .build();
            Log.d("ThingWorxActivity", request.toString());
            String responseStr = "";
            try  {
                Response response = client.newCall(request).execute();
                //responseStr =  response.body().string();
                responseStr =  response.toString();
                Log.d("ThingWorxActivity", responseStr);
            } catch (IOException e)
            {
                Log.e("ThingWorxActivity", String.valueOf(e));
            }
            return responseStr;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView feedback = findViewById(R.id.to_server_feedback);
            feedback.setText(result);
        }
    }

    private class GetServerAsyncTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String sendKey = ((EditText)findViewById(R.id.key_from_server)).getText().toString();
            String url = String.format("%s/Thingworx/Things/%s/Properties/%s", baseURl, thing, sendKey); //Add the parameter to the url to fetch value
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept", "application/json")
                    .addHeader("appkey", appKey)
                    .get()
                    .build();
            Log.d("ThingWorxActivity", request.toString());
            String responseStr = "";
            try  {
                Response response = client.newCall(request).execute();
                //responseStr =  response.body().string();
                responseStr =  response.body().string();
                Log.d("ThingWorxActivity", responseStr);


                JsonObject obj = new Gson().fromJson(responseStr, JsonObject.class);

                responseStr = obj.getAsJsonArray("rows").get(0).getAsJsonObject().get(sendKey).getAsString();
            } catch (Exception e)
            {
                Log.e("ThingWorxActivity", String.valueOf(e));
            }

            return responseStr;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView feedback = findViewById(R.id.from_server_value);
            feedback.setText(result);
        }
    }

}