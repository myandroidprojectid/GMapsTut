package com.news.thewire.client;

import com.news.thewire.model.HomeParams;
import com.news.thewire.model.HomeResponse;
import com.news.thewire.model.ModelCategory;
import com.news.thewire.model.ModelNews;
import com.news.thewire.model.ModelNewsDetails;
import com.news.thewire.model.ModelNewsPaged;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rahul on 06/04/18.
 */

public interface ApiInterface {
    @GET("menu/categories")
    Call<ModelCategory[]> getCategories();

    @GET("home/all/")
    Call<HomeResponse> getHome(@Query("arrparam") JSONArray homeParams);

    @GET("posts/category/politics/all/")
    Call<ModelNews> getPolitics(@Query("orderedpost") int orderedpost, @Query("recentpost") int recentpost, @Query("arrparam") JSONArray homeParams);


    @GET("posts/category/politics/recent-stories/")
    Call<ModelNewsPaged[]> getPolitics(@Query("per_page") int perPage, @Query("page") int page);

    @GET("posts/detail/{postName}")
    Call<ModelNewsDetails> getDetailedNews(@Path("postName") String postName);
}