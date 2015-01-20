package com.example.damiank.pomyslna.itemView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.damiank.pomyslna.R;
import com.example.damiank.pomyslna.data.Recipe;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;



@EViewGroup(R.layout.przepis_item)
public class RecipeItemView extends  RelativeLayout{
    @ViewById
    ImageView logoEmpty;

    @ViewById
    TextView name;

    @ViewById
    TextView czasGotowania;

    @ViewById
    TextView servs;
    @ViewById
    TextView created;


    public RecipeItemView(Context context) {
        super(context);
    }

    public void bind(Recipe recipe) {
        created.setText(recipe.created);
        name.setText(recipe.title);
        czasGotowania.setText("Czas gotowania: " + String.valueOf(recipe.cookingMinutes));
        servs.setText("x " + String.valueOf(recipe.servings));
        if (recipe.pictureBytes != null) {
            decodeAndSetImage(recipe.pictureBytes, logoEmpty);
        } else {
            logoEmpty.setImageDrawable(null);
        }
    }

   private void decodeAndSetImage(String base64bytes, ImageView image) {
        // zamień ciąg tekstowy Base-64 na tablicę bajtów
        byte[] decodedString = Base64.decode(base64bytes, Base64.DEFAULT);
        // utwórz bitmapę na podstawie ciągu bajtów z obrazem JPEG
        Bitmap decodedBytes = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        // wstaw bitmapę do komponentu ImageView awatara
        image.setImageBitmap(decodedBytes);
    }
}
