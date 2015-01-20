package com.example.damiank.pomyslna;

import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_menu)
@OptionsMenu(R.menu.menu_start)
public class UlubioneActivity extends ActionBarActivity {

@Extra
 User user;

}
