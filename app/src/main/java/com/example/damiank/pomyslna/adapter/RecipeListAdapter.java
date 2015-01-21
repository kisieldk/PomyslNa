package com.example.damiank.pomyslna.adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.damiank.pomyslna.data.Comment;
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

    List<Recipe> recipe = new ArrayList<Recipe>();

    public RecipeListAdapter() {
    }

    @Override
    public int getCount() {
        return recipe.size();
    }

    @Override
    public Recipe getItem(int i) {
        return recipe.get(i);
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
        recipe.clear();
        recipe.addAll(cookBook.records);
        notifyDataSetChanged();
    }
    public void update1(MyRecipe myRecipe) {
        recipe.clear();
        recipe.addAll(myRecipe.records);
        notifyDataSetChanged();
    }

}
