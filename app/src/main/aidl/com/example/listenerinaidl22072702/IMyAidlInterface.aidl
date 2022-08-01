// IMyAidlInterface.aidl
package com.example.listenerinaidl22072702;

import com.example.listenerinaidl22072702.CallbackListener;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String update(String str);

    void registerCallBack(CallbackListener inter);

    void requstIMyAidlInterface(int id);
}