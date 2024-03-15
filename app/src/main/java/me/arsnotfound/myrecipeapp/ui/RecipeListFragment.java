package me.arsnotfound.myrecipeapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import me.arsnotfound.myrecipeapp.R;
import me.arsnotfound.myrecipeapp.database.RecipeDatabase;
import me.arsnotfound.myrecipeapp.databinding.FragmentRecipeListBinding;
import me.arsnotfound.myrecipeapp.repo.RecipeRepository;

public class RecipeListFragment extends Fragment {
    private FragmentRecipeListBinding binding;

    private RecipeRepository repo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false);
        RecipeDatabase db = new RecipeDatabase(this.getContext());
        repo = new RecipeRepository(db);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecipeAdapter adapter = new RecipeAdapter();
        adapter.submitList(repo.getAllRecipes());
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
