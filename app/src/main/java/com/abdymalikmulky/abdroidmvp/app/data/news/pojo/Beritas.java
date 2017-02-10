
package com.abdymalikmulky.abdroidmvp.app.data.news.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Beritas {

    @SerializedName("berita")
    @Expose
    private List<Berita> berita = null;

    public List<Berita> getBerita() {
        return berita;
    }

    public void setBerita(List<Berita> berita) {
        this.berita = berita;
    }


    @Override
    public String toString() {
        return "Beritas{" +
                "berita=" + berita +
                '}';
    }
}
