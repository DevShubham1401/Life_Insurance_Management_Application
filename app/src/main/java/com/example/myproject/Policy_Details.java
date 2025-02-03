//package com.example.myproject;
//
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class Policy_Details extends AppCompatActivity {
//
//    Button knowMoreButton1;
//    Button applyNowButton1;
//    Button knowMoreButton2;
//    Button applyNowButton2;
//    Button knowMoreButton3;
//    Button applyNowButton3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // Ensure you're inflating the correct layout file
//        setContentView(R.layout.activity_policy_details);
//
//        // Initialize buttons
//        knowMoreButton1 = findViewById(R.id.knowMoreButton1);
//        applyNowButton1 = findViewById(R.id.applyNowButton1);
//        knowMoreButton2 = findViewById(R.id.knowMoreButton2);
//        applyNowButton2 = findViewById(R.id.applyNowButton2);
//        knowMoreButton3 = findViewById(R.id.knowMoreButton3);
//        applyNowButton3 = findViewById(R.id.applyNowButton3);
//
//        // Set click listeners
//            knowMoreButton1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openUrl("https://www.youtube.com/watch?v=ebL-MQnuh84");
//                }
//            });
//
//
//            applyNowButton1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openApplyPage("Jeevan Umang");
//                }
//            });
//
//
//
//            knowMoreButton2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openUrl("https://www.youtube.com/watch?v=oSQUYxcKYNc");
//                }
//            });
//
//
//
//            applyNowButton2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openApplyPage("Jeevan Shanti");
//                }
//            });
//
//
//            knowMoreButton3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openUrl("https://www.youtube.com/watch?v=o2t9wUnbDEY");
//                }
//            });
//
//
//            applyNowButton3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openApplyPage("Bima Jyoti");
//                }
//            });
//
//    }
//
//    private void openUrl(String url) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        startActivity(intent);
//    }
//
//    private void openApplyPage(String policyName) {
//        Intent intent = new Intent(this, Customer_Details.class);
//        intent.putExtra("policy_name", policyName);
//        startActivity(intent);
//    }
//}



package com.example.myproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Policy_Details extends AppCompatActivity {

    Button knowMoreButton1;
    Button applyNowButton1;
    Button knowMoreButton2;
    Button applyNowButton2;
    Button knowMoreButton3;
    Button applyNowButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_details);

        // Initialize buttons
        knowMoreButton1 = findViewById(R.id.knowMoreButton1);
        applyNowButton1 = findViewById(R.id.applyNowButton1);
        knowMoreButton2 = findViewById(R.id.knowMoreButton2);
        applyNowButton2 = findViewById(R.id.applyNowButton2);
        knowMoreButton3 = findViewById(R.id.knowMoreButton3);
        applyNowButton3 = findViewById(R.id.applyNowButton3);

        // Set click listeners
        knowMoreButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.youtube.com/watch?v=ebL-MQnuh84");
            }
        });

        applyNowButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApplyPage("Jeevan Umang");
            }
        });

        knowMoreButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.youtube.com/watch?v=oSQUYxcKYNc");
            }
        });

        applyNowButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApplyPage("Jeevan Shanti");
            }
        });

        knowMoreButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.youtube.com/watch?v=o2t9wUnbDEY");
            }
        });

        applyNowButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApplyPage("Bima Jyoti");
            }
        });
    }

    // Method to open a URL (e.g., YouTube link)
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // Method to open the application page with policy name
    private void openApplyPage(String policyName) {
        Intent intent = new Intent(this, Customer_Details.class);
        intent.putExtra("policy_name", policyName);
        startActivity(intent);
    }
}
