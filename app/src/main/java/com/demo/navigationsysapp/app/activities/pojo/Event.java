package com.demo.navigationsysapp.app.activities.pojo;


public class Event {
    private long id;
    private String type;
    private String actorAvatar;

    public Event(String type) {
        this.type = type;
    }

    public String getActorAvatar() {
        return actorAvatar;
    }

    public void setActorAvatar(String actorAvatar) {
        this.actorAvatar = actorAvatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
