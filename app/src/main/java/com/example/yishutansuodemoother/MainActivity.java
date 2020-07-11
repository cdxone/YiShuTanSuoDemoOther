package com.example.yishutansuodemoother;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.yishutansuodemo.IMyAidl;
import com.example.yishutansuodemo.zhang_2.bean.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private IMyAidl iMyAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("myservice");
                intent.setPackage("com.example.yishutansuodemo");
                bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iMyAidl.addPerson(new Person(11,"张三"));
                    List<Person> personList = iMyAidl.getPersonList();
                    Log.e(TAG,"数据个数：" + personList.size());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    ServiceConnection mServiceConnection =  new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
