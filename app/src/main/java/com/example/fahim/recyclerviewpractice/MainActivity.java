package com.example.fahim.recyclerviewpractice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        new asyncTask().execute();
    }


    public String fetchDataFromUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    class asyncTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                data = fetchDataFromUrl("https://api.androidhive.info/contacts/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);


            // Telling Gson to form data from "data" like "MyContacts.class" format
            MyContacts myContacts = new Gson().fromJson(data, MyContacts.class);
            recyclerView.setAdapter(new myAdapter(MainActivity.this, myContacts.getContacts()));
        }
    }
}
