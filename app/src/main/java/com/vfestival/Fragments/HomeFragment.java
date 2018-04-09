package com.vfestival.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vfestival.R;

public class HomeFragment extends Fragment {

    final int SEND_SMS_PERMISSION_REQUEST_CODE =1;

    Button sendText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.home_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendText = view.findViewById(R.id.sendText);

        sendText.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            sendText.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
        sendText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSend(v);
            }
        });
    }
    public void onSend(View v){
        String phoneNumber = "5554";
        String smsMessage = "This is a test message from VFest";

        if(phoneNumber == null || phoneNumber.length() == 0 ||
                smsMessage == null || smsMessage.length() == 0){
            return;
        }

        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
            Toast.makeText(getContext(), "Message Sent!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(getContext(), permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}
