package com.example.myapplication;

public class getteamviewlist {
String teampic;
String teamname;
    String address;
    String contact;
    String email;
    String captainName;
    String teamId;


    public getteamviewlist() {
    }

    public getteamviewlist(String teampic, String teamname, String address, String contact, String email, String captainName, String teamId) {
        this.teampic = teampic;
        this.teamname = teamname;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.captainName = captainName;
        this.teamId = teamId;
    }

    public String getTeampic() {
        return teampic;
    }

    public void setTeampic(String teampic) {
        this.teampic = teampic;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
