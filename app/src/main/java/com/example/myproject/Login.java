package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextInputEditText etEmail, etPassword, etPhone;
    Button btnLogin;
    ProgressBar progressBar;
    TextView textView;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Policy_Details.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
//        etPhone = findViewById(R.id.phone);  // Add phone field
        btnLogin = findViewById(R.id.btnlogin);
        progressBar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.RegisterNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });




        Button disp = findViewById(R.id.disp);
        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UsersInfo.class);
                startActivity(intent);
            }
        });





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = String.valueOf(etEmail.getText());
                String password = String.valueOf(etPassword.getText());
                String phone = String.valueOf(etPhone.getText());

//                // Validate phone number
//                if (TextUtils.isEmpty(phone) || !Patterns.PHONE.matcher(phone).matches()) {
//                    etPhone.setError("Enter a valid phone number!");
//                    etPhone.requestFocus();
//                    progressBar.setVisibility(View.GONE);
//                    return;
//                }

                // Validate email
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Enter a valid email!");
                    etEmail.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                // Validate password
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Password is Empty", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Policy_Details.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
//package com.example.myproject;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Patterns;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//public class Login extends AppCompatActivity {
//    FirebaseAuth mAuth;
//    FirebaseFirestore db;
//    TextInputEditText etEmail, etPassword;
//    Button btnLogin;
//    ProgressBar progressBar;
//    TextView textView;
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            checkUserType(currentUser.getUid());  // Fetch userType and navigate accordingly
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        // Initialize FirebaseAuth and Firestore
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize UI components
//        etEmail = findViewById(R.id.email);
//        etPassword = findViewById(R.id.password);
//        btnLogin = findViewById(R.id.btnlogin);
//        progressBar = findViewById(R.id.progressbar);
//        textView = findViewById(R.id.RegisterNow);
//
//        // Navigate to Register page
//        textView.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), Register.class);
//            startActivity(intent);
//            finish();
//        });
//
//        // Login button click handler
//        btnLogin.setOnClickListener(v -> {
//            progressBar.setVisibility(View.VISIBLE);
//            String email = String.valueOf(etEmail.getText());
//            String password = String.valueOf(etPassword.getText());
//
//            // Validate email
//            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                etEmail.setError("Enter a valid email!");
//                etEmail.requestFocus();
//                progressBar.setVisibility(View.GONE);
//                return;
//            }
//
//            // Validate password
//            if (TextUtils.isEmpty(password)) {
//                Toast.makeText(Login.this, "Password is Empty", Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.GONE);
//                return;
//            }
//
//            // Attempt to sign in the user
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(task -> {
//                        progressBar.setVisibility(View.GONE);
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            if (user != null) {
//                                checkUserType(user.getUid());  // Fetch userType after login
//                            }
//                        } else {
//                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        });
//    }
//
//    // Check internet connection status
//    private boolean isConnectedToInternet() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
//        return activeNetwork != null && activeNetwork.isConnected();
//    }
//
//    // Check user type from Firestore and navigate accordingly
//    private void checkUserType(String userId) {
//        // If no internet connection, show message
//        if (!isConnectedToInternet()) {
//            Toast.makeText(Login.this, "No internet connection. Please try again later.", Toast.LENGTH_SHORT).show();
//            progressBar.setVisibility(View.GONE);
//            return;
//        }
//
//        // Fetch the user data from Firestore
//        db.collection("users").document(userId)
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        DocumentSnapshot document = task.getResult();
//                        if (document != null && document.exists()) {
//                            // Log the entire document to check its contents
//                            Log.d("Login", "Document data: " + document.getData());
//
//                            String userType = document.getString("userType");
//
//                            if (userType != null) {
//                                // Navigate based on userType
//                                if ("Agent".equals(userType)) {
//                                    // If the user is an Agent, go to UserDetailsActivity page
//                                    Intent intent = new Intent(Login.this, UserDetailsActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else if ("Customer".equals(userType)) {
//                                    // If the user is a Customer, go to PolicyDetails page
//                                    Intent intent = new Intent(Login.this, Policy_Details.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    Toast.makeText(Login.this, "Invalid user type", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(Login.this, "userType field is missing", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(Login.this, "No such document", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        // Handle network errors or failures
//                        Exception exception = task.getException();
//                        if (exception != null && exception.getMessage().contains("NetworkError")) {
//                            Toast.makeText(Login.this, "Network Error: Check your internet connection.", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(Login.this, "Failed to fetch user type: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    progressBar.setVisibility(View.GONE);
//                });
//    }
//}
