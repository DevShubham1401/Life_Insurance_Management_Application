////package com.example.myproject;
////
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.TextView;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.ValueEventListener;
////
////import java.util.ArrayList;
////
//////public class DisplayCustomersActivity extends AppCompatActivity {
////
////public class customer_item extends AppCompatActivity {
////
////    private RecyclerView recyclerView;
////    private CustomerAdapter adapter;
////    private ArrayList<Customer> customerList;
////    private DatabaseReference databaseReference;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_display_customers);
////
////        recyclerView = findViewById(R.id.recyclerView);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
//////        customerList = new ArrayList<>();
//////        adapter = new CustomerAdapter(customerList);
//////        recyclerView.setAdapter(adapter);
////
////
////        customerList = new ArrayList<>();
////        adapter = new CustomerAdapter(customerList, customer -> {
////            // When a customer is clicked, navigate to CustomerDetailsActivity
////            Intent intent = new Intent(customer_item.this, Show_Customer_Details.class);
////            intent.putExtra("customer", customer); // Pass the customer object
////            startActivity(intent);
////        });
////        recyclerView.setAdapter(adapter);
////
////        databaseReference = FirebaseDatabase.getInstance().getReference("Customers");
////        fetchCustomers();
////
//////        databaseReference = FirebaseDatabase.getInstance().getReference("Customers");
////
//////        fetchCustomers();
////    }
////
////
////
////
////
////
////
////
////
////    private void fetchCustomers() {
////        databaseReference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                customerList.clear();
////                for (DataSnapshot customerSnapshot : snapshot.getChildren()) {
////                    Customer customer = customerSnapshot.getValue(Customer.class);
////                    customerList.add(customer);
////                }
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                // Handle possible errors here
////            }
////        });
////    }
////
////    private static class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
////
////        private final ArrayList<Customer> customers;
////
////        public CustomerAdapter(ArrayList<Customer> customers) {
////            this.customers = customers;
////        }
////
////        @NonNull
////        @Override
////        public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_customer_item, parent, false);
////            return new CustomerViewHolder(view);
////        }
////
////        @Override
////        public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
////            Customer customer = customers.get(position);
////            holder.nameTextView.setText("Name: " + customer.getName());
////            holder.mobileTextView.setText("Mobile: " + customer.getMobile());
////            holder.emailTextView.setText("Email: " + customer.getEmail());
////            holder.policyTextView.setText("Policy: " + customer.getPolicy());
////        }
////
////        @Override
////        public int getItemCount() {
////            return customers.size();
////        }
////
////        public static class CustomerViewHolder extends RecyclerView.ViewHolder {
////
////            TextView nameTextView, mobileTextView, emailTextView, policyTextView;
////
////            public CustomerViewHolder(@NonNull View itemView) {
////                super(itemView);
////                nameTextView = itemView.findViewById(R.id.nameTextView);
////                mobileTextView = itemView.findViewById(R.id.mobileTextView);
////                emailTextView = itemView.findViewById(R.id.emailTextView);
////                policyTextView = itemView.findViewById(R.id.policyTextView);
////            }
////        }
////    }
////}
//
//
////package com.example.myproject;
////
////import android.annotation.SuppressLint;
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.TextView;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.ValueEventListener;
////
////import java.util.ArrayList;
////
////public class DisplayCustomersActivity extends AppCompatActivity {
////
////    private RecyclerView recyclerView;
////    private CustomerAdapter adapter;
////    private ArrayList<Customer> customerList;
////    private DatabaseReference databaseReference;
////
////    @SuppressLint("MissingInflatedId")
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.display_customer_activity);
////
////        recyclerView = findViewById(R.id.recyclerView);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        customerList = new ArrayList<>();
////        adapter = new CustomerAdapter(customerList, customer -> {
////            // Navigate to Show_Customer_Details activity
////            Intent intent = new Intent(DisplayCustomersActivity.this, Show_Customer_Details.class);
////            intent.putExtra("customer", customer); // Pass the customer object
////            startActivity(intent);
////        });
////        recyclerView.setAdapter(adapter);
////
////        databaseReference = FirebaseDatabase.getInstance().getReference("Customers");
////        fetchCustomers();
////    }
////
////    private void fetchCustomers() {
////        databaseReference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                customerList.clear();
////                for (DataSnapshot customerSnapshot : snapshot.getChildren()) {
////                    Customer customer = customerSnapshot.getValue(Customer.class);
////                    if (customer != null) {
////                        customerList.add(customer);
////                    }
////                }
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                // Handle possible errors here
////            }
////        });
////    }
////
////    private static class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
////
////        private final ArrayList<Customer> customers;
////        private final OnCustomerClickListener listener;
////
////        public interface OnCustomerClickListener {
////            void onCustomerClick(Customer customer);
////        }
////
////        public CustomerAdapter(ArrayList<Customer> customers, OnCustomerClickListener listener) {
////            this.customers = customers;
////            this.listener = listener;
////        }
////
////        @NonNull
////        @Override
////        public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_display_customers, parent, false);
////            return new CustomerViewHolder(view);
////        }
////
////        @Override
////        public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
////            Customer customer = customers.get(position);
////            holder.nameTextView.setText("Name: " + customer.getName());
////            holder.mobileTextView.setText("Mobile: " + customer.getMobile());
////            holder.emailTextView.setText("Email: " + customer.getEmail());
////            holder.policyTextView.setText("Policy: " + customer.getPolicy());
////
////            holder.itemView.setOnClickListener(v -> listener.onCustomerClick(customer));
////        }
////
////        @Override
////        public int getItemCount() {
////            return customers.size();
////        }
////
////        public static class CustomerViewHolder extends RecyclerView.ViewHolder {
////
////            TextView nameTextView, mobileTextView, emailTextView, policyTextView;
////
////            public CustomerViewHolder(@NonNull View itemView) {
////                super(itemView);
////                nameTextView = itemView.findViewById(R.id.nameTextView);
////                mobileTextView = itemView.findViewById(R.id.mobileTextView);
////                emailTextView = itemView.findViewById(R.id.emailTextView);
////                policyTextView = itemView.findViewById(R.id.policyTextView);
////            }
////        }
////    }
////}
//
//
//
//
//package com.example.myproject;
//
//
////import android.annotation.SuppressLint;
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.TextView;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.ValueEventListener;
////
////import java.util.ArrayList;
////
//public class DisplayCustomersActivity extends AppCompatActivity {
////
////    private RecyclerView recyclerView;
////    private CustomerAdapter adapter;
////    private ArrayList<Customer> customerList;
////    private DatabaseReference databaseReference;
////
////    @SuppressLint("MissingInflatedId")
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.display_customer_activity);
////
////        recyclerView = findViewById(R.id.recyclerView);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        customerList = new ArrayList<>();
////        adapter = new CustomerAdapter(customerList, customer -> {
////            // Navigate to Show_Customer_Details activity
////            Intent intent = new Intent(DisplayCustomersActivity.this, Show_Customer_Details.class);
////            intent.putExtra("customer", customer); // Pass the customer object
////            startActivity(intent);
////        });
////        recyclerView.setAdapter(adapter);
////
////        databaseReference = FirebaseDatabase.getInstance().getReference("Customers");
////        fetchCustomers();
////    }
////
////    private void fetchCustomers() {
////        databaseReference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                customerList.clear();
////                for (DataSnapshot customerSnapshot : snapshot.getChildren()) {
////                    Customer customer = customerSnapshot.getValue(Customer.class);
////                    if (customer != null) {
////                        customerList.add(customer);
////                    }
////                }
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                // Handle possible errors here
////            }
////        });
////    }
////
////    private static class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
////
////        private final ArrayList<Customer> customers;
////        private final OnCustomerClickListener listener;
////
////        public interface OnCustomerClickListener {
////            void onCustomerClick(Customer customer);
////        }
////
////        public CustomerAdapter(ArrayList<Customer> customers, OnCustomerClickListener listener) {
////            this.customers = customers;
////            this.listener = listener;
////        }
////
////        @NonNull
////        @Override
////        public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_display_customers, parent, false);
////            return new CustomerViewHolder(view);
////        }
////
////        @Override
////        public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
////            Customer customer = customers.get(position);
////            holder.nameTextView.setText("Name: " + customer.getName());
////            holder.mobileTextView.setText("Mobile: " + customer.getMobile());
////            holder.emailTextView.setText("Email: " + customer.getEmail());
////            holder.policyTextView.setText("Policy: " + customer.getPolicy());
////
////            holder.itemView.setOnClickListener(v -> listener.onCustomerClick(customer));
////        }
////
////        @Override
////        public int getItemCount() {
////            return customers.size();
////        }
////
////        public static class CustomerViewHolder extends RecyclerView.ViewHolder {
////
////            TextView nameTextView, mobileTextView, emailTextView, policyTextView;
////
////            public CustomerViewHolder(@NonNull View itemView) {
////                super(itemView);
////                nameTextView = itemView.findViewById(R.id.nameTextView);
////                mobileTextView = itemView.findViewById(R.id.mobileTextView);
////                emailTextView = itemView.findViewById(R.id.emailTextView);
////                policyTextView = itemView.findViewById(R.id.policyTextView);
////            }
////        }
////    }
////}
//
//
//
//
//
//
