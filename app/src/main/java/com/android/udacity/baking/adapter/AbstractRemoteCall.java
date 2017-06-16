package com.android.udacity.baking.adapter;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Mayokun on 6/12/2017.
 */

public abstract class AbstractRemoteCall<T, S> {

    protected abstract Call<List<T>> makeListCall(S s);

    protected abstract Call<T> makeCall(S s);

}
