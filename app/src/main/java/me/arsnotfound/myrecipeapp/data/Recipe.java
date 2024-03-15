package me.arsnotfound.myrecipeapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe implements Parcelable {
    private final long id;

    private String name;

    private Difficulty difficulty;

    private int timeToCook;

    private String description;

    private ArrayList<String> steps;

    public Recipe(String name, Difficulty difficulty, int timeToCook, String description, List<String> steps) {
        this(-1, name, difficulty, timeToCook, description, steps);
    }

    public Recipe(long id, String name, Difficulty difficulty, int timeToCook, String description, List<String> steps) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.description = description;
        this.steps = new ArrayList<>(steps);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Collections.unmodifiableList(steps);
    }

    public void setSteps(List<String> steps) {
        this.steps = new ArrayList<>(steps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return id == recipe.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(difficulty.name());
        dest.writeInt(timeToCook);
        dest.writeString(description);
        dest.writeStringList(steps);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            long id = source.readLong();
            String name = source.readString();
            Difficulty difficulty = Difficulty.valueOf(source.readString());
            int timeToCook = source.readInt();
            String description = source.readString();
            ArrayList<String> steps = new ArrayList<>();
            source.readStringList(steps);

            return new Recipe(id, name, difficulty, timeToCook, description, steps);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
