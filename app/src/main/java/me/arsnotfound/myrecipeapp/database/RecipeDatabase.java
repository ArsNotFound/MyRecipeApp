package me.arsnotfound.myrecipeapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeDatabase {
    private static final String DATABASE_NAME = "recipe.db";
    private static final int DATABASE_VERSION = 1;

    private static final String RECIPE_TABLE = "recipes";
    private static final String STEPS_TABLE = "recipe_steps";

    private static final String COLUMN_RECIPE_ID = "id";
    private static final String COLUMN_RECIPE_NAME = "name";
    private static final String COLUMN_RECIPE_DIFFICULTY = "difficulty";
    private static final String COLUMN_RECIPE_TIME_TO_COOK = "timeToCook";
    private static final String COLUMN_RECIPE_DESCRIPTION = "description";

    private static final int COLUMN_RECIPE_ID_NUM = 0;
    private static final int COLUMN_RECIPE_NAME_NUM = 1;
    private static final int COLUMN_RECIPE_DIFFICULTY_NUM = 2;
    private static final int COLUMN_RECIPE_TIME_TO_COOK_NUM = 3;
    private static final int COLUMN_RECIPE_DESCRIPTION_NUM = 4;

    private static final String COLUMN_STEPS_RECIPE_ID = "recipeId";
    private static final String COLUMN_STEPS_ORDER = "stepOrder";
    private static final String COLUMN_STEPS_NAME = "name";

    private static final int COLUMN_STEPS_RECIPE_ID_NUM = 0;
    private static final int COLUMN_STEPS_ORDER_NUM = 1;
    private static final int COLUMN_STEPS_NAME_NUM = 2;

    private final SQLiteDatabase db;

    public RecipeDatabase(Context context) {
        OpenHelper helper = new OpenHelper(context);
        db = helper.getWritableDatabase();
    }

    /*
    Recipe Methods
     */
    public long insertRecipe(@NonNull RecipeDB recipe) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECIPE_NAME, recipe.getName());
        cv.put(COLUMN_RECIPE_DIFFICULTY, recipe.getDifficulty());
        cv.put(COLUMN_RECIPE_TIME_TO_COOK, recipe.getTimeToCook());
        cv.put(COLUMN_RECIPE_DESCRIPTION, recipe.getDescription());
        return db.insert(RECIPE_TABLE, null, cv);
    }

    public int updateRecipe(@NonNull RecipeDB recipe) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECIPE_NAME, recipe.getName());
        cv.put(COLUMN_RECIPE_DIFFICULTY, recipe.getDifficulty());
        cv.put(COLUMN_RECIPE_TIME_TO_COOK, recipe.getTimeToCook());
        cv.put(COLUMN_RECIPE_DESCRIPTION, recipe.getDescription());
        return db.update(RECIPE_TABLE, cv, COLUMN_RECIPE_ID + " = ?", new String[]{ String.valueOf(recipe.getId()) });
    }

    public void deleteAllRecipes() {
        db.delete(RECIPE_TABLE, null, null);
    }

    public void deleteRecipe(long id) {
        db.delete(RECIPE_TABLE, COLUMN_RECIPE_ID + " = ?", new String[]{ String.valueOf(id) });
    }

    public RecipeDB selectRecipe(long id) {
        Cursor cursor = db.query(RECIPE_TABLE, null, COLUMN_RECIPE_ID + " = ?", new String[]{ String.valueOf(id) }, null, null, null);

        cursor.moveToFirst();
        RecipeDB recipe = getRecipeFromCursor(cursor);
        cursor.close();

        return recipe;
    }

    public List<RecipeDB> selectAllRecipes() {
        Cursor cursor = db.query(RECIPE_TABLE, null, null, null, null, null, null);

        ArrayList<RecipeDB> recipes = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                recipes.add(getRecipeFromCursor(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return recipes;
    }

    @NonNull
    private RecipeDB getRecipeFromCursor(@NonNull Cursor cursor) {
        long dbId = cursor.getLong(COLUMN_RECIPE_ID_NUM);
        String name = cursor.getString(COLUMN_RECIPE_NAME_NUM);
        String difficulty = cursor.getString(COLUMN_RECIPE_DIFFICULTY_NUM);
        int timeToCook = cursor.getInt(COLUMN_RECIPE_TIME_TO_COOK_NUM);
        String description = cursor.getString(COLUMN_RECIPE_DESCRIPTION_NUM);

        return new RecipeDB(dbId, name, difficulty, timeToCook, description);
    }

    /*
    Steps Methods
     */

    public long insertStep(@NonNull StepDB step) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STEPS_RECIPE_ID, step.getRecipeId());
        cv.put(COLUMN_STEPS_ORDER, step.getOrder());
        cv.put(COLUMN_STEPS_NAME, step.getName());
        return db.insert(STEPS_TABLE, null, cv);
    }

    public int updateStep(@NonNull StepDB step) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STEPS_NAME, step.getName());
        return db.update(
                STEPS_TABLE,
                cv,
                COLUMN_STEPS_RECIPE_ID + " = ? AND " + COLUMN_STEPS_ORDER + " = ?",
                new String[]{String.valueOf(step.getRecipeId()), String.valueOf(step.getOrder())}
        );
    }

    public void deleteStep(long recipeId, int order) {
        db.delete(
                STEPS_TABLE,
                COLUMN_STEPS_RECIPE_ID + " = ? AND " + COLUMN_STEPS_ORDER + " = ?",
                new String[]{String.valueOf(recipeId), String.valueOf(order)}
        );
    }

    public void deleteAllSteps() {
        db.delete(STEPS_TABLE, null, null);
    }

    public void deleteAllRecipeSteps(long recipeId) {
        db.delete(
                STEPS_TABLE,
                COLUMN_STEPS_RECIPE_ID + " = ?" ,
                new String[]{String.valueOf(recipeId)}
        );
    }

    public StepDB getStep(long recipeId, int order) {
        Cursor cursor = db.query(
                STEPS_TABLE,
                null,
                COLUMN_RECIPE_ID + " = ? AND " + COLUMN_STEPS_ORDER + " = ?",
                new String[]{String.valueOf(recipeId), String.valueOf(order)},
                null,
                null,
                null
        );

        cursor.moveToFirst();
        StepDB step = getStepFromCursor(cursor);
        cursor.close();

        return step;
    }

    public List<StepDB> getAllRecipeSteps(long recipeId) {
        Cursor cursor = db.query(
                STEPS_TABLE,
                null,
                COLUMN_STEPS_RECIPE_ID + " = ?",
                new String[]{String.valueOf(recipeId)},
                null,
                null,
                COLUMN_STEPS_ORDER
        );

        ArrayList<StepDB> steps = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                steps.add(getStepFromCursor(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return steps;
    }

    @NonNull
    private StepDB getStepFromCursor(@NonNull Cursor cursor) {
        long recipeId = cursor.getLong(COLUMN_STEPS_RECIPE_ID_NUM);
        int order = cursor.getInt(COLUMN_STEPS_ORDER_NUM);
        String name = cursor.getString(COLUMN_STEPS_NAME_NUM);

        return new StepDB(recipeId, order, name);
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        @Override
        public void onCreate(@NonNull SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + RECIPE_TABLE + " (" +
                        COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_RECIPE_NAME + " TEXT NOT NULL," +
                        COLUMN_RECIPE_DIFFICULTY + " TEXT NOT NULL," +
                        COLUMN_RECIPE_TIME_TO_COOK + " INTEGER NOT NULL," +
                        COLUMN_RECIPE_DESCRIPTION + " TEXT NOT NULL" +
                        ")");

            db.execSQL("CREATE TABLE " + STEPS_TABLE + " (" +
                       COLUMN_STEPS_RECIPE_ID + " INTEGER NOT NULL," +
                       COLUMN_STEPS_ORDER + " INTEGER NOT NULL," +
                       COLUMN_STEPS_NAME + " TEXT NOT NULL," +
                       "PRIMARY KEY (" + COLUMN_STEPS_RECIPE_ID + ", " + COLUMN_STEPS_ORDER + ")," +
                       "FOREIGN KEY (" + COLUMN_STEPS_RECIPE_ID  + ") REFERENCES " + RECIPE_TABLE + "(" + COLUMN_RECIPE_ID + ")" +
                       ")");
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            db.execSQL("INSERT INTO recipes (name, difficulty, timeToCook, description) VALUES ('test recipe', 'NORMAL', 10, 'test description')");
        }

        @Override
        public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + STEPS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
            onCreate(db);
        }
    }
}
