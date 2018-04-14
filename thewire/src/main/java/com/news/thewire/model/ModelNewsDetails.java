package com.news.thewire.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul on 07/04/18.
 */

public class ModelNewsDetails {
    @SerializedName("post-detail")
    @Expose
    private PostDetail[] postDetails;

    public class PostDetail {
        @SerializedName("post_title")
        @Expose
        private String postTitle;

        @SerializedName("post_excerpt")
        @Expose
        private String postExcerpt;

        @SerializedName("post_content")
        @Expose
        private String postContent;

        @SerializedName("date_time_display")
        @Expose
        private String dateTimeDisplay;

        @SerializedName("featured_image")
        @Expose
        private String[] featuredImage;

        @SerializedName("post_author_name")
        @Expose
        private PostAuthorName[] postAuthorName;

        @SerializedName("prime_category")
        @Expose
        private PrimeCategory[] primeCategory;

        public String getPostTitle() {
            return postTitle;
        }

        public String getPostExcerpt() {
            return postExcerpt;
        }

        public String getPostContent() {
            return postContent;
        }

        public String getDateTimeDisplay() {
            return dateTimeDisplay;
        }

        public String[] getFeaturedImage() {
            return featuredImage;
        }

        public PostAuthorName[] getPostAuthorName() {
            return postAuthorName;
        }

        public PrimeCategory[] getPrimeCategory() {
            return primeCategory;
        }
    }

    public PostDetail[] getPostDetails() {
        return postDetails;
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

    public class PrimeCategory {

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("slug")
        @Expose
        private String slug;

        public String getName() {
            return name;
        }

        public String getSlug() {
            return slug;
        }
    }

    private static ModelNewsDetails modelNewsDetails;

    public static ModelNewsDetails getModelNewsDetails() {
        return modelNewsDetails;
    }

    public static void setModelNewsDetails(ModelNewsDetails modelNewsDetails) {
        ModelNewsDetails.modelNewsDetails = modelNewsDetails;
    }
}
