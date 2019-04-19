package com.example.myapplication;

public class User {
    String id;
    String name;
    String email;
    String address;
    String contact;

    public User(String id, String name, String email, String address, String contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public static class teamRating {
        String TeamId;
        String TeamName;
        float rating;

        public String getTeamId() {
            return TeamId;
        }

        public void setTeamId(String teamId) {
            TeamId = teamId;
        }

        public String getTeamName() {
            return TeamName;
        }

        public void setTeamName(String teamName) {
            TeamName = teamName;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public teamRating(String teamId, String teamName, float rating) {
            TeamId = teamId;
            TeamName = teamName;
            this.rating = rating;
        }
    }
}
