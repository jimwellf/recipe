package com.fooding.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fooding.recipe.Controller.LocalStorageController;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.username_id);
        passwordEditText = findViewById(R.id.password_id);
        signupButton = findViewById(R.id.signupbutton_id);

        signupButton.setOnClickListener(view -> {
            LocalStorageController localStorageController = new LocalStorageController(this);

            try {
                if (!usernameEditText.getText().toString().isEmpty()) {
                    localStorageController.write(usernameEditText.getText() + ".txt", passwordEditText.getText().toString() );
                    Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}