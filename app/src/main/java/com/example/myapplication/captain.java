package com.example.myapplication;

public class captain {
    String Id, name,pic;

    public captain() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public captain(String id, String name, String pic) {
        Id = id;
        this.name = name;
        this.pic = pic;
    }
}
