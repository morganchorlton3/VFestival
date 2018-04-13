package com.vfestival.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vfestival.MainActivity;
import com.vfestival.R;

import org.w3c.dom.Text;

public class AccountFragment extends Fragment {

    TextView account_welcome;
    Button changePasswordBtn, changeEmailBtn, deleteAccountbtn, signOutbtn;
    ScrollView account_layout;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.account_fragment, container, false);
        account_welcome = v.findViewById(R.id.account_welcome);
        changePasswordBtn = v.findViewById(R.id.changePasswordBtn);
        changeEmailBtn = v.findViewById(R.id.changeEmailBtn);
        signOutbtn = v.findViewById(R.id.signOutBtn);
        deleteAccountbtn = v.findViewById(R.id.deleteAccountBtn);
        account_layout = v.findViewById(R.id.account_layout);


        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        account_welcome.setText("Hi, " + user.getEmail().toString());

        changeEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction ft = fragmentManager2.beginTransaction();
                ft.replace(R.id.fragment_view, new ChangeEmailFragment());
                ft.commit();
            }
        });

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction ft = fragmentManager2.beginTransaction();
                ft.replace(R.id.fragment_view, new ChangePasswordFragment());
                ft.commit();
            }
        });

        deleteAccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.alertDialog);
                builder.setMessage(R.string.delete_account_confirmation)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes_delete_account, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                user.delete()
                                        .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Snackbar snackbar = Snackbar.make(account_layout, "Your account has been deleted!", Snackbar.LENGTH_LONG);
                                                    snackbar.show();
                                                }else{
                                                    Snackbar snackbar = Snackbar.make(account_layout, "We couldn't delete your account, please try again later", Snackbar.LENGTH_LONG);
                                                    snackbar.show();
                                                }
                                            }
                                        });
                                signOut();
                            }
                        })
                        .setNegativeButton(R.string.no_delete_account, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Snackbar snackbar = Snackbar.make(account_layout, "We have not deleted your account", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        signOutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        return v;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Simple Dialog");
        builder.setMessage("Some message here");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Posetive");
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Negative");
            }
        });

        return builder.create();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
