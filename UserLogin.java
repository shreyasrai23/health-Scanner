import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import org.json.JSONObject;
//
//import java.io.OutputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.concurrent.ExecutionException;
//
//import javax.net.ssl.HttpsURLConnection;


public class UserLogin extends AppCompatActivity implements TaskCallBack{

    public static String enteredUsername;
    public static String enteredPassword;

    private int responseCode = 22;

    public static EditText editNAME;
    public static EditText editPASS;
    public static TextView wrongLoginMsg;


    private Button returnToMain;
    private Button createAcc;
    public static boolean flag = false;

    public static boolean loginState = false;



    @Override
    public void onBackPressed() {}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //final GetLoginData obj = new GetLoginData(this);
        returnToMain = findViewById(R.id.button2);


        final TaskCallBack tempVar = this;

        //final EditText editNameVar = findViewById(R.id.editUsername);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //System.out.println("Login response code:  " +GetLoginData.loginAPIresponseCode);

                editNAME = findViewById(R.id.editUsername);
                //editNAME.setText("");
                enteredUsername = editNAME.getText().toString();
                User.setLogin_userName(enteredUsername);
                editPASS = findViewById(R.id.editUserpassword);
                //editPASS.setText("");8
                enteredPassword = editPASS.getText().toString();
                User.setLogin_passWord(enteredPassword);


                wrongLoginMsg = findViewById(R.id.errorMessage);
                wrongLoginMsg.setText("");


                final GetLoginData obj2 = new GetLoginData(tempVar);
                obj2.execute();

            }
        });

        createAcc = findViewById(R.id.button4);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When scan button is clicked, a new intent is created and the second screen (scan screen) is launched
                Intent i = new Intent(UserLogin .this, UserSignIn.class);
                // The request code is just an identifier number that is used by this activity to mao the result to the activity that is being launched.
                startActivity(i);
            }
        });
    }

    public void done()
    {
        Intent g = new Intent(UserLogin.this, Overview.class);
        //startActivityForResult(g, responseCode);
        startActivity(g);
        UserLogin.this.finish();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // Check which request we're responding to
//        if (requestCode == 22) {
//
////            System.out.print("RESPONSE CODE:::: "+ responseCode);
//            // Make sure the request was successful
//            if (resultCode == RESULT_OK) {
//                finish();
//            }
//        }
//    }



}
