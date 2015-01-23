package com.example.damiank.pomyslna.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.MyRecipe;
import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.itemView.RecipeItemView;
import com.example.damiank.pomyslna.itemView.RecipeItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class RecipeListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    List<Recipe> recipeL = new ArrayList<Recipe>();

    public RecipeListAdapter() {
    }

    @Override
    public int getCount() {
        return recipeL.size();
    }

    @Override
    public Recipe getItem(int i) {
        return recipeL.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecipeItemView recipeItemView;
        if (convertView == null) {
            recipeItemView = RecipeItemView_.build(context);
        } else {
            recipeItemView = (RecipeItemView) convertView;
        }

            recipeItemView.bind(getItem(position));

        return recipeItemView;
    }

    public void update(CookBook cookBook) {
        recipeL.clear();
        recipeL.addAll(cookBook.records);
        notifyDataSetChanged();
    }
    public void update1(MyRecipe myRecipe) {
        recipeL.clear();
        recipeL.addAll(myRecipe.records);
        notifyDataSetChanged();
    }
    public void update2(Recipe recipe) {

        recipeL.add(recipe);
        notifyDataSetChanged();
    }

}
