package com.fooding.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.widget.Toast;

import com.fooding.recipe.Fragments.HomepageFragment;
import com.fooding.recipe.Fragments.LoggedinFragment;
import com.fooding.recipe.Fragments.PantryFragment;
import com.fooding.recipe.Fragments.ProfileFragment;
import com.fooding.recipe.Fragments.RecipesFragment;
import com.fooding.recipe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    protected ActivityMainBinding binding;
    private boolean isLogged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        isLogged = Boolean.parseBoolean(getIntent().getStringExtra("isLogged"));

        if(isLogged == true) {
            Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show();
        }

        configBottomNav();
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
