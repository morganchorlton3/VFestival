package com.vfestival.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vfestival.MainActivity;
import com.vfestival.R;

import static android.content.ContentValues.TAG;

public class ForgottenPasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
    String email;
    ScrollView forgotten_layout;
    EditText emailInput;
    Button resetPasswordBtn, forgotBackBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.forgoten_password_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        emailInput = v.findViewById(R.id.emailInput);
        resetPasswordBtn = v.findViewById(R.id.resetPasswordbtn);
        forgotBackBtn = v.findViewById(R.id.forgotBackBtn);
        forgotten_layout = v.findViewById(R.id.forgotten_layout);

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword(emailInput.getText().toString());
            }
        });

        forgotBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction ft = fragmentManager2.beginTransaction();
                ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                ft.replace(R.id.fragment_view, new LoginFragment());
                ft.commit();
            }
        });


        return v;
    }

    private void resetPassword(final String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Snackbar snackbar = Snackbar.make(forgotten_layout, "We have sent a password reset email to " + email, Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }else{
                            Snackbar snackbar = Snackbar.make(forgotten_layout, "We couldn't send an email to " + email + ". Please try again later", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        MainActivity.updateUI(user);
    }
}
