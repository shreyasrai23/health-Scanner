import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UserSignIn extends AppCompatActivity {

    private Button returnToMain;
    private String userName;
    private Object yob;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private int login_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        returnToMain = findViewById(R.id.button3);

        //Spinner
        Spinner yobSpinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> yobAdapter = new ArrayAdapter<String>(UserSignIn.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.yearsOfBirth));
        yobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yobSpinner.setAdapter(yobAdapter);
        

        //User user = new User();

        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText temp = findViewById(R.id.editUsername);
                userName = temp.getText().toString();
                User.setUserName(userName);
                temp = findViewById(R.id.editFname);
                fName = temp.getText().toString();
                User.setfirstName(fName);
                temp = findViewById(R.id.editLname);
                lName = temp.getText().toString();
                User.setLastName(lName);
                temp = findViewById(R.id.editEmail);
                email = temp.getText().toString();
                User.setEmail(email);
                temp = findViewById(R.id.editUserpassword);
                password = temp.getText().toString();
                User.setPassword(password);
                Spinner mSpinner = (Spinner) findViewById(R.id.spinner1);
                yob = mSpinner.getSelectedItem();


                GetAccountData g = new GetAccountData();
                g.execute();
                Intent t = new Intent(UserSignIn.this, ConditionSetup.class);
                startActivity(t);

            }
        });
    }
}
