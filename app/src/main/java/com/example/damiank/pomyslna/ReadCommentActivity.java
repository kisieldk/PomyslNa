package com.example.damiank.pomyslna;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.Toast;

import com.example.damiank.pomyslna.adapter.CommentListAdapter;
import com.example.damiank.pomyslna.data.Comment;
import com.example.damiank.pomyslna.data.Recipe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_ctlist)
@OptionsMenu(R.menu.menu_start)
public class ReadCommentActivity extends Activity {

    @Extra
    Recipe recipe;

    @ViewById
    ListView list;

    @Bean
    CommentListAdapter adapter;


    @Bean
    @NonConfigurationInstance
    RestCommentBackgroundTask restCommentBackgroundTask;



    @AfterViews
    void init() {
        Toast.makeText(this, recipe.title, Toast.LENGTH_LONG).show();

        list.setAdapter(adapter);

        try {
            restCommentBackgroundTask.getComment("recipeId=" + recipe.id.toString());
        }
        catch (Exception e){
            Toast.makeText(this,"co?", Toast.LENGTH_LONG).show();
        }


    }





    public void updateMyRecipe2(Comment comment) {

        adapter.update2(comment);
    }

    public void showError(Exception e) {

        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

}
