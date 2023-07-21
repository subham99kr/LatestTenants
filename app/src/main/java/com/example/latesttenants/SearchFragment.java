package com.example.latesttenants;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterHouses adapterHouses;
    DatabaseReference housesRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleHouses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        housesRef = FirebaseDatabase.getInstance().getReference().child("users");

        housesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<AllHouseFetchModel> houseList = new ArrayList<>();

                for(DataSnapshot userSnapshot : snapshot.getChildren()){
                    DataSnapshot housesSnapshot = userSnapshot.child("houses");
                    for (DataSnapshot houseSnapshot : housesSnapshot.getChildren()){
                        AllHouseFetchModel house = houseSnapshot.getValue(AllHouseFetchModel.class);
                        houseList.add(house);
                    }

                }

                adapterHouses=new AdapterHouses(houseList);
                recyclerView.setAdapter(adapterHouses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
}


/* FirebaseRecyclerOptions<AllHouseFetchModel> options=
                new FirebaseRecyclerOptions.Builder<AllHouseFetchModel>()
                        .setQuery(housesRef.child("houses"), AllHouseFetchModel.class)
                        .build();
*/