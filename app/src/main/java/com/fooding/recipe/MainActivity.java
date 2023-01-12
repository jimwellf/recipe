package com.fooding.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fooding.recipe.Controller.LocalStorageController;
import com.fooding.recipe.Fragments.HomepageFragment;
import com.fooding.recipe.Fragments.LoggedinFragment;
import com.fooding.recipe.Fragments.PantryFragment;
import com.fooding.recipe.Fragments.ProfileFragment;
import com.fooding.recipe.Fragments.RecipesFragment;
import com.fooding.recipe.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    protected ActivityMainBinding binding;
    private boolean isLogged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(HomepageFragment.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        checkOnline();
        configBottomNav();
    }

    private void checkOnline () {
        LocalStorageController localStorageController = new LocalStorageController(this);
        try {
            isLogged = Boolean.parseBoolean(localStorageController.read("isLogged"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(isLogged == true) {
            Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show();
        }
    }
    private void configBottomNav() {
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.homepage:
                    showFragment(HomepageFragment.class);
                    break;
                case R.id.pantry:
                    showFragment(PantryFragment.class);
                    break;
                case R.id.recipes:
                    showFragment(RecipesFragment.class);
                    break;
                case R.id.profile:
                    if(isLogged) {
                        showFragment(LoggedinFragment.class);
                    } else {
                        showFragment(ProfileFragment.class);
                    }
                    break;
            }
            return true;
        });
    }
    private void showFragment(Class fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_layout, fragmentClass, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }
}
