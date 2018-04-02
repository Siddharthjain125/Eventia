package com.a7476.eventia.eventia;

import android.os.Parcelable;

import java.io.Serializable;


public class Event implements Serializable {
    private String name,eventId,city,venue,host;
    private String category;
    private String date , time, description;


    public Event(){

    }

    public Event(String eventId, String name, String category, String date, String time, String description,String venue ,String city,String host) {
        this.eventId = eventId;
        this.name = name;
        this.category = category;
        this.date = date;
        this.time = time;
        this.description=description;
        this.city = city;
        this.venue = venue;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public String getVenue() {
        return venue;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
