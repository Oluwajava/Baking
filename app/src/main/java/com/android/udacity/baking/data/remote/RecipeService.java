package com.android.udacity.baking.data.remote;

import com.android.udacity.baking.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mayokun on 6/12/2017.
 */

public interface RecipeService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipies();

}
