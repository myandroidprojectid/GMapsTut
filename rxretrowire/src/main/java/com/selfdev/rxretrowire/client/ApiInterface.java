package com.selfdev.rxretrowire.client;

import com.selfdev.rxretrowire.model.HomeResponse;
import com.selfdev.rxretrowire.model.ModelCategory;
import com.selfdev.rxretrowire.model.ModelNews;
import com.selfdev.rxretrowire.model.ModelNewsDetails;
import com.selfdev.rxretrowire.model.ModelNewsPaged;

import org.json.JSONArray;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rahul on 06/04/18.
 */

public interface ApiInterface {
    @GET("menu/categories")
    Observable<ModelCategory[]> getCategories();

    @GET("home/all/")
    Observable<HomeResponse> getHome(@Query("arrparam") JSONArray homeParams);

    @GET("posts/category/politics/all/")
    Observable<ModelNews> getPolitics(@Query("orderedpost") int orderedpost, @Query("recentpost") int recentpost, @Query("arrparam") JSONArray homeParams);


    @GET("posts/category/politics/recent-stories/")
    Observable<ModelNewsPaged[]> getPolitics(@Query("per_page") int perPage, @Query("page") int page);

    //https://thewire.in/wp-json/thewire/v2/posts/category/economy/recent-stories/?per_page=10&page=1
    @GET("posts/category/economy/recent-stories/")
    Observable<ModelNewsPaged[]> getEconomy(@Query("per_page") int perPage, @Query("page") int page);



    @GET("posts/category/politics/recent-stories/")
    Observable<ArrayList<ModelNewsPaged>> getPoliticsArrayListObservable(@Query("per_page") int perPage, @Query("page") int page);

    //https://thewire.in/wp-json/thewire/v2/posts/category/economy/recent-stories/?per_page=10&page=1
    @GET("posts/category/economy/recent-stories/")
    Observable<ArrayList<ModelNewsPaged>> getEconomyArrayListObservable(@Query("per_page") int perPage, @Query("page") int page);

    @GET("posts/detail/{postName}")
    Observable<ModelNewsDetails> getDetailedNews(@Path("postName") String postName);
}