
package com.abdymalikmulky.abdroidmvp.app.data.news.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Berita extends RealmObject {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("ikhtisar")
    @Expose
    private String ikhtisar;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("gambar_thumb")
    @Expose
    private String gambarThumb;
    @SerializedName("detail_url")
    @Expose
    private String detailUrl;
    @SerializedName("dilihat")
    @Expose
    private String dilihat;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIkhtisar() {
        return ikhtisar;
    }

    public void setIkhtisar(String ikhtisar) {
        this.ikhtisar = ikhtisar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGambarThumb() {
        return gambarThumb;
    }

    public void setGambarThumb(String gambarThumb) {
        this.gambarThumb = gambarThumb;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getDilihat() {
        return dilihat;
    }

    public void setDilihat(String dilihat) {
        this.dilihat = dilihat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }


    @Override
    public String toString() {
        return "Berita{" +
                "id='" + id + '\'' +
                ", judul='" + judul + '\'' +
                ", ikhtisar='" + ikhtisar + '\'' +
                ", gambar='" + gambar + '\'' +
                ", gambarThumb='" + gambarThumb + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", dilihat='" + dilihat + '\'' +
                ", tanggal='" + tanggal + '\'' +
                '}';
    }
}
