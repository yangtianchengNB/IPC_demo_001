package com.example.listenerinaidl22072702;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class ClientService extends Service{
    public final String TAG = this.getClass().getName().toString();
    IMyAidlInterface iMyAidlInterface;
    private Messenger mService;

    public ClientService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"startService的onCreate启动");
        bindService(new Intent(this,ServerService.class),conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"startService的onStartCommand启动");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"startService的onDestroy启动");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    CallbackListener callbackListener = new CallbackListener.Stub() {
        @Override
        public void onCallBack() throws RemoteException {
            Log.i(TAG, "onCallBack");
        }
    };

    Handler mHanler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what ==1){//注册
                try {
                    iMyAidlInterface.registerCallBack(callbackListener);
                } catch (RemoteException e) {
                    Log.i(TAG, "error is + error ",e);
                    e.printStackTrace();
                }
            }else if (msg.what == 2){//发送
                try {
                    iMyAidlInterface.requstIMyAidlInterface(0);
                } catch (RemoteException e) {
                    Log.i(TAG, "error is + error ",e);
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    };



    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG,"具体的业务对象："+iBinder);
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            Message msg = Message.obtain(null,1);
            mHanler.handleMessage(msg);
            Message msg1 = Message.obtain(null,2);
            mHanler.handleMessage(msg1);
//            String lixiang = null;
//            try {
//                lixiang = iMyAidlInterface.update("lixiang");
//                 /***** 1 代表注册  2，代表发送
//                 sendmeessage(1)
//
////
////                CallbackListener callbackListener = ClientService.this.iMyAidlInterface.getIMyAidlInterface(0);
////                Log.i(TAG,"获取来自客户端回调的数据"+callbackListener.getClass());
//                Log.i(TAG,"收到了来自服务端的消息："+lixiang);
//            } catch (RemoteException e) {
//                Log.i(TAG, "error is + error ",e);
//                e.printStackTrace();
//            }

//            mService = new Messenger(iBinder);
//            Message msg = Message.obtain(null,1);
//            Bundle data = new Bundle();
//            data.putParcelable("msg1", new MyListener(){
//                @Override
//                public int describeContents() {
//                    return 0;
//                }
//
//                @Override
//                public void writeToParcel(Parcel parcel, int i) {
//
//                }
//
//                @Override
//                public String update(String str) {
//                    Log.i(TAG,"收到了回调:\""+str+"\"");
//                    return str;
//                }
//            });
//            msg.setData(data);
//            try {
//                mService.send(msg);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}