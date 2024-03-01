package me.arsnotfound.myrecipeapp.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

import me.arsnotfound.myrecipeapp.data.Recipe;
import me.arsnotfound.myrecipeapp.databinding.ListItemRecipeBinding;

public class RecipeAdapter extends ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder> {
    private OnItemClickListener onItemClickListener = null;

    public RecipeAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemRecipeBinding binding = ListItemRecipeBinding.inflate(layoutInflater, parent, false);
        return new RecipeViewHolder(binding, this::onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = getCurrentList().get(position);
        holder.bind(recipe);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    private void onItemClick(int position) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(position, getItem(position));
        }
    }

    public static final DiffUtil.ItemCallback<Recipe> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
            return oldItem.equals(newItem);
        }
    };

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final ListItemRecipeBinding binding;

        public RecipeViewHolder(@NonNull ListItemRecipeBinding binding, Consumer<Integer> onItemClick) {
            super(binding.getRoot());

            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> onItemClick.accept(getBindingAdapterPosition()));
        }

        private void bind(Recipe recipe) {
            binding.recipeName.setText(recipe.getName());
            binding.recipeDifficulty.setText(recipe.getDifficulty().getStringID());
            binding.recipeTimeToCook.setText(String.valueOf(recipe.getTimeToCook()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Recipe item);
    }
}
