package com.example.damiank.pomyslna;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damiank.pomyslna.adapter.RecipeListAdapter;
import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.Komentarz;
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

@EActivity (R.layout.activity_opis)
@OptionsMenu(R.menu.menu_start)
public class OpisActivity extends ActionBarActivity {


    @Extra
    Recipe item;

    @Extra
    User user;

    @ViewById
    TextView name;

    @ViewById
    EditText coment;

    @ViewById
    TextView czasGotowania;

    @ViewById
    TextView czasPre;

    @ViewById
    TextView skladniki;

    @ViewById
    TextView opis;

    @ViewById
    TextView howTo;

    @ViewById
    TextView who;

    @ViewById
    TextView servs;

    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;

    ProgressDialog ringProgressDialog;
    @AfterViews
    void init() {

        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);

        name.setText(item.title);
        czasGotowania.setText("Czas gotowania: " + String.valueOf(item.cookingMinutes));
        servs.setText("x " + String.valueOf(item.servings));
        czasPre.setText("Czas przygotowania: " + String.valueOf(item.preparationMinutes));
        skladniki.setText(item.ingredients);
        opis.setText(item.introduction);
        howTo.setText(item.steps);
        who.setText("Przygotował " + String.valueOf(item.ownerId));

    }


    @Click
    void btnCommentClicked()    {

        try {
            if (user.id > 0) {
                ringProgressDialog.show();
                Komentarz komentarz = new Komentarz();
                komentarz.text = coment.getText().toString();
                komentarz.recipeId = item.id;
                komentarz.ownerId = user.id;

                restBackgroundTask.addComment(komentarz, user.sessionId);
                ringProgressDialog.dismiss();
                Toast.makeText(this, "dodano komentarz", Toast.LENGTH_LONG).show();


            }


        } catch (Exception e){
            Toast.makeText(this, "Aby dodać komentarz musisz być zalogowany", Toast.LENGTH_LONG).show();
            Recipe recipe;
            recipe = item;

            LoginActivity_.intent(this).extra("recipe",recipe).start();
           }

    }



    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

    /*@OptionsItem
    void settingsSelected() {
    }*/
}
