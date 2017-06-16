package com.android.udacity.baking.fragments.recipestep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.android.udacity.baking.R;
import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.base.BaseFragment;
import com.android.udacity.baking.model.Recipe;
import com.android.udacity.baking.model.RecipeIngredients;
import com.android.udacity.baking.model.RecipeSteps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayokun on 6/12/2017.
 */

public class RecipeStepFragment extends BaseFragment implements RecipeStepContract.View {

    @BindView(R.id.ingredient_recycler_view)
    RecyclerView ingredientRecyclerView;

    @BindView(R.id.step_recycler_view)
    RecyclerView stepRecyclerView;

    private static List<RecipeIngredients> recipeIngredientsList;
    private static List<RecipeSteps> recipeStepsList;
    private static RecyclerViewItemClickListener<RecipeSteps> listener;

    public static RecipeStepFragment newInstance(RecyclerViewItemClickListener<RecipeSteps> recipeListener, List<RecipeIngredients> ingredientsList, List<RecipeSteps> stepsList) {
        recipeIngredientsList = ingredientsList;
        recipeStepsList = stepsList;
        listener = recipeListener;
        return new RecipeStepFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_step_fragment, container, false);
        ButterKnife.bind(this, view);


        IngredientAdapter ingredientsAdapter = new IngredientAdapter(R.layout.ingredient_view, recipeIngredientsList);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientRecyclerView.setAdapter(ingredientsAdapter);

        StepAdapter stepAdapter = new StepAdapter(R.layout.step_view, recipeStepsList);
        stepRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepAdapter.setListener(listener);
        stepRecyclerView.setAdapter(stepAdapter);

        return view;
    }

    @Override
    public void setPresenter(RecipeStepContract.Presenter presenter) {

    }

}
