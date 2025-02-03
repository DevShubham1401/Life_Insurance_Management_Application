package com.example.myproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Customer_Bank_Details extends AppCompatActivity {

    private static final int PICK_BANK_DOCUMENT = 101;
    private static final int PICK_AADHAR_CARD = 102;
    private static final int PICK_PAN_CARD = 103;

    private Uri bankDocumentUri, aadharCardUri, panCardUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private Button btnUploadBankDocument, btnUploadAadharCard, btnUploadPanCard, btnSubmit;
    private EditText etBankName, etAccountNumber, etIfscCode, etAadharNumber, etPanNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bank_details);

        // Initialize Firebase storage and database references
        storageReference = FirebaseStorage.getInstance().getReference("Documents");
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer_Bank_Details");

        // Initialize UI components
        btnUploadBankDocument = findViewById(R.id.btn_upload_bank_document);
        btnUploadAadharCard = findViewById(R.id.btn_upload_aadhar_card);
        btnUploadPanCard = findViewById(R.id.btn_upload_pan_card);
        btnSubmit = findViewById(R.id.btn_submit);
        etBankName = findViewById(R.id.et_bank_name);
        etAccountNumber = findViewById(R.id.et_account_number);
        etIfscCode = findViewById(R.id.et_ifsc_code);
        etAadharNumber = findViewById(R.id.et_aadhar_number);
        etPanNumber = findViewById(R.id.et_pan_number);

        // Bank Document upload
        btnUploadBankDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile(PICK_BANK_DOCUMENT);
            }
        });

        // Aadhar Card upload
        btnUploadAadharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile(PICK_AADHAR_CARD);
            }
        });

        // PAN Card upload
        btnUploadPanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile(PICK_PAN_CARD);
            }
        });

        // Submit button click event
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFiles();
            }
        });
    }

    private void selectFile(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Set to select any type of file
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri fileUri = data.getData();

            switch (requestCode) {
                case PICK_BANK_DOCUMENT:
                    bankDocumentUri = fileUri;
                    Toast.makeText(this, "Bank Document selected.", Toast.LENGTH_SHORT).show();
                    break;

                case PICK_AADHAR_CARD:
                    aadharCardUri = fileUri;
                    Toast.makeText(this, "Aadhar Card selected.", Toast.LENGTH_SHORT).show();
                    break;

                case PICK_PAN_CARD:
                    panCardUri = fileUri;
                    Toast.makeText(this, "PAN Card selected.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void uploadFiles() {
        if (bankDocumentUri != null) {
            uploadFileToFirebase(bankDocumentUri, "bank_document");
        }

        if (aadharCardUri != null) {
            uploadFileToFirebase(aadharCardUri, "aadhar_card");
        }

        if (panCardUri != null) {
            uploadFileToFirebase(panCardUri, "pan_card");
        }

        // Collecting and submitting other customer details to Firebase Database
        String bankName = etBankName.getText().toString();
        String accountNumber = etAccountNumber.getText().toString();
        String ifscCode = etIfscCode.getText().toString();
        String aadharNumber = etAadharNumber.getText().toString();
        String panNumber = etPanNumber.getText().toString();

        CustomerBankDetails customerBankDetails = new CustomerBankDetails(bankName, accountNumber, ifscCode, aadharNumber, panNumber);
        databaseReference.push().setValue(customerBankDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Customer_Bank_Details.this, "Customer details submitted successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ThankYou.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void uploadFileToFirebase(Uri fileUri, final String fileType) {
        final StorageReference fileReference = storageReference.child(fileType + "_" + System.currentTimeMillis());
        fileReference.putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String downloadUrl = uri.toString();
                                Log.d("FirebaseStorage", fileType + " uploaded: " + downloadUrl);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Customer_Bank_Details.this, "Failed to upload " + fileType, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void clearFields() {
        etBankName.setText("");
        etAccountNumber.setText("");
        etIfscCode.setText("");
        etAadharNumber.setText("");
        etPanNumber.setText("");
    }


    // Model class for CustomerBankDetails
    public class CustomerBankDetails {
        public String bankName;
        public String accountNumber;
        public String ifscCode;
        public String aadharNumber;
        public String panNumber;

        public CustomerBankDetails(String bankName, String accountNumber, String ifscCode, String aadharNumber, String panNumber) {
            this.bankName = bankName;
            this.accountNumber = accountNumber;
            this.ifscCode = ifscCode;
            this.aadharNumber = aadharNumber;
            this.panNumber = panNumber;
        }
    }
}
