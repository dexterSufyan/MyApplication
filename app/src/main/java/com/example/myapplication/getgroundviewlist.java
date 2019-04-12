package com.example.myapplication;

public class getgroundviewlist {
    int groundpic;
    String groundname;
    String groundaddress;

    public getgroundviewlist(int groundpic, String groundname, String groundaddress) {
        this.groundpic = groundpic;
        this.groundname = groundname;
        this.groundaddress = groundaddress;
    }

    public int getGroundpic() {
        return groundpic;
    }

    public void setGroundpic(int groundpic) {
        this.groundpic = groundpic;
    }

    public String getGroundname() {
        return groundname;
    }

    public void setGroundname(String groundname) {
        this.groundname = groundname;
    }

    public String getGroundaddress() {
        return groundaddress;
    }

    public void setGroundaddress(String groundaddress) {
        this.groundaddress = groundaddress;
    }
}
