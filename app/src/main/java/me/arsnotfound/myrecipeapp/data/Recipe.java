package me.arsnotfound.myrecipeapp.data;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private final String name;
    private Difficulty difficulty;
    private int timeToCook;
    private String description;
    private final ArrayList<String> steps;

    public Recipe(String name, Difficulty difficulty, int timeToCook, String description, ArrayList<String> steps) {
        this.name = name;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.description = description;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSteps() {
        return steps;
    }
}
