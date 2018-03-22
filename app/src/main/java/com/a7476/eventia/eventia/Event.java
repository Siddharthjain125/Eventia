package com.a7476.eventia.eventia;

/**
 * Created by HP on 3/22/2018.
 */

public class Event {
    private String name,eventId;
    private String category;
    private String date , time, description;


    public Event(){

    }

    public Event(String eventId, String name, String category, String date, String time, String description) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.time = time;
        this.description=description;
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
}