package com.example.damiank.pomyslna;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_start)
@OptionsMenu(R.menu.menu_start)
public class StartActivity extends Activity {



    @Click
    void btnDodajClicked(){

        LoginActivity_.intent(this).start();

    }
    @Click
    void btnUlubioneClicked(){

        MyRecActivity_.intent(this).start();

    }
    @Click
    void btnPrzepisyClicked(){

        OtherRecActivity_.intent(this).start();

    }
    @Click
    void btnUsunClicked(){

        MenuActivity_.intent(this).start();

    }


}
