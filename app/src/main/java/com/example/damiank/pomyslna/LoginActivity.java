package com.example.damiank.pomyslna;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damiank.pomyslna.data.EmailAndPassword;
import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ResourceBundle;

@EActivity (R.layout.activity_login)
@OptionsMenu(R.menu.menu_start)
public class LoginActivity extends Activity {


    @Extra()
    Recipe recipe;



    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    @ViewById
    EditText log;

    @ViewById
    EditText haslo;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init() {
        Toast.makeText(this, recipe.title, Toast.LENGTH_LONG).show();
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);

    }

    @Click
    void btnRejestrujClicked(){
        RegisterActivity_.intent(this).start();
    }

    @Click
    void btnLogujClicked(){
        if (log.getText().length() <  3 || haslo.getText().length() < 3) {
            Toast.makeText(this,getString(R.string.logerror),Toast.LENGTH_LONG ).show();

        }
        ringProgressDialog.show();
        EmailAndPassword emailAndPassword = new EmailAndPassword();
        emailAndPassword.email = log.getText().toString(); //"example@example.com";
        emailAndPassword.password = haslo.getText().toString(); //"test00";
        restLoginBackgroundTask.login(emailAndPassword);

    }

    public void loginSuccess(User user) {
        ringProgressDialog.dismiss();
        Recipe item;
        item = recipe;
        try {
            if (item.id > 0) {
                OpisActivity_.intent(this).extra("user", user).extra("item", item).start();
            }
        }
        catch (Exception e){
            StartActivity_.intent(this).user(user).start();
        }
    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }



}
