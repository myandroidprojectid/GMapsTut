package com.news.thewire.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul on 06/04/18.
 */

public class ModelCategory {

    private static ModelCategory[] modelCategory;

    @SerializedName("ID")
    private int id;
    @SerializedName("object_id")
    private String objectId;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("slug")
    private String slug;
    @SerializedName("menu_item_parent")
    private String menuItemParent;
    @SerializedName("subcategories")
    private Object[] subcategories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMenuItemParent() {
        return menuItemParent;
    }

    public void setMenuItemParent(String menuItemParent) {
        this.menuItemParent = menuItemParent;
    }

    public Object[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Object[] subcategories) {
        this.subcategories = subcategories;
    }

    public static void setModelCategories(ModelCategory[] modelCategories) {
        modelCategory = modelCategories;
    }

    public static ModelCategory[] getModelCategories() {
        if (modelCategory!=null) {
            return modelCategory;
        } else {
            return null;
        }
    }
}
