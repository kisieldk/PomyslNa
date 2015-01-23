package com.example.damiank.pomyslna;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.Toast;

import com.example.damiank.pomyslna.adapter.RecipeListAdapter;
import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_mylike)
@OptionsMenu(R.menu.menu_start)
public class UlubioneActivity extends Activity {

@Extra
 User user;

    @ViewById
    ListView list;

    @Bean
   RecipeListAdapter adapter;

    @Bean
    @NonConfigurationInstance
    RestLikeBackgroundTask restLikeBackgroundTask;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init() {
        list.setAdapter(adapter);
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);
        ringProgressDialog.show();
        restLikeBackgroundTask.getLikeBook(user);




    }
    @ItemClick
    void listItemClicked(Recipe item) {
        Toast.makeText(this, new Integer(item.id).toString(), Toast.LENGTH_LONG).show();
    }
    @Click
    void refreshClicked()    {
        ringProgressDialog.show();
        restLikeBackgroundTask.getLikeBook(user);
    }



    public void updateLubie(Recipe recipe) {

        ringProgressDialog.dismiss();
        adapter.update2(recipe);




    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

}
