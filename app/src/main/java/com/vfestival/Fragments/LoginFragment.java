package com.vfestival.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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
    private Button loginBtn, backToRegister, forgottenPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.login_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        emailInput = v.findViewById(R.id.emailInput);
        passInput = v.findViewById(R.id.passInput);
        loginBtn = v.findViewById(R.id.btn_login);
        backToRegister = v.findViewById(R.id.btn_register);
        forgottenPassword = v.findViewById(R.id.btn_forgotten_password);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        forgottenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction ft = fragmentManager2.beginTransaction();
                ft.replace(R.id.fragment_view, new ForgottenPasswordFragment());
                ft.commit();
            }
        });

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
                                    Toast.makeText(getActivity(), "Login Successful!",
                                            Toast.LENGTH_SHORT).show();
                                    FragmentManager fragmentManager2 = getFragmentManager();
                                    FragmentTransaction ft = fragmentManager2.beginTransaction();
                                    ft.replace(R.id.fragment_view, new LineUpFragment());
                                    ft.commit();
                                } else {
                                    if (password.length() < 6) {
                                        passInput.setError(getString(R.string.minimum_password));
                                    } else {
                                        Snackbar snackbar = Snackbar.make(getView(),  getString(R.string.auth_failed), Snackbar.LENGTH_SHORT);
                                        snackbar.show();

                                    }
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
