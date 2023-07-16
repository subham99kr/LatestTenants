package com.example.latesttenants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class AccountFragment extends Fragment {

    Button btnLogOut;
    Activity context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
           View view = inflater.inflate(R.layout.fragment_account, container, false);

        btnLogOut = view.findViewById(R.id.logOut);
        context = getActivity();

           btnLogOut.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   FirebaseAuth.getInstance().signOut();
                   Intent intent = new Intent(context, MainActivity.class);
                   startActivity(intent);
               }
           });


           return view;
    }
}