package com.abdymalikmulky.abdroidmvp.app.news.data;

/**
 * Created by abdymalikmulky on 1/17/17.
 */

public class News {
    int id;
    String title;
    String summary;
    String date;

    public News(int id, String title, String summary, String date) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
