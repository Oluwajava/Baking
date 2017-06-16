package com.android.udacity.baking.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.udacity.baking.model.Favourite;

/**
 * Created by Mayokun on 6/13/2017.
 */
@Dao
public interface FavouriteDao {
    @Query("SELECT * FROM favourite WHERE recipeId = :recipeId")
    Favourite getFavourite(int recipeId);

    @Insert
    void insertRecipe(Favourite favourite);

    @Delete
    void deleteRecipe(Favourite favourite);

}
