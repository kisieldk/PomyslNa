package com.example.damiank.pomyslna;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_start)
@OptionsMenu(R.menu.menu_start)
public class StartActivity extends Activity {
@Extra
User user;


    @Click
    void btnDodajClicked(){
try {
    if (user.sessionId != null) {
        AddActivity_.intent(this).user(user).start();
    }
} catch (NullPointerException e){
    LoginActivity_.intent(this).start();}
    }
    @Click
    void btnUlubioneClicked(){

        try {
            if (user.sessionId != null) {
                UlubioneActivity_.intent(this).user(user).start();
            }
        } catch (NullPointerException e){
            LoginActivity_.intent(this).start();}

    }
    @Click
    void btnPrzepisyClicked(){

        OtherRecActivity_.intent(this).start();

    }
    @Click
    void btnUsunClicked(){

        try {
            if (user.sessionId != null) {
                MyRecActivity_.intent(this).user(user).start();
            }
        } catch (NullPointerException e){
            LoginActivity_.intent(this).start();}

    }


}
