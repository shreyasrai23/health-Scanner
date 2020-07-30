import android.app.Activity;
import android.os.AsyncTask;


import com.example.peeyushrai.healthscanner.User;

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

public class GetLoginData extends AsyncTask<String, String, String> {

    String name;
    String pass;

    private TaskCallBack myCallBack;


    public GetLoginData(TaskCallBack callBack)
    {
        myCallBack = callBack;
    }




    public static String loginAPIresponseCode;

//    public static int getloginAPIresponseCode()
//    {
//        return loginAPIresponseCode;
//    }




    @Override
    protected String doInBackground(String... strings) {
        try {
            String url = "http://"+MainActivity.ip+":5000/login";
            //String url = "http://192.168.86.33:5000/login";
            System.out.println(url);
            URL databaseURL = new URL(url);

            HttpURLConnection myConnection =
                    (HttpURLConnection) databaseURL.openConnection();
            myConnection.setRequestMethod("POST");

            //get name and password from what was entered by user
            name = User.getLogin_userName();
            pass = User.getLogin_password();

            // Create the data
            JSONObject myData = new JSONObject();

            myData.put("username", name);
            myData.put("password_hash", pass);

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
            loginAPIresponseCode = ""  +  myConnection.getResponseCode();
            //System.out.println("LOGIN RP CODE:  ---> " + myConnection.getResponseCode());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
          catch (JSONException e) {
            e.printStackTrace();
        }

        return loginAPIresponseCode;
    }

    @Override
    protected void onPostExecute (String r)
    {
        try {

            System.out.println("LOGIN RESPONSE CODE ----->" + loginAPIresponseCode);
            if (r.equals("200")) {
                UserLogin.loginState = true;
                UserLogin.flag = true;
                System.out.println("LOGIN STATE: " + UserLogin.loginState);

                MainActivity.logIn();
                myCallBack.done();
            } else {
                UserLogin.editNAME.setText("");
                UserLogin.editPASS.setText("");
                UserLogin.wrongLoginMsg.setText("Please enter a valid username & password!");
            }
            System.out.println(UserLogin.loginState);
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }

    }

}
