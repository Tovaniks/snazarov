package ru.job4j.tracker;

public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;
    //private String[] comments;

    public Item(String name, String description, long create) {
        this.name = name;
        this.desc = description;
        this.created = create;
    }

    public String getID() {
        return this.id;
    }


    public String getName() {
        return name;
    }


    public String getDesc() {
        return desc;
    }


    public long getCreated() {
        return created;
    }

    public void setID(String id) {
        this.id = id;
    }

}
