package me.arsnotfound.myrecipeapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import me.arsnotfound.myrecipeapp.R;
import me.arsnotfound.myrecipeapp.data.Difficulty;
import me.arsnotfound.myrecipeapp.data.Recipe;
import me.arsnotfound.myrecipeapp.databinding.FragmentRecipeListBinding;

public class RecipeListFragment extends Fragment {
    private FragmentRecipeListBinding binding;

    private List<Recipe> recipeList = List.of(
            new Recipe("Test", Difficulty.EASY, 10, "Test description", List.of("Test 1", "Test 2"))
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecipeAdapter adapter = new RecipeAdapter();
        adapter.submitList(recipeList);
        adapter.setOnItemClickListener((position, item) -> {
            RecipeInfoFragment fragment = RecipeInfoFragment.newInstance(item);
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, fragment)
                    .addToBackStack(RecipeInfoFragment.class.getName())
                    .commit();
        });

        binding.recipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
