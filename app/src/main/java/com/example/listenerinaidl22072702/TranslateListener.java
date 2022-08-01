package com.example.listenerinaidl22072702;

import android.os.IBinder;
import android.os.RemoteException;

public class TranslateListener implements CallbackListener{
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public String onCallBack() throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
