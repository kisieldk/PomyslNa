package com.example.damiank.pomyslna;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damiank.pomyslna.data.Account;
import com.example.damiank.pomyslna.data.EmailAndPassword;
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

@EActivity (R.layout.activity_register)
@OptionsMenu(R.menu.menu_start)
public class RegisterActivity extends Activity {
    @Extra
    Account account;
    @Extra
    User user;
    @ViewById
    EditText email;

    @ViewById
    EditText first_name;

    @ViewById
    EditText last_name;

    @ViewById
    EditText new_password;

    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;
    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    ProgressDialog ringProgressDialog;
    @AfterViews
    void init() {

        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);

    }

    @Click
    void btnDodajClicked() {
        ringProgressDialog.show();
        Account account = new Account();
        account.email = email.getText().toString();
        account.first_name = first_name.getText().toString();
        account.last_name = last_name.getText().toString();
        account.new_password = new_password.getText().toString();

        if (new_password.getText().toString().length() < 6) {
            Toast.makeText(this, "Hasło musi mieć min 6 znaków!", Toast.LENGTH_LONG).show();
        } else if (email.getText().length() > 3 && first_name.getText().length() > 3
                && last_name.getText().length() > 3) {

            try {
                restBackgroundTask.createUser(account);
            } catch (NullPointerException e) {
                showError(e);

            }
            EmailAndPassword emailAndPassword = new EmailAndPassword();
            emailAndPassword.email = email.getText().toString(); //"example@example.com";
            emailAndPassword.password = new_password.getText().toString(); //"test00";
            restLoginBackgroundTask.login(emailAndPassword);


            Toast.makeText(this, "Zarejestrowano", Toast.LENGTH_LONG).show();
            ringProgressDialog.dismiss();
            StartActivity_.intent(this).start();
            finish();

        } else {
            Toast.makeText(this, "Prosze wypełnić wszystkie pola", Toast.LENGTH_LONG).show();
        }


    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }


}
