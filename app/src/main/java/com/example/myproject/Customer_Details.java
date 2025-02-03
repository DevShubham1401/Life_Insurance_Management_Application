package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Customer_Details extends AppCompatActivity {

    private EditText fullName, mobileNumber, email, age, address, annualIncome, policyNameEditText, totalPolicyAmount, planTerm, totalPremiumAmount;
    private RadioGroup genderGroup, premiumPayingFrequencyGroup;
    private RadioButton selectedGender, selectedFrequency;
    private Button submitButton, clearButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        // Retrieve the policy name passed from Policy_Details activity
        Intent intent = getIntent();
        String policyName = intent.getStringExtra("policy_name");

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Customers");

        // Initialize UI elements
        fullName = findViewById(R.id.fullName);
        mobileNumber = findViewById(R.id.mobileNumber);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        annualIncome = findViewById(R.id.annualIncome);
        policyNameEditText = findViewById(R.id.policyName); // EditText for policy name
        totalPolicyAmount = findViewById(R.id.totalPolicyAmount);
        planTerm = findViewById(R.id.planTerm);
        totalPremiumAmount = findViewById(R.id.totalPremiumAmount);
        genderGroup = findViewById(R.id.genderGroup);
        premiumPayingFrequencyGroup = findViewById(R.id.premiumPayingFrequencyGroup);
        submitButton = findViewById(R.id.submitButton);
        clearButton = findViewById(R.id.clearButton);

        // Set the policy name in the EditText
        if (policyName != null) {
            policyNameEditText.setText(policyName);
        }

        // Set listeners to trigger premium calculation
        totalPolicyAmount.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) calculateTotalPremium();
        });

        planTerm.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) calculateTotalPremium();
        });

        premiumPayingFrequencyGroup.setOnCheckedChangeListener((group, checkedId) -> calculateTotalPremium());

        // Submit button logic
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomerDetails();
            }
        });

        // Clear button logic
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });
    }

    private void saveCustomerDetails() {
        String name = fullName.getText().toString().trim();
        String mobile = mobileNumber.getText().toString().trim();
        String emailValue = email.getText().toString().trim();
        String ageValue = age.getText().toString().trim();
        String addressValue = address.getText().toString().trim();
        String income = annualIncome.getText().toString().trim();
        String policy = policyNameEditText.getText().toString().trim();
        String totalAmount = totalPolicyAmount.getText().toString().trim();
        String term = planTerm.getText().toString().trim();
        String premiumAmount = totalPremiumAmount.getText().toString().trim();

        // Get the selected gender and frequency radio buttons
        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        int selectedFrequencyId = premiumPayingFrequencyGroup.getCheckedRadioButtonId();

        // Initialize selectedGender and selectedFrequency
        selectedGender = findViewById(selectedGenderId);
        selectedFrequency = findViewById(selectedFrequencyId);

        // Validate the input fields
        if (validateInputs()) {
            // Check if both gender and frequency have been selected
            if (selectedGender != null && selectedFrequency != null) {
                String id = databaseReference.push().getKey();
                Customer customer = new Customer(id, name, mobile, emailValue, ageValue, selectedGender.getText().toString(), addressValue, income, policy, totalAmount, term, premiumAmount, selectedFrequency.getText().toString());

                databaseReference.child(id).setValue(customer).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Customer Details Saved", Toast.LENGTH_LONG).show();
                        clearForm();

                        Intent intent = new Intent(Customer_Details.this, Customer_Bank_Details.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Failed to save customer details.", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(this, "Please select gender and frequency", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean validateInputs() {
        String emailValue = email.getText().toString().trim();
        String phone = mobileNumber.getText().toString().trim();

        if (TextUtils.isEmpty(fullName.getText().toString())) {
            fullName.setError("Full name is required!");
            fullName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(phone) || !Patterns.PHONE.matcher(phone).matches()) {
            mobileNumber.setError("Enter a valid phone number!");
            mobileNumber.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(emailValue) || !Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            email.setError("Enter a valid email!");
            email.requestFocus();
            return false;
        }

        return true;
    }

    private void clearForm() {
        fullName.setText("");
        mobileNumber.setText("");
        email.setText("");
        age.setText("");
        address.setText("");
        annualIncome.setText("");
        totalPolicyAmount.setText("");
        planTerm.setText("");
        totalPremiumAmount.setText("");
        genderGroup.clearCheck();
        premiumPayingFrequencyGroup.clearCheck();
    }

    // Method to calculate total premium
    private void calculateTotalPremium() {
        try {
            int totalPolicyAmountValue = Integer.parseInt(totalPolicyAmount.getText().toString().trim());
            int planTermValue = Integer.parseInt(planTerm.getText().toString().trim());
            int selectedFrequencyId = premiumPayingFrequencyGroup.getCheckedRadioButtonId();
            selectedFrequency = findViewById(selectedFrequencyId);
            int frequencyValue = 1;

            switch (selectedFrequency.getText().toString()) {
                case "Yearly":
                    frequencyValue = 1;
                    break;
                case "Half-Yearly":
                    frequencyValue = 2;
                    break;
                case "Quarterly":
                    frequencyValue = 4;
                    break;
                case "Monthly":
                    frequencyValue = 12;
                    break;
            }

            float totalPremiumAmountValue = (float) totalPolicyAmountValue / (planTermValue * frequencyValue);
            totalPremiumAmount.setText(String.format("%.2f", totalPremiumAmountValue));

        } catch (NumberFormatException e) {
            totalPremiumAmount.setText("");
        }
    }
}
