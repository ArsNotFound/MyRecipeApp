package me.arsnotfound.myrecipeapp.database;

public class RecipeDB {
    private long id = -1;
    private String name;
    private String difficulty;
    private int timeToCook;
    private String description;

    public RecipeDB(String name, String difficulty, int timeToCook, String description) {
        this.name = name;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.description = description;
    }

    public RecipeDB(long id, String name, String difficulty, int timeToCook, String description) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
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
}
