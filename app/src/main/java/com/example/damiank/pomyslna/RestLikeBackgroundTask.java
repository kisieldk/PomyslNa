package com.example.damiank.pomyslna;


import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.Like;
import com.example.damiank.pomyslna.data.Lubie;
import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.data.Ulubione;
import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;


@EBean
public class RestLikeBackgroundTask {
    public static Integer zmiena = 3;
    Integer i = 0;
    @RootContext
    UlubioneActivity activity;


    @RestService
    PomyslRestClient restClient;

    @Background
    void getLikeBook(User user) {
        restClient.setHeader("X-Dreamfactory-Application-Name","cookbook");
        Like like = restClient.getLikeBook("ownerId="+user.id);


        if(like.records.size()>0) {
           for (Ulubione ulubione : like.records) {
               try {

                  Recipe recipe = restClient.getMyLike(ulubione.recipeId);
                   publishResult(recipe);
               }
               catch (NullPointerException e){publishError(e);}

           }
        }
    }



    @Background
    void addLike(Ulubione ulubione, String sessionId){
        restClient.setHeader("X-Dreamfactory-Session-Token", sessionId);
        restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");


        try {
            restClient.addLike(ulubione);

        }catch (Exception e){
            i++;
            if(i<=zmiena){
                addLike(ulubione,sessionId);
            }
            else {
                publishError(e);
            }
        }
    }





@UiThread
   void publishResult(Recipe recipe) {
       try {
           activity.updateLubie(recipe);
       } catch (NullPointerException e) {
           publishError(e);
       }
   }
    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }

}
