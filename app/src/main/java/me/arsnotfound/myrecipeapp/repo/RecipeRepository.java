package me.arsnotfound.myrecipeapp.repo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import me.arsnotfound.myrecipeapp.data.Difficulty;
import me.arsnotfound.myrecipeapp.data.Recipe;
import me.arsnotfound.myrecipeapp.database.RecipeDB;
import me.arsnotfound.myrecipeapp.database.RecipeDatabase;
import me.arsnotfound.myrecipeapp.database.StepDB;

public class RecipeRepository {
    private final RecipeDatabase database;

    public RecipeRepository(RecipeDatabase database) {
        this.database = database;
    }

    public List<Recipe> getAllRecipes() {
        List<RecipeDB> dbRecipes = database.selectAllRecipes();
        return dbRecipes.stream().map(RecipeRepository::mapDbToData).collect(Collectors.toList());
    }

    private static Recipe mapDbToData(RecipeDB recipeDB) {
        long id = recipeDB.getId();
        String name = recipeDB.getName();
        Difficulty difficulty = Difficulty.valueOf(recipeDB.getDifficulty());
        int timeToCook = recipeDB.getTimeToCook();
        String description = recipeDB.getDescription();
        List<String> steps = Collections.emptyList();

        return new Recipe(id, name, difficulty, timeToCook, description, steps);
    }

    private static Recipe mapDbToData(RecipeDB recipeDB, List<StepDB> stepDBList) {
        long id = recipeDB.getId();
        String name = recipeDB.getName();
        Difficulty difficulty = Difficulty.valueOf(recipeDB.getDifficulty());
        int timeToCook = recipeDB.getTimeToCook();
        String description = recipeDB.getDescription();
        List<String> steps = stepDBList.stream().map(StepDB::getName).collect(Collectors.toList());

        return new Recipe(id, name, difficulty, timeToCook, description, steps);
    }
}
