package com.example.myapplication;

public class getdashboard {
    int Title_img;
    String Title_Text;

    public getdashboard(int title_img, String title_Text) {
        Title_img = title_img;
        Title_Text = title_Text;
    }

    public int getTitle_img() {
        return Title_img;
    }

    public void setTitle_img(int title_img) {
        Title_img = title_img;
    }

    public String getTitle_Text() {
        return Title_Text;
    }

    public void setTitle_Text(String title_Text) {
        Title_Text = title_Text;
    }
}
