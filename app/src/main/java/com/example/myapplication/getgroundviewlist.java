package com.example.myapplication;

public class getgroundviewlist {
    String groundpic;
    String groundname;
    String address;
    String email;
    String contact,groundId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public getgroundviewlist(String groundpic, String groundname, String address, String email, String contact, String groundId) {
        this.groundpic = groundpic;
        this.groundname = groundname;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.groundId = groundId;
    }




    public String getGroundpic() {
        return groundpic;
    }

    public void setGroundpic(String groundpic) {
        this.groundpic = groundpic;
    }

    public String getGroundname() {
        return groundname;
    }

    public void setGroundname(String groundname) {
        this.groundname = groundname;
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

    public String getGroundId() {
        return groundId;
    }

    public void setGroundId(String groundId) {
        this.groundId = groundId;
    }
}
