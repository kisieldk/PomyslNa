package com.example.damiank.pomyslna;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.damiank.pomyslna.adapter.RecipeListAdapter;
import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.MyRecipe;
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

@EActivity (R.layout.activity_my)
@OptionsMenu(R.menu.menu_start)
public class MyRecActivity extends ActionBarActivity {

    @Extra
    User user;

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
        restBackgroundTask.getMyCookBook("ownerId="+user.id);
        ringProgressDialog.dismiss();

    }

    @ItemClick
    void listItemClicked(final Recipe item) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyRecActivity.this);


        alertDialog.setTitle("Potwierdź usunięcie");


        alertDialog.setMessage("Napewno chcesz usunąć ten przepis?");



        alertDialog.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                deletRec(item);
            }
        });


        alertDialog.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(), "Wybrałeś nie", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


        alertDialog.show();

    }


public void deletRec(Recipe item){

        try {
            restBackgroundTask.deleteRecipe(item.id, user.sessionId);
        } catch (NullPointerException e) {
            showError(e);

        }
        Toast.makeText(this, "Usunięto" + item.title, Toast.LENGTH_LONG).show();
        StartActivity_.intent(this).start();

    }
    public void updateMyRecipe(MyRecipe myRecipe) {
        ringProgressDialog.dismiss();
        adapter.update1(myRecipe);
    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

}
