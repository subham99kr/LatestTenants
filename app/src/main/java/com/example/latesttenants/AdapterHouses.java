package com.example.latesttenants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class AdapterHouses extends RecyclerView.Adapter<AdapterHouses.myViewHolder> {
    private List<AllHouseFetchModel> houseList;
    public AdapterHouses(@NonNull List<AllHouseFetchModel> houseList) {
        this.houseList = houseList;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        //call model instead of directly calling AllHouseFetchModel
        //if don't do so it will ask to make adapter class getter static which we don't want

        AllHouseFetchModel house = houseList.get(position);
        String city = house.getCity();
        String country = house.getState();
        String location = city + ", " + country;
        String rent = "Rs."+ house.getRent() +" /month";

        holder.titleTextView.setText(house.getTitle());
        holder.priceTextView.setText(rent);
        holder.locationTextView.setText(location);

        //Using glide to show image **make sure that image url ends with .jpg
        Glide.with(holder.imageView.getContext()).load(house.getImage()).into(holder.imageView);


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_search_houses,parent,false);
        return new myViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return houseList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView titleTextView, priceTextView,locationTextView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imageView);
            titleTextView= itemView.findViewById(R.id.titleTextView);
            priceTextView= itemView.findViewById(R.id.priceTextView);
            locationTextView= itemView.findViewById(R.id.locationTextView);
        }
    }

}
