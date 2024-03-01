package me.arsnotfound.myrecipeapp.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.arsnotfound.myrecipeapp.data.Recipe;
import me.arsnotfound.myrecipeapp.databinding.FragmentRecipeInfoBinding;

public class RecipeInfoFragment extends Fragment {
    private FragmentRecipeInfoBinding binding;

    private Recipe recipe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                recipe = getArguments().getParcelable("recipe", Recipe.class);
            else
                recipe = getArguments().getParcelable("recipe");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recipeName.setText(recipe.getName());
        binding.recipeDifficulty.setText(recipe.getDifficulty().getStringID());
        binding.recipeTimeToCook.setText(String.valueOf(recipe.getTimeToCook()));
        binding.recipeDescription.setText(recipe.getDescription());
        binding.recipeSteps.setText(String.join("\n", recipe.getSteps()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @NonNull
    public static RecipeInfoFragment newInstance(Recipe recipe) {
        RecipeInfoFragment fragment = new RecipeInfoFragment();

        Bundle args = new Bundle();
        args.putParcelable("recipe", recipe);
        fragment.setArguments(args);

        return fragment;
    }
}
