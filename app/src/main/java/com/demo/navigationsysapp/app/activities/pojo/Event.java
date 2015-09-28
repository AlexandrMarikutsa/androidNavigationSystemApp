package com.demo.navigationsysapp.app.activities.pojo;


public class Event {
    public static String TYPE = "type";
    public static String CREATED_AT = "created_at";
    public static String REPO = "repo";


    private long id;
    private String type;
    private String createdAt;
    private Repo repo;

    public Event(Repo repo) {
        this.repo = repo;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Event(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
