package com.example.bottomnavigation.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class CategoryId implements Parcelable {
    private Integer id;
    private String title;
public CategoryId(){}
    protected CategoryId(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CategoryId> CREATOR = new Creator<CategoryId>() {
        @Override
        public CategoryId createFromParcel(Parcel in) {
            return new CategoryId(in);
        }

        @Override
        public CategoryId[] newArray(int size) {
            return new CategoryId[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
