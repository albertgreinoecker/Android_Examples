package at.ac.htlinn.androidexamples.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import at.ac.htlinn.androidexamples.R;
import at.ac.htlinn.androidexamples.thingworx.ThingWorxActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONActivity extends AppCompatActivity {
    Gson gson;
    private OkHttpClient client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonactivity);
        gson = new Gson();
        client = new OkHttpClient();
    }

    public  void jsonPerson(View v)
    {
        Person p = new Person("Hubert", 30);
        String psStr = gson.toJson(p);
        Log.d("JSONDEMO", psStr);

        Person p2 =  gson.fromJson(psStr, Person.class);
        Log.d("JSONDEMO", "READ FROM str: :" + p);
    }

    public void jsonDummyProduct(View v)
    {
        JSONActivity.GetDummyAsyncTask task = new JSONActivity.GetDummyAsyncTask();
        task.execute();
    }

    private class GetDummyAsyncTask extends AsyncTask<String, Void, Product> {
        protected Product doInBackground(String... urls) {
            Product obj = null;
            String url = "https://dummyjson.com/products/1";
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept", "application/json")
                    .get()
                    .build();
            Log.d("JSONDEMO", "REQUEST:" + request);
            String responseStr = "";
            try  {
                Response response = client.newCall(request).execute();
                responseStr =  response.body().string();
                Log.d("JSONDEMO", "SERVER ANSWER:" + responseStr);
                obj = new Gson().fromJson(responseStr, Product.class);
                Log.d("JSONDEMO", "AS OBJECT:" + obj);

            } catch (Exception e)
            {
                Log.e("JSONDEMO", String.valueOf(e));
            }

            return obj;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Product result) {
            TextView feedback = findViewById(R.id.feedback);
            feedback.setText(result.getTitle());
        }
    }
}