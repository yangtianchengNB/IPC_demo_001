package com.example.listenerinaidl22072702;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerService extends Service {
    public static final String TAG = ServerService.class.getName();
    private Map<Integer,CallbackListener> callbackListenerMap = new HashMap<>();
    private static final RemoteCallbackList<CallbackListener> client = new RemoteCallbackList<>();

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
//        return new Messenger(new MessengerHandler(/*Looper.myLooper()*/)).getBinder();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"bindService启动onUnbind方法");
        return super.onUnbind(intent);
    }

//    private static class MessengerHandler extends Handler{
//
////        public MessengerHandler(@NonNull Looper looper) {
////            super(Looper.myLooper());
////        }
//
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            if (msg.what == 1){//代表注册
//
//                client.register(msg.getData().getParcelable("msg1"));
//
////                Log.i("msg",msg.toString());
////                Log.i("msg.getData()",msg.getData().toString());
////                RemoteCallbackList
////                MyListener listener = msg.getData().getParcelable("msg");
////                Log.i("ServerService",msg.getData().getString("msg"));
////                listener.update("服务器："+ServerService.TAG+"发来回调");
//            }else if(msg.what == 2){//代表发送
//                Log.i("msg.getData()",msg.getData().getParcelable("msg1"));
//            }else {
//                Log.i("ServerService","没收着");
//            }
//        }
//    }

    IBinder iBinder = new IMyAidlInterface.Stub() {

        @Override
        public String update(String str) throws RemoteException {
            Log.i(TAG,"收到了来自客户端的消息："+str);
            return "收到了来自客户端的消息："+str;
        }

        @Override
        public void registerCallBack(CallbackListener inter) throws RemoteException {
            Log.i(TAG,"收到注册的对象:"+inter);
            client.register(inter);
        }

        @Override
        public void requstIMyAidlInterface(int id) throws RemoteException {
            Log.i(TAG,"获取第"+id+"个Listener");
            int i = client.beginBroadcast();
            for (int a = 0; a < i; a++) {
                Log.i(TAG,"获取对象："+client.getBroadcastItem(a));
            }
            client.finishBroadcast();
        }

    };
}