package com.example.myproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_display_customers extends AppCompatActivity {

    private TextView nameTextView, mobileTextView, emailTextView, policyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_customer_activity);

        // Initialize the views
        nameTextView = findViewById(R.id.fullName);
        mobileTextView = findViewById(R.id.mobileNumber);
        emailTextView = findViewById(R.id.email);
        policyTextView = findViewById(R.id.policyName);

        // Retrieve the customer object from the intent
        Customer customer = (Customer) getIntent().getSerializableExtra("customer");

        // Set the customer details to the TextViews
        if (customer != null) {
            nameTextView.setText("Name: " + customer.getName());
            mobileTextView.setText("Mobile: " + customer.getMobile());
            emailTextView.setText("Email: " + customer.getEmail());
            policyTextView.setText("Policy: " + customer.getPolicy());
        }
    }
}
