package com.example.damiank.pomyslna.itemView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.damiank.pomyslna.R;
import com.example.damiank.pomyslna.data.Komentarz;
import com.example.damiank.pomyslna.data.Recipe;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.comment_item)
public class CommentItemView extends  RelativeLayout{

    @ViewById
    TextView text;

    @ViewById
    TextView create;

    @ViewById
    TextView ownerId;



    public CommentItemView(Context context) {
        super(context);
    }

    public void bind(Komentarz komentarz) {
        create.setText(komentarz.created);
        text.setText(komentarz.text);
        ownerId.setText("Autor: " + String.valueOf(komentarz.ownerId));

    }


}
