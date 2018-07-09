package ru.job4j.tracker;

import java.time.LocalDateTime;

public class Item {
    private String id;
    private String name;
    private String desc;
    private LocalDateTime created;

    public Item(final String id, final String name, final String description, final LocalDateTime create) {
        this.id = id;
        this.name = name;
        this.desc = description;
        this.created = create;
    }

    public Item(final String name, final String description) {
        this.name = name;
        this.desc = description;
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

    public LocalDateTime getTime() {
        return created;
    }

    public void setID(final String id) {
        this.id = id;
    }

    public void setTime(final LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Item) {
            Item item = (Item) obj;
            result = item.getID().equals(this.getID()) && item.getName().equals(this.getName()) && item.getDesc().equals(this.getDesc()) && item.getTime().equals(this.getTime());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + desc.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }
}
