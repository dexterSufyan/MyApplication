package com.example.myapplication;

public class geteventviewlist {
    String eventpic;
    String event_name;
    String event_url;
    String eventId;

    public geteventviewlist() {
    }

    public String getEventpic() {
        return eventpic;
    }

    public void setEventpic(String eventpic) {
        this.eventpic = eventpic;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public geteventviewlist(String eventpic, String event_name, String event_url, String eventId) {
        this.eventpic = eventpic;
        this.event_name = event_name;
        this.event_url = event_url;
        this.eventId = eventId;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_url() {
        return event_url;
    }

    public void setEvent_url(String event_url) {
        this.event_url = event_url;
    }
}
