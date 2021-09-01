package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contacts;
    private static String JSON_URL = "https://assets.gpshopper.com/jc/android_eval.json";
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.contactlist);
        contacts = new ArrayList<>();

        GetData getdata = new GetData();
        getdata.execute();

    }


    public class GetData extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try {
                URL url;
                HttpsURLConnection urlConnection= null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpsURLConnection) url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    int data = inputStreamReader.read();
                    while(data!= -1){
                        current += (char)data;
                        data = inputStreamReader.read();

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null ){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("roster");
                for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject contactObject = jsonArray.getJSONObject(i);
                        Contacts contact = new Contacts();
                        contact.setName(contactObject.getString("name").toString());
                        contact.setPosition(contactObject.getString("position").toString());
                        contact.setImage_url(contactObject.getString("image_url").toString());
                        contacts.add(contact);
                    }
                }catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter = new ContactsAdapter(MainActivity.this,contacts);
            recyclerView.setAdapter(adapter);
        }
    }
}