package com.example.damiank.pomyslna;

import android.graphics.Picture;

import com.example.damiank.pomyslna.data.Account;
import com.example.damiank.pomyslna.data.Comment;
import com.example.damiank.pomyslna.data.CookBook;
import com.example.damiank.pomyslna.data.EmailAndPassword;
import com.example.damiank.pomyslna.data.Komentarz;
import com.example.damiank.pomyslna.data.Like;
import com.example.damiank.pomyslna.data.Lubie;
import com.example.damiank.pomyslna.data.MyRecipe;
import com.example.damiank.pomyslna.data.Pictures;
import com.example.damiank.pomyslna.data.Recipe;
import com.example.damiank.pomyslna.data.Ulubione;
import com.example.damiank.pomyslna.data.User;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Rest(rootUrl = "http://pegaz.wzr.ug.edu.pl:8080/rest", converters = {
        MappingJackson2HttpMessageConverter.class })


@RequiresHeader({"X-Dreamfactory-Session-Token", "X-Dreamfactory-Application-Name"})


public interface PomyslRestClient extends RestClientHeaders{
    @Get("/db/recipes?order=created DESC")
    CookBook getCookBook();

  @Get("/db/recipes?filter={path}")
  MyRecipe getMyCookBook(String path);

    @Delete("/db/recipes/{id}")
   void deleteRecipe(Integer id);

    @Post("/db/recipes")
    void addCookBookEntry(Recipe recipe);

    @Post("/user/session")
    User login(EmailAndPassword emailAndPassword);


    @Post("/user/register/?login=true")
    Account createUser(Account account);



    @Post("/db/comments")
    void addComment(Komentarz komentarz);

    @Get("/db/comments?filter={path}")
    Comment getComment(String path);

   //@Get("/db/pictures/{id}")
   //Pictures getPictureById(int id);

    @Get("/db/likes?filter={path}")
    Like getLikeBook(String path);

    @Get("/db/recipes/{id}")
    Lubie getMyLike(Integer id);

    @Post("/db/likes")
    void addLike(Ulubione ulubione);
}
