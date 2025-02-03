package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText nameInput, emailInput, branchNameInput, agencyCodeInput, passwordInput, repasswordInput;
    private RadioGroup userTypeGroup;
    private RadioButton customerRadio, agentRadio;
    private LinearLayout agentFields;
    private Button signUpButton;

    private FirebaseAuth mAuth; // Firebase Authentication
    private DatabaseReference databaseReference; // Firebase Database Reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        branchNameInput = findViewById(R.id.branchNameInput);
        agencyCodeInput = findViewById(R.id.agencyCodeInput);
        passwordInput = findViewById(R.id.passwordInput);
        repasswordInput = findViewById(R.id.repasswordInput);
        userTypeGroup = findViewById(R.id.genderGroup);
        customerRadio = findViewById(R.id.customer);
        agentRadio = findViewById(R.id.agent);
        agentFields = findViewById(R.id.agentFields);
        signUpButton = findViewById(R.id.signUpButton);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        // Firebase reference for additional user data
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Default: set user type as "Customer" and hide agent fields
        customerRadio.setChecked(true);
        agentFields.setVisibility(View.GONE);
        branchNameInput.setEnabled(false);
        agencyCodeInput.setEnabled(false);

        // Show/Hide agent fields based on user type selection
        userTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.agent) {
                agentFields.setVisibility(View.VISIBLE);
                branchNameInput.setEnabled(true);
                agencyCodeInput.setEnabled(true);
            } else {
                agentFields.setVisibility(View.GONE);
                branchNameInput.setEnabled(false);
                agencyCodeInput.setEnabled(false);
            }
        });

        // Sign Up button click
        signUpButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String repassword = repasswordInput.getText().toString().trim();
            String userType = customerRadio.isChecked() ? "Customer" : "Agent";
            String branchName = branchNameInput.getText().toString().trim();
            String agencyCode = agencyCodeInput.getText().toString().trim();

            // Validate inputs
            if (TextUtils.isEmpty(name)) {
                nameInput.setError("Name is required");
                return;
            }
            if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Valid email is required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                passwordInput.setError("Password is required");
                return;
            }
            if (!password.equals(repassword)) {
                repasswordInput.setError("Passwords do not match");
                return;
            }

            if (agentRadio.isChecked()) {
                // Agent must fill branch and agency codes
                if (TextUtils.isEmpty(branchName)) {
                    branchNameInput.setError("Branch name is required for agents");
                    return;
                }
                if (TextUtils.isEmpty(agencyCode)) {
                    agencyCodeInput.setError("Agency code is required for agents");
                    return;
                }
            }

            // Create user using Firebase Authentication
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Get current user
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user != null) {
                                // Store additional user data in the Realtime Database
                                String userId = user.getUid();
                                Map<String, String> userData = new HashMap<>();
                                userData.put("name", name);
                                userData.put("email", email);
                                userData.put("userType", userType);
                                if (userType.equals("Agent")) {
                                    userData.put("branchName", branchName);
                                    userData.put("agencyCode", agencyCode);
                                }

                                // Save to database
                                databaseReference.child(userId).setValue(userData).addOnCompleteListener(dbTask -> {
                                    if (dbTask.isSuccessful()) {
                                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        // Navigate to another activity
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Register.this, "Failed to register. Try again.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(Register.this, "Failed to register. Try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
