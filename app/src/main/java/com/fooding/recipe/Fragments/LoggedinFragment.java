package com.fooding.recipe.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fooding.recipe.Controller.LocalStorageController;
import com.fooding.recipe.MainActivity;
import com.fooding.recipe.R;

import java.io.IOException;

public class LoggedinFragment extends Fragment {

    private Button signoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loggedin_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LocalStorageController localStorageController = new LocalStorageController(getActivity());
        signoutButton = view.findViewById(R.id.signoutbutton_id);
        signoutButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(this.getContext(), MainActivity.class);

            try {
                localStorageController.write("isLogged", "false");
            } catch (IOException e) {
                e.printStackTrace();
            }

            intent.putExtra("isLogged", "false");
            Toast.makeText(getContext(), "Logged Out!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
    }
}
