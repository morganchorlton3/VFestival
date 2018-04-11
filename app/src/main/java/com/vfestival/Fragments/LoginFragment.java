package com.vfestival.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vfestival.MainActivity;
import com.vfestival.R;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText emailInput, passInput;
    private Button loginBtn, backToRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.login_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        emailInput = v.findViewById(R.id.emailInput);
        passInput = v.findViewById(R.id.passInput);
        loginBtn = v.findViewById(R.id.btn_login);
        backToRegister = v.findViewById(R.id.btn_register);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        backToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction ft = fragmentManager2.beginTransaction();
                ft.replace(R.id.fragment_view, new RegisterFragment());
                ft.commit();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                final String password = passInput.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    MainActivity.updateUI(user);
                                    FragmentManager fragmentManager2 = getFragmentManager();
                                    FragmentTransaction ft = fragmentManager2.beginTransaction();
                                    ft.replace(R.id.fragment_view, new LineUpFragment());
                                    ft.commit();
                                    Toast.makeText(getActivity(), "Login Successful!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    MainActivity.updateUI(null);
                                }
                            }
                        });
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        MainActivity.updateUI(user);
    }
}
