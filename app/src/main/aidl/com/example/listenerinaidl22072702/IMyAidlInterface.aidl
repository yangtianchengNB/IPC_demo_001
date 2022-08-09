// IMyAidlInterface.aidl
package com.example.listenerinaidl22072702;

import com.example.listenerinaidl22072702.CallbackListener;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

    String update(String str);

    void registerCallBack(CallbackListener inter);

    void requstIMyAidlInterface(int id);
}