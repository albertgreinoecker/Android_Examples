package at.ac.htlinn.androidexamples.concurrent.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import at.ac.htlinn.androidexamples.R;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleAsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    Button fetchBtn;
    TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_async_task);
        fetchBtn = findViewById(R.id.fetch_btn);
        fetchBtn.setOnClickListener(this);
        contentTv = findViewById(R.id.content_view);
    }

    @Override
    public void onClick(View view) {
        SimpleAsyncTask task = new SimpleAsyncTask();
        task.execute("https://jsonplaceholder.typicode.com/todos/1");
    }

    /**
     * Solution done here with an inner Class, can also be a separate class
     * Do a simple download of a test JSON call
     * The types of AsyncTask<X,Y,Z> are:
     * X...parameters of doInBackground
     * Y...return type of onProgressUpdate
     * Z...return type of doInBackground
     */
    private class SimpleAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            //find more information on https://github.com/square/okhttp
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(urls[0])
                            .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Download failed";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            contentTv.setText(result);
            //we already know Gson for JSON Processing
            Gson gson = new Gson();
            Todo todo = gson.fromJson(result, Todo.class);
            contentTv.append("\n");
            contentTv.append(String.format("TITLE: %s\n",todo.getTitle()));

        }
    }

    class Todo
    {
        private int userId;
        private int id;

        private String title;

        public String getTitle() {
            return title;
        }

        private boolean completed;
    }
}