package com.example.damiank.pomyslna;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_add)
@OptionsMenu(R.menu.menu_start)
public class AddActivity extends ActionBarActivity {
    @Extra
    User user;

    @ViewById
    EditText title;

    @ViewById
    EditText introduction;

    @ViewById
    EditText ingredients;

    @ViewById
    EditText steps;

    @ViewById
    EditText servings;

    @ViewById
    EditText cooktime;

    @ViewById
    EditText preparetime;


    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;

    ProgressDialog ringProgressDialog;
    @AfterViews
    void init() {

        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);

    }

    @Click
    void btnDodajClicked() {
        Recipe recipe = new Recipe();
        recipe.title = title.getText().toString();
        recipe.ingredients = ingredients.getText().toString();
        recipe.introduction = introduction.getText().toString();
        recipe.steps = steps.getText().toString();
        recipe.cookingMinutes = Integer.parseInt(cooktime.getText().toString());
        recipe.preparationMinutes = Integer.parseInt(preparetime.getText().toString());
        recipe.servings = Integer.parseInt(servings.getText().toString());
        recipe.ownerId = user.id;

        if (title.getText().length() <  3 || ingredients.getText().length() < 3
                || steps.getText().length() < 3 || servings.getText() == null){
            Toast.makeText(this,"Prosze wypełnić obowiązkowe pola oznaczone *",Toast.LENGTH_LONG ).show();

        }


           try {
               restBackgroundTask.addCookBookEntry(recipe, user.sessionId);
           } catch (NullPointerException e) {
               showError(e);

           }
        Toast.makeText(this, "dodano przepis", Toast.LENGTH_LONG).show();
        StartActivity_.intent(this).start();
        }


    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }


}
