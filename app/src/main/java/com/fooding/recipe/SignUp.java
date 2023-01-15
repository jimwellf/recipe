package com.fooding.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://recipe-860f7-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        final EditText fullname = findViewById(R.id.fullname);
        final EditText email = findViewById(R.id.email);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final EditText controlPassword = findViewById(R.id.controlPassword);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginNowBtn = findViewById(R.id.loginNowBtn);

        registerBtn.setOnClickListener(view -> {
            final String fullnameTxt = fullname.getText().toString();
            final String emailTxt = email.getText().toString();
            final String usernameTxt = username.getText().toString();
            final String passwordTxt = password.getText().toString();
            final String controlPasswordTxt = controlPassword.getText().toString();

            if(fullnameTxt.isEmpty() || emailTxt.isEmpty() || usernameTxt.isEmpty() || passwordTxt.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else if(!passwordTxt.equals(controlPasswordTxt)) {
                Toast.makeText(SignUp.this, "Passwords are not matching!", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(usernameTxt)) {
                            Toast.makeText(SignUp.this, "Username is already in use!", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("users").child(usernameTxt).child("fullname").setValue(fullnameTxt);
                            databaseReference.child("users").child(usernameTxt).child("email").setValue(emailTxt);
                            databaseReference.child("users").child(usernameTxt).child("password").setValue(passwordTxt);
                            Toast.makeText(SignUp.this, "Account Registered Successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        loginNowBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUp.this, Login.class));
            finish();
        });


    }
}