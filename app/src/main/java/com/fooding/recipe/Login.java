package com.fooding.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fooding.recipe.Controller.LocalStorageController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://recipe-860f7-default-rtdb.europe-west1.firebasedatabase.app/");
    LocalStorageController localStorageController = new LocalStorageController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNowBtn = findViewById(R.id.registerNowBtn);

        loginBtn.setOnClickListener(view -> {
            final String usernameTxt = username.getText().toString();
            final String passwordTxt = password.getText().toString();

            if(usernameTxt.isEmpty() || passwordTxt.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(usernameTxt)) {
                            final String getPassword = snapshot.child(usernameTxt).child("password").getValue(String.class);

                            if(getPassword.equals(passwordTxt)) {
                                toast("Successfully Logged In!");
                                loginLocalStorage();
                                goHomepage();
                            } else {toast("Wrong Password!");}
                        } else {toast("Username or password is not correct!");}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        registerNowBtn.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, SignUp.class));
            finish();
        });
    }

    private void toast(String msg) {
        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void goHomepage() {
        startActivity(new Intent(Login.this, MainActivity.class));
    }

    private void loginLocalStorage() {
        try {
            localStorageController.write("isLogged", "true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}