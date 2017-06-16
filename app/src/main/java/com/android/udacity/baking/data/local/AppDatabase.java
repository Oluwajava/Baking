package com.android.udacity.baking.data.local;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.android.udacity.baking.model.Favourite;

/**
 * Created by Mayokun on 6/13/2017.
 */
@Database(entities =  {Favourite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   public abstract FavouriteDao favouriteDao();
}
