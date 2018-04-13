package com.vfestival.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vfestival.MainActivity;
import com.vfestival.R;

public class ChangeEmailFragment extends Fragment {

    private FirebaseAuth mAuth;
    ScrollView forgotten_layout;
    EditText emailInputNew, emailInputConfirm, emailInputOld;
    Button updateEmailBtn, forgotBackBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.change_email_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        emailInputNew = v.findViewById(R.id.emailInputNew);
        emailInputConfirm = v.findViewById(R.id.emailInputConfirm);
        emailInputOld = v.findViewById(R.id.emailInputOld);
        updateEmailBtn = v.findViewById(R.id.updateEmailBtn);
        forgotBackBtn = v.findViewById(R.id.forgotBackBtn);
        forgotten_layout = v.findViewById(R.id.forgotten_layout);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        updateEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailInputOld.getText().toString().equals(user.getEmail().toString()) && emailInputNew.getText().toString().equals(emailInputConfirm.getText().toString())){
                    ChangeEmail(emailInputNew.getText().toString());
                }else if(!emailInputNew.toString().equals(emailInputConfirm.toString())){
                    emailInputConfirm.setError("Your Emails don't match!");
                }else if(emailInputNew.toString().equals(user.getEmail().toString())){
                    emailInputNew.setError("That email is already in use!");
                }
            }
        });

        forgotBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction ft = fragmentManager2.beginTransaction();
                ft.replace(R.id.fragment_view, new LoginFragment());
                ft.commit();
            }
        });


        return v;
    }

    private void ChangeEmail(final String email) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail(email)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"We have updated your email to " + email + ", please log back in with your new email to update the changes", Toast.LENGTH_SHORT).show();

                            signOut();
                        }else{
                            Toast.makeText(getContext(), "We couldn't update your email to " + email + ". Please try again later", Toast.LENGTH_SHORT).show();
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
    public void signOut() {
        mAuth.signOut();
        MainActivity.updateUI(null);
        Toast.makeText(getContext(), "You have been signed out", Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction ft = fragmentManager2.beginTransaction();
        ft.replace(R.id.fragment_view, new LineUpFragment());
        ft.commit();
    }
}
