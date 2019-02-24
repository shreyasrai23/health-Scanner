package com.example.peeyushrai.healthscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Overview extends AppCompatActivity {

    int respCode = 45;

    Button mainScanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        mainScanButton = findViewById(R.id.MainScanBtn);

        mainScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Overview.this, ScanActivity.class);
                startActivityForResult(i, 1000);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1000) {
//            System.out.print("RESPONSE CODE:::: "+ respCode);
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}
