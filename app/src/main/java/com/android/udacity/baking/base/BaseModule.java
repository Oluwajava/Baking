package com.android.udacity.baking.base;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.android.udacity.baking.data.local.AppDatabase;
import com.android.udacity.baking.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mayokun on 6/9/2017.
 */
@Module
public class BaseModule {

    private final Context context;

    public BaseModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.Keys.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    AppDatabase providesAppDatabase() {
        return Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();
    }
}

