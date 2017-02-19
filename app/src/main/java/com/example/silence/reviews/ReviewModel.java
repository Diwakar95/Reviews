package com.example.silence.reviews;

/**
 * Created by Silence on 03-Nov-16.
 */
public class ReviewModel {

    public String title;
    public String comment;
    public String usefulness;
    public String reviewer_name;
    public String ratings;
    public String delivery_time;
    public String discounts_and_offers;
    public String packaging;
    public String connection_level;

    public ReviewModel()
    {

    }

    public ReviewModel(String title, String comment, String usefulness,
                       String reviewer_name, String ratings, String delivery_time,
                       String discounts_and_offers, String packaging, String connection_level) {
        this.title = title;
        this.comment = comment;
        this.usefulness = usefulness;
        this.reviewer_name = reviewer_name;
        this.ratings = ratings;
        this.delivery_time = delivery_time;
        this.discounts_and_offers = discounts_and_offers;
        this.packaging = packaging;
        this.connection_level = connection_level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsefulness() {
        return usefulness;
    }

    public void setUsefulness(String usefulness) {
        this.usefulness = usefulness;
    }

    public String getReviewer_name() {
        return reviewer_name;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getDiscounts_and_offers() {
        return discounts_and_offers;
    }

    public void setDiscounts_and_offers(String discounts_and_offers) {
        this.discounts_and_offers = discounts_and_offers;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getConnection_level() {
        return connection_level;
    }

    public void setConnection_level(String connection_level) {
        this.connection_level = connection_level;
    }
}
