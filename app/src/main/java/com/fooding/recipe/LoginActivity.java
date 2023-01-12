package com.fooding.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.fooding.recipe.Controller.LocalStorageController;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_id);
        passwordEditText = findViewById(R.id.password_id);

        loginButton = findViewById(R.id.loginbutton_id);
        loginButton.setOnClickListener(view -> {
            LocalStorageController localStorageController = new LocalStorageController(this);
            try {
                String txt = localStorageController.read(  usernameEditText.getText() + ".txt");

                if(!passwordEditText.getText().toString().isEmpty() && passwordEditText.getText().toString().equals(txt)) {
                    localStorageController.write("isLogged", "true");
                    goToHomepage();
                } else {
                    showNewAccSnackbar(view);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void goToHomepage() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isLogged", "true");
        startActivity(intent);
    }

    private void showNewAccSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, "Create New Account!", 5000);
        snackbar.setAction("CREATE", view1 -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
        snackbar.show();
    }
}