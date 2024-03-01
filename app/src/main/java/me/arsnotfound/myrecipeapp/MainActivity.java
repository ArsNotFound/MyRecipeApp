package me.arsnotfound.myrecipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import me.arsnotfound.myrecipeapp.databinding.ActivityMainBinding;
import me.arsnotfound.myrecipeapp.ui.RecipeListFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(binding.fragmentContainerView.getId(), RecipeListFragment.class, null)
                    .commit();
        }

        setContentView(binding.getRoot());
    }
}