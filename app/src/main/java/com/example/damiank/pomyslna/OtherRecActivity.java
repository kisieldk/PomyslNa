package com.example.damiank.pomyslna;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.damiank.pomyslna.adapter.RecipeListAdapter;
import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.Recipe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_other)
@OptionsMenu(R.menu.menu_start)
public class OtherRecActivity extends ActionBarActivity {


    @ViewById
    ListView list;

    @Bean
    RecipeListAdapter adapter;

    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init() {
        list.setAdapter(adapter);
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);
        ringProgressDialog.show();
        restBackgroundTask.getCookBook();
        ringProgressDialog.dismiss();
    }

    @ItemClick
    void listItemClicked(Recipe item) {

        OpisActivity_.intent(this).extra("item",item).start();
    }


    @Click
    void refreshClicked()    {
        ringProgressDialog.show();
        restBackgroundTask.getCookBook();
    }

    public void updateCookbook(CookBook cookBook) {
        ringProgressDialog.dismiss();
        adapter.update(cookBook);
    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

    /*@OptionsItem
    void settingsSelected() {
    }*/
}
