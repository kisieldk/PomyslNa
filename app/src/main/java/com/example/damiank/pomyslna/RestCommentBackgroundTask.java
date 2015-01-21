package com.example.damiank.pomyslna;




import com.example.damiank.pomyslna.data.Account;
import com.example.damiank.pomyslna.data.Comment;
import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.Komentarz;
import com.example.damiank.pomyslna.data.MyRecipe;
import com.example.damiank.pomyslna.data.Recipe;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;


@EBean
public class RestCommentBackgroundTask {
    public static Integer zmiena = 3;
    Integer i = 0;
    @RootContext
    ReadCommentActivity activity;


    @RestService
    PomyslRestClient restClient;



    @Background
    void getComment(String recipeId) {

        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            Comment comment = restClient.getComment(recipeId);
            /*for (Recipe recipe: cookBook.records) {
                if (recipe.pictureId != null) {

                   // Pictures pictures = restClient.getPictureById(recipe.pictureId);
                   // recipe.pictureBytes = pictures.base64bytes;
                }
            }*/
            publishResult1(comment);
        } catch (Exception e) {
            publishError(e);
        }
    }



   /* @Background
    void addComment(Komentarz komentarz, String sessionId){
        restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
        restClient.setHeader("X-Dreamfactory-Session-Token", sessionId);

        try {
            restClient.addComment(komentarz);

        }catch (Exception e){
           i++;
                   if(i<=zmiena){
                       addComment(komentarz,sessionId);
                   }
            else {
                     publishError(e);
                   }
        }
    }*/




    @UiThread
    void publishResult1(Comment comment) {
        try {
            activity.updateMyRecipe2(comment);
        } catch ( NullPointerException e) {
            publishError(e);
        }
    }

    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }

}
