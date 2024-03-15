package me.arsnotfound.myrecipeapp.database;

public class StepDB {
    private long recipeId;

    private int order;

    private String name;

    public StepDB(long recipeId, int order, String name) {
        this.recipeId = recipeId;
        this.order = order;
        this.name = name;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
