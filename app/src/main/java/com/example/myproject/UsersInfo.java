//package com.example.myproject;
//
//import android.app.Dialog;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class UsersInfo extends AppCompatActivity {
//
//    private FirebaseAuth auth;
//    private DatabaseReference databaseReference;
//    private Dialog dialog;
//    private String id;
//
//    // UI Components
//    private TextView nameTextView,emailTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Set the content view directly from the XML file
//        setContentView(R.layout.activity_users_info);
//
//        // Enable Edge-to-Edge layout (if applicable)
//        EdgeToEdge.enable(this);
//
//        // Initialize UI components
//        nameTextView = findViewById(R.id.nameTextView);
//        emailTextView = findViewById(R.id.emailTextView);
//
//        // Initialize Firebase Authentication
//        auth = FirebaseAuth.getInstance();
//
//        // Get the currently authenticated user ID
//        if (auth.getCurrentUser() != null) {
//            id = auth.getCurrentUser().getUid();
//        } else {
//            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Initialize database reference
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
//
//        // Initialize other variables if needed
//        dialog = new Dialog(this);
//
//        // Fetch user data if ID is not empty
//        if (!id.isEmpty()) {
//            getUserData();
//        } else {
//            Toast.makeText(this, "User ID is empty", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void getUserData() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // Assuming you have a User class to map the data
//                    Customer user = snapshot.getValue(Customer.class);
//                    if (user != null) {
//                        // Populate UI with user data
//                        nameTextView.setText(user.getName());
//                        emailTextView.setText(user.getEmail());
//                    }
//                } else {
//                    Toast.makeText(UsersInfo.this, "User data not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UsersInfo.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}



//package com.example.myproject;
//
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
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
//import java.util.ArrayList;
//
//public class UsersInfo extends AppCompatActivity {
//
//    private DatabaseReference databaseReference;
//    private ListView usersListView;
//    private ArrayList<String> userList;
//    private ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_users_info);
//
//        // Initialize ListView
//        usersListView = findViewById(R.id.usersListView);
//
//        // Initialize list and adapter
//        userList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
//        usersListView.setAdapter(adapter);
//
//        // Initialize Firebase Database Reference
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers");
//
//        // Fetch data from Firebase
//        fetchUsersData();
//    }
//
//    private void fetchUsersData() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                userList.clear(); // Clear previous data
//                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
//                    // Assuming each user has a "name" and "email" field
//                    String name = userSnapshot.child("name").getValue(String.class);
//                    String email = userSnapshot.child("email").getValue(String.class);
//
//                    if (name != null && email != null) {
//                        userList.add(name + " - " + email);
//                    }
//                }
//                adapter.notifyDataSetChanged(); // Refresh ListView
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UsersInfo.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}


package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersInfo extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ListView usersListView;
    private ArrayList<String> userList;
    private ArrayList<String> userIdList; // Store user IDs for each item
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info);

        // Initialize ListView
        usersListView = findViewById(R.id.usersListView);

        // Initialize list and adapter
        userList = new ArrayList<>();
        userIdList = new ArrayList<>(); // Initialize user ID list
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        usersListView.setAdapter(adapter);

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers");

        // Fetch data from Firebase
        fetchUsersData();

        // Set OnItemClickListener for ListView
        usersListView.setOnItemClickListener((parent, view, position, id) -> {
            // Get the user ID and other information from the clicked item
            String userId = userIdList.get(position);

            // Intent to navigate to the details activity
            Intent intent = new Intent(UsersInfo.this, UserDetailsActivity.class);
            intent.putExtra("userId", userId); // Pass the user ID
            startActivity(intent);
        });
    }

    private void fetchUsersData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear(); // Clear previous data
                userIdList.clear(); // Clear previous user IDs

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String name = userSnapshot.child("name").getValue(String.class);
                    String email = userSnapshot.child("email").getValue(String.class);
                    String userId = userSnapshot.getKey(); // Get the unique user ID

                    if (name != null && email != null) {
                        // Add data to lists
                        userList.add(name + " - " + email);
                        userIdList.add(userId); // Store the user ID
                    }
                }
                adapter.notifyDataSetChanged(); // Refresh ListView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UsersInfo.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
