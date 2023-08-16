package com.example.datktph26567_fn_mob304.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Giay implements Parcelable {
    private String id;
    private String name;
    private String hangSx;
    private String loai;
    private String size;
    private String moTa;

    public Giay(String id, String name, String hangSx, String loai, String size, String moTa) {
        this.id = id;
        this.name = name;
        this.hangSx = hangSx;
        this.loai = loai;
        this.size = size;
        this.moTa = moTa;
    }

    public Giay() {
    }
    protected Giay(Parcel in) {
        id = in.readString();
        name = in.readString();
        hangSx = in.readString();
        loai = in.readString();
        size = in.readString();
        moTa = in.readString();
    }

    public static final Creator<Giay> CREATOR = new Creator<Giay>() {
        @Override
        public Giay createFromParcel(Parcel in) {
            return new Giay(in);
        }

        @Override
        public Giay[] newArray(int size) {
            return new Giay[size];
        }
    };

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

    public String getHangSx() {
        return hangSx;
    }

    public void setHangSx(String hangSx) {
        this.hangSx = hangSx;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(hangSx);
        dest.writeString(loai);
        dest.writeString(size);
        dest.writeString(moTa);
    }
}
