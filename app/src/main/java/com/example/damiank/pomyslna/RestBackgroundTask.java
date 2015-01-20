package com.example.damiank.pomyslna;




import com.example.damiank.pomyslna.data.Account;
import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.Komentarz;
import com.example.damiank.pomyslna.data.MyRecipe;
import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.data.Pictures;
import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;



@EBean
public class RestBackgroundTask {
    @RootContext
    OtherRecActivity activity;
    @RootContext
    MyRecActivity activity1;

    @RestService
    PomyslRestClient restClient;

    @Background
    void getCookBook() {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            CookBook cookBook = restClient.getCookBook();
            /*for (Recipe recipe: cookBook.records) {
                if (recipe.pictureId != null) {

                   // Pictures pictures = restClient.getPictureById(recipe.pictureId);
                   // recipe.pictureBytes = pictures.base64bytes;
                }
            }*/
            publishResult(cookBook);
        } catch (Exception e) {
            publishError(e);
        }
    }

    @Background
    void getMyCookBook(String ownerId) {

        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            MyRecipe myRecipe = restClient.getMyCookBook(ownerId);
            /*for (Recipe recipe: cookBook.records) {
                if (recipe.pictureId != null) {

                   // Pictures pictures = restClient.getPictureById(recipe.pictureId);
                   // recipe.pictureBytes = pictures.base64bytes;
                }
            }*/
            publishResult1(myRecipe);
        } catch (Exception e) {
            publishError(e);
        }
    }


    @Background
    void addCookBookEntry(Recipe recipe, String sessionId) {
        try {
            restClient.setHeader("X-Dreamfactory-Session-Token", sessionId);
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.addCookBookEntry(recipe);
          // CookBook cookBook = restClient.getCookBook();
          // publishResult(cookBook);
        } catch (NullPointerException e) {
            publishError(e);
        }
    }

    @Background
    void createUser(Account account){
             try {
                 restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
                 restClient.createUser(account);
             }
             catch (NullPointerException e){
                 publishError(e);
             }
    }

    @Background
    void deleteRecipe(Integer id, String sessionId) {
        try {
            restClient.setHeader("X-Dreamfactory-Session-Token", sessionId);
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.deleteRecipe(id);
            // CookBook cookBook = restClient.getCookBook();
            // publishResult(cookBook);
        } catch (NullPointerException e) {
            publishError(e);
        }
    }
    @Background
    void addComment(Komentarz komentarz, String sessionId){
        restClient.setHeader("X-Dreamfactory-Session-Token", sessionId);
        restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
        try {
            restClient.addComment(komentarz);

        }catch (NullPointerException e){
            publishError(e);
        }
    }



    @UiThread
    void publishResult(CookBook cookBook) {
        try {
            activity.updateCookbook(cookBook);
        } catch ( NullPointerException e) {
            publishError(e);
        }
        }
    @UiThread
    void publishResult1(MyRecipe myRecipe) {
        try {
            activity1.updateMyRecipe(myRecipe);
        } catch ( NullPointerException e) {
            publishError(e);
        }
    }

    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }

}
