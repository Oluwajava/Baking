package com.android.udacity.baking.fragments.recipedetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.udacity.baking.R;
import com.android.udacity.baking.adapter.NavigationListener;
import com.android.udacity.baking.base.BaseFragment;
import com.android.udacity.baking.model.RecipeSteps;
import com.android.udacity.baking.utils.VideoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Mayokun on 6/14/2017.
 */

public class RecipeDetailFragment extends BaseFragment implements RecipeDetailContract.View {

    private static final String TAG = RecipeDetailFragment.class.getSimpleName();
    private static RecipeSteps recipeSteps;
    private static NavigationListener listener;
    private VideoPlayer videoPlayer;

    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.previous)
    @Nullable
    Button previous;
    @BindView(R.id.next)
    @Nullable
    Button next;

    private RecipeDetailContract.Presenter presenter;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    public static RecipeDetailFragment newInstance(NavigationListener navigationListener, RecipeSteps r) {
        recipeSteps = r;
        listener = navigationListener;
        return new RecipeDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        mPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.playerView);
        videoPlayer = new VideoPlayer.Builder(mPlayerView)
                .exoPlayer(mExoPlayer)
                .context(getActivity())
                .uri(Uri.parse(recipeSteps.getVideoURL()))
                .build();
        videoPlayer.initializePlayer();

        setDetail(recipeSteps);

        if (!getResources().getBoolean(R.bool.isTablet)) {
            previous.setOnClickListener((v) -> {
                listener.onPrevious();
            });

            next.setOnClickListener((v) -> {
                listener.onNext();
            });
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.releasePlayer();
    }

    @Override
    public void setPresenter(RecipeDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDetail(RecipeSteps recipeSteps) {
        description.setText(recipeSteps.getDescription());
        if(mExoPlayer != null)
            videoPlayer.releasePlayer();
        videoPlayer.prepareMediaSource(Uri.parse(recipeSteps.getVideoURL()));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
