package com.example.myproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btn;
    TextView textView;
    FirebaseUser user;
    private ImageView imageView;
    private TextView textview;
    private static final int ANIMATION_DURATION_IMAGE = 1500;
    private static final int ANIMATION_DURATION_TEXT = 1500;
    private static final int DELAY_MILLIS = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.user_details);
        user = mAuth.getCurrentUser();

        if (user == null) {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }, DELAY_MILLIS);
        } else {
            textView.setText("Hello " + user.getEmail());
        }

        imageView = findViewById(R.id.imageView1);
        textview = findViewById(R.id.textView1);

        imageView.setAlpha(0f);
        textview.setAlpha(0f);

        imageView.animate().alpha(1f).setDuration(ANIMATION_DURATION_IMAGE).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textview.animate().alpha(1f).setDuration(ANIMATION_DURATION_TEXT);
            }
        });
    }
}
