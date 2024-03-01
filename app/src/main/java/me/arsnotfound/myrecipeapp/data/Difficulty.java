package me.arsnotfound.myrecipeapp.data;

import me.arsnotfound.myrecipeapp.R;

public enum Difficulty {
    EASY(R.string.difficulty_easy),
    NORMAL(R.string.difficulty_normal),
    HARD(R.string.difficulty_hard);

    private final int stringID;

    Difficulty(int stringID) {
        this.stringID = stringID;
    }

    public int getStringID() {
        return stringID;
    }
}
