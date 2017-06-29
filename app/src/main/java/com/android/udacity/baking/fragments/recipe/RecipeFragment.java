package com.android.udacity.baking.fragments.recipe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.udacity.baking.R;
import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.base.BaseFragment;
import com.android.udacity.baking.data.local.AppDatabase;
import com.android.udacity.baking.model.Recipe;
import com.android.udacity.baking.model.RecipeIngredients;
import com.android.udacity.baking.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayokun on 6/10/2017.
 */

public class RecipeFragment extends BaseFragment implements RecipeContract.View{

    RecipeContract.Presenter recipePresenter;

    @BindView(R.id.recipe_layout) RecyclerView recipeRecyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);

        recipePresenter.start();

        return view;
    }

    @Override
    public void setPresenter(RecipeContract.Presenter presenter) {
        this.recipePresenter = presenter;
    }

    @Override
    public void setAdapter(List<Recipe> recipeList, RecyclerViewItemClickListener<Recipe> recipeListener, AppDatabase localDb) {
        RecipeAdapter recipeAdapter = new RecipeAdapter(R.layout.recipe_card, recipeList);
        recipeAdapter.setListener(recipeListener);
        recipeAdapter.setLocalDb(localDb);

        if(getResources().getBoolean(R.bool.isTablet)) {
            recipeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        } else {
            recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        recipeRecyclerView.setAdapter(recipeAdapter);
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(Constants.Keys.shared_preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        String text = "";
        for(RecipeIngredients ingredient: recipeList.get(0).getIngredients()) {
            text.concat(ingredient.getIngredient()+"\n");
        }
        editor.putString(Constants.Keys.ingredients, text);
        editor.commit();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
