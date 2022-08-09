package com.example.listenerinaidl22072702;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ClientService extends Service{
    public final String TAG = this.getClass().getName().toString();
    IMyAidlInterface iMyAidlInterface;
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



    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG,"具体的业务对象："+iBinder);
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);

            String lixiang = null;
            try {
                lixiang = iMyAidlInterface.update("lixiang");


                iMyAidlInterface.registerCallBack(new CallbackListener.Stub() {
                    @Override
                    public void onCallBack() throws RemoteException {
                        Log.i(TAG, "onCallBack");
                    }
                });
//                iMyAidlInterface.requstIMyAidlInterface(0);
//                CallbackListener callbackListener = ClientService.this.iMyAidlInterface.getIMyAidlInterface(0);
//                Log.i(TAG,"获取来自客户端回调的数据"+callbackListener.getClass());
                Log.i(TAG,"收到了来自服务端的消息："+lixiang);
            } catch (RemoteException e) {
                Log.i(TAG, "error is + error ",e);
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}