package com.example.listenerinaidl22072702;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public interface MyListener extends Parcelable {

    String update(String str);

    @Override
    void writeToParcel(Parcel parcel, int i);

    @Override
    int describeContents();
}
