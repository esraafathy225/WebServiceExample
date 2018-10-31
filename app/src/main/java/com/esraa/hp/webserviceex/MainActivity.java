package com.esraa.hp.webserviceex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
ListView listView;
    String link="http://pastebin.com/raw/wgkJgazE";
    URL url;
    InputStream inputStream;
    String result;
    HttpURLConnection urlConnection;
    ArrayList<UserDetails> users;
    UserDetails details;
    int likes;
    String name;
    UserAdapter adapter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url=new URL(link);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            urlConnection= (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("GET");
                            inputStream=urlConnection.getInputStream();
                            int c=0;
                            StringBuffer buffer=new StringBuffer();
                            int responseCode = urlConnection.getResponseCode();
                            if (responseCode == HttpsURLConnection.HTTP_OK) {
                                while ((c = inputStream.read()) != -1) {
                                    buffer.append((char) c);
                                }
                            }
                            result=buffer.toString();
                            users=new ArrayList<>();
                            JSONArray array=new JSONArray(result);
                            for(int i=0;i<array.length();i++) {
                                JSONObject object = array.getJSONObject(i);
                                likes = object.getInt("likes");
                                JSONObject object1=object.getJSONObject("user");
                                name = object1.getString("name");
                                users.add(new UserDetails(name,likes));
                            }
                            adapter=new UserAdapter(MainActivity.this,users);
                            inputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finally {
                            urlConnection.disconnect();
                        }
                    }
                }).start();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(adapter);
                    }
                });

            }
        });








    }
}
