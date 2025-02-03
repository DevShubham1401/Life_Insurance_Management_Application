package com.example.myproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetailsActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView nameTextView, emailTextView, addressTextView, mobileNumber; // Add more TextViews if needed
    private ImageView docImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Initialize UI components
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
//        addressTextView = findViewById(R.id.addressTextView); // Optional
        mobileNumber = findViewById(R.id.mobileNumber); // Optional
//        docImageView = findViewById(R.id.docImageView);

        // Get user ID from Intent
        String userId = getIntent().getStringExtra("userId");

        if (userId != null) {
            // Initialize Firebase Database Reference
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userId);

            // Fetch user details from Firebase
            fetchUserDetails(userId);
        }
    }

    private void fetchUserDetails(String userId) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Assuming user has fields: name, email, address, and phone
                    String name = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);
                    String mobile = snapshot.child("mobile").getValue(String.class);

                    // Display user details in TextViews
                    nameTextView.setText(name);
                    emailTextView.setText(email);
                    if (address != null) {
                        addressTextView.setText(address);
                    }
                    if (mobile != null) {
                        mobileNumber.setText(mobile);
                    }
                } else {
                    Toast.makeText(UserDetailsActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserDetailsActivity.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
//package com.example.myproject;
//
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class UserDetailsActivity extends AppCompatActivity {
//
//    private DatabaseReference databaseReference;
//    private TextView nameTextView, emailTextView, addressTextView, mobileNumber, ageTextView, frequencyTextView,
//            genderTextView, annualIncomeTextView, policyTextView, sumAssuredTextView, termTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_details);
//
//        // Initialize UI components
//        nameTextView = findViewById(R.id.name);
//        emailTextView = findViewById(R.id.email);
//        addressTextView = findViewById(R.id.address);
//        mobileNumber = findViewById(R.id.mobileNumber);
//        ageTextView = findViewById(R.id.age);
//        frequencyTextView = findViewById(R.id.frequency);
//        genderTextView = findViewById(R.id.gender);
//        annualIncomeTextView = findViewById(R.id.annualIncome);
//        policyTextView = findViewById(R.id.policy);
//        sumAssuredTextView = findViewById(R.id.sumassured);
//        termTextView = findViewById(R.id.term);
//
//        // Get user ID from Intent
//        String userId = getIntent().getStringExtra("userId");
//
//        if (userId != null) {
//            // Initialize Firebase Database Reference
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userId);
//
//            // Fetch user details from Firebase
//            fetchUserDetails(userId);
//        }
//    }
//
//    private void fetchUserDetails(String userId) {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // Assuming user has the following fields in Firebase: name, email, address, etc.
//                    String name = snapshot.child("name").getValue(String.class);
//                    String email = snapshot.child("email").getValue(String.class);
//                    String address = snapshot.child("address").getValue(String.class);
//                    String mobile = snapshot.child("mobile").getValue(String.class);
//                    String age = snapshot.child("age").getValue(String.class);
//                    String frequency = snapshot.child("frequency").getValue(String.class);
//                    String gender = snapshot.child("gender").getValue(String.class);
//                    String annualIncome = snapshot.child("annualIncome").getValue(String.class);
//                    String policy = snapshot.child("policy").getValue(String.class);
//                    String sumAssured = snapshot.child("sumAssured").getValue(String.class);
//                    String term = snapshot.child("term").getValue(String.class);
//
//                    // Display user details in TextViews
//                    if (name != null) nameTextView.setText(name);
//                    if (email != null) emailTextView.setText(email);
//                    if (address != null) addressTextView.setText(address);
//                    if (mobile != null) mobileNumber.setText(mobile);
//                    if (age != null) ageTextView.setText(age);
//                    if (frequency != null) frequencyTextView.setText(frequency);
//                    if (gender != null) genderTextView.setText(gender);
//                    if (annualIncome != null) annualIncomeTextView.setText(annualIncome);
//                    if (policy != null) policyTextView.setText(policy);
//                    if (sumAssured != null) sumAssuredTextView.setText(sumAssured);
//                    if (term != null) termTextView.setText(term);
//                } else {
//                    Toast.makeText(UserDetailsActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UserDetailsActivity.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}

//package com.example.myproject;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class UserDetailsActivity extends AppCompatActivity {
//
//    private DatabaseReference databaseReference;
//    private TextView nameTextView, emailTextView, addressTextView, mobileNumber, ageTextView, frequencyTextView,
//            genderTextView, annualIncomeTextView, policyTextView, sumAssuredTextView, termTextView;
//    private Button approveButton, disapproveButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_details);
//
//        // Initialize UI components
//        nameTextView = findViewById(R.id.name);
//        emailTextView = findViewById(R.id.email);
//        addressTextView = findViewById(R.id.address);
//        mobileNumber = findViewById(R.id.mobileNumber);
//        ageTextView = findViewById(R.id.age);
//        frequencyTextView = findViewById(R.id.frequency);
//        genderTextView = findViewById(R.id.gender);
//        annualIncomeTextView = findViewById(R.id.annualIncome);
//        policyTextView = findViewById(R.id.policy);
//        sumAssuredTextView = findViewById(R.id.sumassured);
//        termTextView = findViewById(R.id.term);
//
//        // Initialize buttons
//        approveButton = findViewById(R.id.approveButton);
//        disapproveButton = findViewById(R.id.disapproveButton);
//
//        // Get user ID from Intent
//        String userId = getIntent().getStringExtra("userId");
//
//        if (userId != null) {
//            // Initialize Firebase Database Reference
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userId);
//
//            // Fetch user details from Firebase
//            fetchUserDetails(userId);
//        }
//
//        // Set onClickListeners for the buttons
//        approveButton.setOnClickListener(v -> updateApprovalStatus(userId, "approved"));
//        disapproveButton.setOnClickListener(v -> updateApprovalStatus(userId, "disapproved"));
//    }
//
//    private void fetchUserDetails(String userId) {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // Assuming user has the following fields in Firebase: name, email, address, etc.
//                    String name = snapshot.child("name").getValue(String.class);
//                    String email = snapshot.child("email").getValue(String.class);
//                    String address = snapshot.child("address").getValue(String.class);
//                    String mobile = snapshot.child("mobile").getValue(String.class);
//                    String age = snapshot.child("age").getValue(String.class);
//                    String frequency = snapshot.child("frequency").getValue(String.class);
//                    String gender = snapshot.child("gender").getValue(String.class);
//                    String annualIncome = snapshot.child("annualIncome").getValue(String.class);
//                    String policy = snapshot.child("policy").getValue(String.class);
//                    String sumAssured = snapshot.child("sumAssured").getValue(String.class);
//                    String term = snapshot.child("term").getValue(String.class);
//
//                    // Display user details in TextViews
//                    if (name != null) nameTextView.setText(name);
//                    if (email != null) emailTextView.setText(email);
//                    if (address != null) addressTextView.setText(address);
//                    if (mobile != null) mobileNumber.setText(mobile);
//                    if (age != null) ageTextView.setText(age);
//                    if (frequency != null) frequencyTextView.setText(frequency);
//                    if (gender != null) genderTextView.setText(gender);
//                    if (annualIncome != null) annualIncomeTextView.setText(annualIncome);
//                    if (policy != null) policyTextView.setText(policy);
//                    if (sumAssured != null) sumAssuredTextView.setText(sumAssured);
//                    if (term != null) termTextView.setText(term);
//                } else {
//                    Toast.makeText(UserDetailsActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UserDetailsActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void updateApprovalStatus(String userId, String status) {
//        if (userId != null) {
//            // Set approval status in the database
//            databaseReference.child("approvalStatus").setValue(status)
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(UserDetailsActivity.this, "Status updated to " + status, Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(UserDetailsActivity.this, "Failed to update status", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//    }
//}
//

//package com.example.myproject;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.squareup.picasso.Picasso;
//
//public class UserDetailsActivity extends AppCompatActivity {
//
//    private DatabaseReference databaseReference;
//    private TextView nameTextView, emailTextView, addressTextView, mobileNumber, ageTextView, frequencyTextView,
//            genderTextView, annualIncomeTextView, policyTextView, sumAssuredTextView, termTextView;
//    private Button approveButton, disapproveButton;
//    private LinearLayout imagesContainer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_details);
//
//        // Initialize UI components
//        nameTextView = findViewById(R.id.name);
//        emailTextView = findViewById(R.id.email);
//        addressTextView = findViewById(R.id.address);
//        mobileNumber = findViewById(R.id.mobileNumber);
//        ageTextView = findViewById(R.id.age);
//        frequencyTextView = findViewById(R.id.frequency);
//        genderTextView = findViewById(R.id.gender);
//        annualIncomeTextView = findViewById(R.id.annualIncome);
//        policyTextView = findViewById(R.id.policy);
//        sumAssuredTextView = findViewById(R.id.sumassured);
//        termTextView = findViewById(R.id.term);
//        imagesContainer = findViewById(R.id.imagesContainer); // For displaying images
//
//        // Initialize buttons
//        approveButton = findViewById(R.id.approveButton);
//        disapproveButton = findViewById(R.id.disapproveButton);
//
//        // Get user ID from Intent
//        String userId = getIntent().getStringExtra("userId");
//
//        if (userId != null) {
//            // Initialize Firebase Database Reference
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userId);
//
//            // Fetch user details from Firebase
//            fetchUserDetails(userId);
//
//            // Fetch user images from Firebase
//            fetchUserImages(userId);
//        }
//
//        // Set onClickListeners for the buttons
//        approveButton.setOnClickListener(v -> updateApprovalStatus(userId, "approved"));
//        disapproveButton.setOnClickListener(v -> updateApprovalStatus(userId, "disapproved"));
//    }
//
//    private void fetchUserDetails(String userId) {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // Assuming user has the following fields in Firebase: name, email, address, etc.
//                    String name = snapshot.child("name").getValue(String.class);
//                    String email = snapshot.child("email").getValue(String.class);
//                    String address = snapshot.child("address").getValue(String.class);
//                    String mobile = snapshot.child("mobile").getValue(String.class);
//                    String age = snapshot.child("age").getValue(String.class);
//                    String frequency = snapshot.child("frequency").getValue(String.class);
//                    String gender = snapshot.child("gender").getValue(String.class);
//                    String annualIncome = snapshot.child("annualIncome").getValue(String.class);
//                    String policy = snapshot.child("policy").getValue(String.class);
//                    String sumAssured = snapshot.child("sumAssured").getValue(String.class);
//                    String term = snapshot.child("term").getValue(String.class);
//
//                    // Display user details in TextViews
//                    if (name != null) nameTextView.setText(name);
//                    if (email != null) emailTextView.setText(email);
//                    if (address != null) addressTextView.setText(address);
//                    if (mobile != null) mobileNumber.setText(mobile);
//                    if (age != null) ageTextView.setText(age);
//                    if (frequency != null) frequencyTextView.setText(frequency);
//                    if (gender != null) genderTextView.setText(gender);
//                    if (annualIncome != null) annualIncomeTextView.setText(annualIncome);
//                    if (policy != null) policyTextView.setText(policy);
//                    if (sumAssured != null) sumAssuredTextView.setText(sumAssured);
//                    if (term != null) termTextView.setText(term);
//                } else {
//                    Toast.makeText(UserDetailsActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UserDetailsActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void fetchUserImages(String userId) {
//        DatabaseReference imagesRef = databaseReference.child("images"); // Assuming 'images' node stores image names
//
//        imagesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    for (DataSnapshot imageSnapshot : snapshot.getChildren()) {
//                        String imageName = imageSnapshot.getValue(String.class);
//                        if (imageName != null) {
//                            fetchImageFromStorage(userId, imageName);
//                        }
//                    }
//                } else {
//                    Toast.makeText(UserDetailsActivity.this, "No images found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UserDetailsActivity.this, "Failed to fetch images", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void fetchImageFromStorage(String userId, String imageName) {
//        StorageReference storageRef = FirebaseStorage.getInstance()
//                .getReference().child("userImages").child(userId).child(imageName);
//
//        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//            String imageUrl = uri.toString();
//            displayImage(imageUrl);
//        }).addOnFailureListener(e -> {
//            Toast.makeText(UserDetailsActivity.this, "Failed to fetch image: " + imageName, Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    private void displayImage(String imageUrl) {
//        ImageView imageView = new ImageView(this);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        imageView.setPadding(8, 8, 8, 8);
//
//        Picasso.get()
//                .load(imageUrl)
//                .placeholder(R.drawable.placeholder) // Add a placeholder image in your `drawable` folder
//                .error(R.drawable.error_image)       // Add an error image in your `drawable` folder
//                .into(imageView);
//
//        imagesContainer.addView(imageView);
//    }
//
//    private void updateApprovalStatus(String userId, String status) {
//        if (userId != null) {
//            // Set approval status in the database
//            databaseReference.child("approvalStatus").setValue(status)
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(UserDetailsActivity.this, "Status updated to " + status, Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(UserDetailsActivity.this, "Failed to update status", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//    }
//
//}
