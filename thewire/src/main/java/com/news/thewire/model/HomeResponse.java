package com.news.thewire.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul on 06/04/18.
 */

public class HomeResponse {

    @SerializedName("featured")
    @Expose
    private Featured featured;
    @SerializedName("highlights")
    @Expose
    private Highlights highlights;

    public class Featured {
        @SerializedName("card_title")
        private String cardTitle;

        public String getCardTitle() {
            return cardTitle;
        }

        public void setCardTitle(String cardTitle) {
            this.cardTitle = cardTitle;
        }
    }

    public class Highlights {
        @SerializedName("card_title")
        private String cardTitle;

        public String getCardTitle() {
            return cardTitle;
        }

        public void setCardTitle(String cardTitle) {
            this.cardTitle = cardTitle;
        }
    }

    public void setFeatured(Featured featured) {
        this.featured = featured;
    }

    public void setHighlights(Highlights highlights) {
        this.highlights = highlights;
    }

    public Featured getFeatured() {
        return featured;
    }

    public Highlights getHighlights() {
        return highlights;
    }

    @Override
    public String toString() {
        return "HomeResponse{" +
                "featured=" + featured +
                ", highlights=" + highlights +
                '}';
    }
}
