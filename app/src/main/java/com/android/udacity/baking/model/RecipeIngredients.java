package com.android.udacity.baking.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeIngredients implements Parcelable {
    public RecipeIngredients(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public RecipeIngredients(){}

    public static final Creator<RecipeIngredients> CREATOR = new Creator<RecipeIngredients>() {
        @Override
        public RecipeIngredients createFromParcel(Parcel source) {
            RecipeIngredients var = new RecipeIngredients();
            var.quantity = source.readDouble();
            var.measure = source.readString();
            var.ingredient = source.readString();
            return var;
        }

        @Override
        public RecipeIngredients[] newArray(int size) {
            return new RecipeIngredients[size];
        }
    };
    private double quantity;
    private String measure;
    private String ingredient;

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return this.measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
