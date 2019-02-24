package com.example.peeyushrai.healthscanner;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;

import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    public static String ip = "192.168.86.34";

    public static TextView tvresult;
    private  Button btn;
    private static Button loginBTN;
    private Button profileButton;
    //private Button condButton;
    private static boolean isLoggedIn;
    //private Button signInBtn;
    public static TextView ingredients;
    public static String barcode;
    public static TextView itemName;
    public static TextView brandName;
    public static TextView badIngredients;
    public static DatabaseHelper db;
    public static TextView badIngredientsLabel;
    public static TextView goodIngredientsLabel;
    public static ImageView itemImage;
    public static RecyclerView ingrRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public static void logIn()
    {
        loginBTN.setText("Logout");
        isLoggedIn = true;
    }

    public void logOut()
    {
        loginBTN.setText("Login");
        isLoggedIn = false;
        Intent goBackToLogin = new Intent(this, UserLogin.class);
        startActivity(goBackToLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(isLoggedIn == false)
        {
            Intent LogInFirst = new Intent(MainActivity.this, UserLogin.class);
            startActivity(LogInFirst);
        }





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link the variables to Activity fields
        loginBTN = findViewById(R.id.logB);
        loginBTN.setText("Login");
        isLoggedIn = false;
        logIn();
        tvresult = findViewById(R.id.scanContent);
        ingredients = findViewById(R.id.goodIngredients);
        ingredients.setTextColor(Color.WHITE);
        itemName = findViewById(R.id.itemName);
        brandName = findViewById(R.id.brandName);
        badIngredients = findViewById(R.id.badIngredients);
        badIngredients.setTextColor(Color.RED);
        badIngredientsLabel = findViewById(R.id.badIngredientsLabel);
        goodIngredientsLabel = findViewById(R.id.goodIngredientsLabel);
        itemImage = findViewById(R.id.itemImage);

        ingrRecyclerView = findViewById(R.id.ingredients_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        ingrRecyclerView.setLayoutManager(layoutManager);



        //Log.i("Now Calling", "Database");
        db = new DatabaseHelper(this);
        // delete old data and create data again
        db.deleteData();
        db.insertUsers();
        db.insertConditions();

        btn = findViewById(R.id.button);
        tvresult.setText("Barcode");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When scan button is clicked, a new intent is created and the second screen (scan screen) is launched
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                // The request code is just an identifier number that is used by this
                // activity to mao the result to the activity that is being launched.
                startActivityForResult(intent, 1000);
            }
        });


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (isLoggedIn == false)
                {
                    logIn();
                    Intent i = new Intent(MainActivity.this, UserLogin.class);
                    startActivityForResult(i, 2000);
                }
                else
                {
                    logOut();
                }
            }
        });

        profileButton = findViewById(R.id.HomeToConditions);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intnt = new Intent(MainActivity.this, ProfilePage.class);
                startActivity(intnt);
            }
        });

    }
    // This method is invoked when this activity receives a result from another activity - in this case scan activity

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Commented the lines below as they were causing the app to crash when back button was pressed as the result was null
        //String logBarCode = data.getStringExtra("barcode");
        //Log.i("In Main", logBarCode);
        switch (requestCode){
            case 1000:
                if (resultCode == RESULT_OK) {
                    barcode = data.getStringExtra("barcode");
                    //Log.i("Final Barcode: ", barcode);
                    tvresult.setText(barcode);
                    // Below is a test barcode that can be used to get ingredient data values
                    // if no product is available. To use, just uncomment the line and set
                    // the barcode variable
                    //barcode = "52200004265";
                    getData process = new getData();
                    process.execute();
                }
        }

    }
}
