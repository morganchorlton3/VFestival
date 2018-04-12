package com.vfestival.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vfestival.R;

import java.util.Properties;

import static com.vfestival.R.array.days;
import static com.vfestival.R.array.quantity;

public class TicketsFragment extends Fragment {

    private FirebaseAuth mAuth;
    final int SEND_SMS_PERMISSION_REQUEST_CODE =1;
    Button register;
    EditText nameInput, phoneInput, emailInput;
    Spinner daySelector, QSelector;
    RadioButton fullWeekend, dayTicket, selectedRadioButton;
    String nameString, emailString, quantityString,  ticketTypeString;
    RadioGroup ticketType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.tickets_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            emailString = user.getEmail();
           // emailInput.setText(emailString);
        }
        register = view.findViewById(R.id.RegisterBtn);
        nameInput = view.findViewById(R.id.NameInput);
        phoneInput = view.findViewById(R.id.PhoneNumInput);
        emailInput = view.findViewById(R.id.EmailInput);
        daySelector = view.findViewById(R.id.DaySelector);
        QSelector = view.findViewById(R.id.QSelector);
        fullWeekend = view.findViewById(R.id.fullWeekend);
        dayTicket = view.findViewById(R.id.dayTicket);
        ticketType = view.findViewById(R.id.ticketType);

        ticketType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if( dayTicket.isChecked()){
                    daySelector.setVisibility(View.VISIBLE);
                }else{
                    daySelector.setVisibility(View.INVISIBLE);
                }
            }
        });

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(days));

        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySelector.setAdapter(dayAdapter);

        ArrayAdapter<String> QAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(quantity));

        QAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        QSelector.setAdapter(QAdapter);

        register.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            register.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int selectedId = ticketType.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                selectedRadioButton = v.findViewById(selectedId);

                if (dayTicket.isChecked()){
                    ticketTypeString = daySelector.getSelectedItem().toString() + " Ticket (s)";
                }else if (fullWeekend.isChecked()){
                    ticketTypeString = "Full Weekend Ticket (s)";
                }

                nameString = nameInput.getText().toString();
                quantityString = QSelector.getSelectedItem().toString();

                String message = "Hi " + nameString + ", your booking of " + quantityString + " " + ticketTypeString + " has been successful. Thank you for booking your tickets for V Festival 2018!";


                /*Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, emailString);
                intent.putExtra(Intent.EXTRA_SUBJECT, "V Festival Booking Confirmation");
                intent.putExtra(Intent.EXTRA_TEXT, message );
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, "Send Email");
                startActivity(chooser);*/

                onSend(v, message);
            }
        });

    }

    public void onSend(View v ,String message){
        String phoneNumber = phoneInput.getText().toString();

        if(phoneNumber == null || phoneNumber.length() == 0 ||
                message == null || message.length() == 0){
            return;
        }

        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getContext(), "Thank you for booking tickets, you will receive an email and a sms shortly to confirm your booking", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "An error occurred!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(getContext(), permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
