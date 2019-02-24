package com.example.peeyushrai.healthscanner; /**
 * Created by shreyasrai on 6/9/18.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class GetAccountData extends AsyncTask<Void, Void, Void>
{
    @Override
    protected Void doInBackground(Void... params)
    {
        try {

            String url = "http://"+MainActivity.ip+":5000/registration";
            URL databaseURL = new URL(url);

            HttpURLConnection myConnection =
                    (HttpURLConnection) databaseURL.openConnection();
            myConnection.setRequestMethod("POST");

            // Create the data
            JSONObject myData = new JSONObject();

            myData.put("username", User.getUserName());
            myData.put("password_hash", User.getPassword());
            myData.put("email", User.getEmail());
            myData.put("first_name", User.getfirstName());
            myData.put("last_name", User.getLastName());
            myData.put("location_id", "1");
            myData.put("year_of_birth", "2048");

            System.out.println(myData);

            // Enable writing
            myConnection.setDoOutput(true);

            myConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // Write the data
            OutputStream os = myConnection.getOutputStream();
            os.write(myData.toString().getBytes());
            os.flush();
            //myConnection.getOutputStream().write(myData.getBytes()));

//            HttpResponseCache myCache = HttpResponseCache.install(
//                    getCacheDir(), 100000L);

            System.out.println(" RESPONSE CODE:  -------> "  + myConnection.getResponseCode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
