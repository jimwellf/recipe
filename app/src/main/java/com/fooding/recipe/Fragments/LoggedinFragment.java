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

import com.fooding.recipe.MainActivity;
import com.fooding.recipe.R;

public class LoggedinFragment extends Fragment {

    private Button signoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loggedin_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        signoutButton = view.findViewById(R.id.signoutbutton_id);
        signoutButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(this.getContext(), MainActivity.class);
            intent.putExtra("isLogged", "false");
            startActivity(intent);
        });
    }
}
