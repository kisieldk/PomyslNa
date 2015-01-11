package com.example.damiank.pomyslna;

import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_start)
@OptionsMenu(R.menu.menu_start)
public class StartActivity extends ActionBarActivity {

    @ViewById
    public EditText login;
    @ViewById
    public EditText haslo;

    @Click
    void btnLogujClicked(){
        if (login.getText().length() <  3 || haslo.getText().length() < 3) {
            Toast.makeText(this,getString(R.string.logerror),Toast.LENGTH_LONG ).show();
        }
    }

}
