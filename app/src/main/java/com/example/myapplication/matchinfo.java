package com.example.myapplication;

public class matchinfo {
    String Team1name,Team2name,over,venue,Historyid,matchinfoId;

    public matchinfo(String team1name, String team2name, String over, String venue, String historyid, String matchinfoId) {
        Team1name = team1name;
        Team2name = team2name;
        this.over = over;
        this.venue = venue;
        Historyid = historyid;
        this.matchinfoId = matchinfoId;
    }

    public String getHistoryid() {
        return Historyid;
    }

    public void setHistoryid(String historyid) {
        Historyid = historyid;
    }

    public String getMatchinfoId() {
        return matchinfoId;
    }

    public void setMatchinfoId(String matchinfoId) {
        this.matchinfoId = matchinfoId;
    }

    public String getTeam1name() {
        return Team1name;
    }

    public void setTeam1name(String team1name) {
        Team1name = team1name;
    }

    public String getTeam2name() {
        return Team2name;
    }

    public void setTeam2name(String team2name) {
        Team2name = team2name;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}

