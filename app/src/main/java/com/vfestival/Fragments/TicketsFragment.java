package com.vfestival.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
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

public class TicketsFragment extends Fragment {

    final int SEND_SMS_PERMISSION_REQUEST_CODE =1;
    Button register;
    EditText nameInput, phoneInput, emailInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.tickets_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        register = view.findViewById(R.id.RegisterBtn);
        nameInput = view.findViewById(R.id.NameInput);
        phoneInput = view.findViewById(R.id.PhoneNumInput);
        emailInput = view.findViewById(R.id.EmailInput);

        register.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            register.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSend(v);
            }
        });

    }
    public void onSend(View v){
        String phoneNumber = phoneInput.getText().toString();
        String name = nameInput.getText().toString();
        String smsMessage = "Hi, " + name + " Your tickets have successfully been booked for V Fest, see you soon!";

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
