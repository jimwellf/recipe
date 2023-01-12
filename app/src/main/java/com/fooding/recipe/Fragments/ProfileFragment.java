package com.fooding.recipe.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fooding.recipe.LoginActivity;
import com.fooding.recipe.R;
import com.fooding.recipe.SignupActivity;

public class ProfileFragment extends Fragment {

    protected Button loginButton;
    protected Button signupButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loginButton = view.findViewById(R.id.loginbutton_id);
        loginButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(this.getContext(), LoginActivity.class);
            startActivity(intent);
        });

        signupButton = view.findViewById(R.id.signupbutton_id);
        signupButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(this.getContext(), SignupActivity.class);
            startActivity(intent);
        });
    }
}
