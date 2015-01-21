package com.example.damiank.pomyslna.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.damiank.pomyslna.data.Comment;
import com.example.damiank.pomyslna.data.Komentarz;
import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.itemView.CommentItemView;
import com.example.damiank.pomyslna.itemView.CommentItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class CommentListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    List<Komentarz> com = new ArrayList<Komentarz>();

    public CommentListAdapter() {
    }

    @Override
    public int getCount() {
        return com.size();
    }

    @Override
    public Komentarz getItem(int i) {
        return com.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommentItemView commentItemView;
        if (convertView == null) {
            commentItemView = CommentItemView_.build(context);
        } else {
           commentItemView = (CommentItemView) convertView;
        }

            commentItemView.bind(getItem(position));

        return commentItemView;
    }


    public void update2(Comment comment) {
        com.clear();
        com.addAll(comment.records);
        notifyDataSetChanged();
    }
}
