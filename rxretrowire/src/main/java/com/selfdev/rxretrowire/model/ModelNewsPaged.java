package com.selfdev.rxretrowire.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rahul on 07/04/18.
 */

public class ModelNewsPaged {

    @SerializedName("ID")
    @Expose
    private long id;

    @SerializedName("post_author")
    @Expose
    private String postAuthor;

    @SerializedName("post_date")
    @Expose
    private String postDate;

    @SerializedName("post_date_gmt")
    @Expose
    private String postDateGMT;

    @SerializedName("post_title")
    @Expose
    private String postTitle;

    @SerializedName("post_excerpt")
    @Expose
    private String postExcerpt;

    @SerializedName("post_status")
    @Expose
    private String postStatus;

    @SerializedName("post_name")
    @Expose
    private String postName;

    @SerializedName("post_parent")
    @Expose
    private int postParent;

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("post_type")
    @Expose
    private String postType;

    @SerializedName("date_time_display")
    @Expose
    private String dateTimeDisplay;

    @SerializedName("storytype_display")
    @Expose
    private String storyTypeDisplay;

    @SerializedName("view_count")
    @Expose
    private String viewCount;

    @SerializedName("featured_image")
    @Expose
    private String[] featuredImage;

    @SerializedName("post_author_name")
    @Expose
    private PostAuthorName[] postAuthorName;

    public long getId() {
        return id;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPostDateGMT() {
        return postDateGMT;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public String getPostName() {
        return postName;
    }

    public int getPostParent() {
        return postParent;
    }

    public String getGuid() {
        return guid;
    }

    public String getPostType() {
        return postType;
    }

    public String getDateTimeDisplay() {
        return dateTimeDisplay;
    }

    public String getStoryTypeDisplay() {
        return storyTypeDisplay;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String[] getFeaturedImage() {
        return featuredImage;
    }

    public PostAuthorName[] getPostAuthorName() {
        return postAuthorName;
    }

    public class PostAuthorName {

        @SerializedName("author_avatar")
        @Expose
        private String authorAvatar;

        @SerializedName("author_name")
        @Expose
        private String authorName;

        @SerializedName("author_slug")
        @Expose
        private String authorSlug;

        public String getAuthorAvatar() {
            return authorAvatar;
        }

        public String getAuthorName() {
            return authorName;
        }

        public String getAuthorSlug() {
            return authorSlug;
        }
    }

    private static ModelNewsPaged[] modelNews;
    private static ArrayList<ModelNewsPaged> modelNewsPagedArrayList;
    private static ArrayList<ModelNewsPaged> modelEconomicsNewsPagedArrayList;

    public static ModelNewsPaged[] getModelNews() {
        return modelNews;
    }

    public static void setModelNews(ModelNewsPaged[] modelNews) {
        ModelNewsPaged.modelNews = modelNews;
    }

    public static void addModelNews(ArrayList<ModelNewsPaged> modelNewsPagedArrayList) {
        if (ModelNewsPaged.modelNewsPagedArrayList !=null) {
            ModelNewsPaged.modelNewsPagedArrayList.addAll(modelNewsPagedArrayList);
        } else {
            ModelNewsPaged.modelNewsPagedArrayList = new ArrayList<>();
            ModelNewsPaged.modelNewsPagedArrayList.addAll(modelNewsPagedArrayList);
        }
    }

    public static ArrayList<ModelNewsPaged> getModelNewsPagedArrayList() {
        return (modelNewsPagedArrayList!=null)?modelNewsPagedArrayList:new ArrayList<ModelNewsPaged>();
    }

    public static void addEconomicsNews(ArrayList<ModelNewsPaged> modelNewsPagedArrayList) {
        if (ModelNewsPaged.modelEconomicsNewsPagedArrayList !=null) {
            ModelNewsPaged.modelEconomicsNewsPagedArrayList.addAll(modelNewsPagedArrayList);
        } else {
            ModelNewsPaged.modelEconomicsNewsPagedArrayList = new ArrayList<>();
            ModelNewsPaged.modelEconomicsNewsPagedArrayList.addAll(modelNewsPagedArrayList);
        }
    }

    public static ArrayList<ModelNewsPaged> getEconomicsNewsPagedArrayList() {
        return (modelEconomicsNewsPagedArrayList!=null)?modelEconomicsNewsPagedArrayList:new ArrayList<ModelNewsPaged>();
    }
}
