package com.example.listenerinaidl22072702;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerService extends Service {
    public final String TAG = this.getClass().getName().toString();
    private Map<Integer,CallbackListener> callbackListenerMap = new HashMap<>();

    public ServerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"bindService启动onCreate方法");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"bindService启动onDestroy方法");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"bindService启动onBind方法");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"bindService启动onUnbind方法");
        return super.onUnbind(intent);
    }

    IBinder iBinder = new IMyAidlInterface.Stub() {

        @Override
        public String update(String str) throws RemoteException {
            Log.i(TAG,"收到了来自客户端的消息："+str);
            return "收到了来自客户端的消息："+str;
        }

        @Override
        public void registerCallBack(CallbackListener inter) throws RemoteException {
            System.out.println("map的key值："+callbackListenerMap.keySet().size());
            Log.i(TAG," register Call back listener "+inter);
            callbackListenerMap.put(callbackListenerMap.keySet().size(),inter);
        }

        @Override
        public void requstIMyAidlInterface(int id) throws RemoteException {
            CallbackListener callbackListener = callbackListenerMap.get(id);

        }

    };
}