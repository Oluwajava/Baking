package com.android.udacity.baking.data.local;

import android.graphics.Color;
import android.widget.ImageView;

import com.android.udacity.baking.model.Favourite;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mayokun on 6/13/2017.
 */

public class GetFavourite {

    public static boolean isFavourite(Favourite favourite, AppDatabase db) {
        Observable<Favourite> getFavourite = Observable.create((emitter) -> {
            emitter.onNext(favourite);
        });

        final boolean[] response = new boolean[1];

        getFavourite.subscribeOn(Schedulers.io())
                .subscribe((fav) -> {
                    Favourite f = db.favouriteDao().getFavourite(fav.getRecipeId());
                    if (db.favouriteDao().getFavourite(fav.getRecipeId()) == null)
                        response[0] = false;
                    else
                        response[0] = true;
                });

        return response[0];
    }


    public static void setFavouriteStatus(Favourite favourite, AppDatabase db, ImageView imageView) {
        boolean response = isFavourite(favourite, db);

        if (response) {
            imageView.setColorFilter(Color.RED);
        } else {
            imageView.setColorFilter(Color.WHITE);
        }
    }

    public static void addFavourite(Favourite favourite, AppDatabase db) {
        Observable<Favourite> addFavourite = Observable.create((emitter) -> {
            emitter.onNext(favourite);
        });

        addFavourite.subscribeOn(Schedulers.io()).subscribe((fav) -> {
            db.favouriteDao().insertRecipe(fav);
        });
    }

    public static void deleteFavourite(Favourite favourite, AppDatabase db) {
        Observable<Favourite> deleteFavourite = Observable.create((emitter) -> {
            emitter.onNext(favourite);
        });

        deleteFavourite.subscribeOn(Schedulers.io()).subscribe((fav) -> {
            db.favouriteDao().deleteRecipe(fav);
        });
    }


}
