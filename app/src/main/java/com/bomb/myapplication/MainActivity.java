package com.bomb.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import static android.content.ContentValues.TAG;


public class MainActivity extends Activity {


   public TextView text,text1;
    private SmsReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) this.findViewById(R.id.text);
        text1 = (TextView) this.findViewById(R.id.text1);
        text.setText("bomb1");
        System.out.println("bomb2");

        smsReceiver = new SmsReceiver();
        Intent intent = new Intent();
        intent.setAction("android.provider.Telephony.SMS_RECEIVED");
        //监听短信
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

       MainActivity.this.registerReceiver(smsReceiver, filter);
       // MainActivity.this.sendBroadcast(intent);

    }
    public class SmsReceiver extends BroadcastReceiver {
        public SmsReceiver(){

        }
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            text1.setText("receiver message");
            Bundle bundle = intent.getExtras();
            Object[] object = (Object[]) bundle.get("pdus");
            SmsMessage sms[] = new SmsMessage[object.length];
            for (int i = 0; i < object.length; i++) {
                sms[0] = SmsMessage.createFromPdu((byte[]) object[i]);
                text1.setText(sms[i].getDisplayMessageBody());
                //Toast.makeText(context, "来自" + sms[i].getDisplayOriginatingAddress() + " 的消息是：" + sms[i].getDisplayMessageBody(), Toast.LENGTH_SHORT).show();
            }

            //终止广播，在这里我们可以稍微处理，根据用户输入的号码可以实现短信防火墙。
//        abortBroadcast();
        }
    }

}

