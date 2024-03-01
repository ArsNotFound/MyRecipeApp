package me.arsnotfound.myrecipeapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import me.arsnotfound.myrecipeapp.data.Difficulty;
import me.arsnotfound.myrecipeapp.data.Recipe;
import me.arsnotfound.myrecipeapp.databinding.FragmentRecipeListBinding;

public class RecipeListFragment extends Fragment {
    private FragmentRecipeListBinding binding;

    private List<Recipe> recipeList = List.of(
            new Recipe("Test", Difficulty.EASY, 10, "Test descriptiion", new ArrayList<>())
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false);

        RecipeAdapter adapter = new RecipeAdapter();
        adapter.submitList(recipeList);
        binding.recipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
