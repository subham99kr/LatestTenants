package com.example.latesttenants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class ParentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bnView;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        bnView=findViewById(R.id.bottom_navigationView);
        bnView.setBackground(null);

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();

                if(itemID==R.id.searchView){
                    openFragment(new SearchFragment() );
                     return true;
                }
                else if(itemID==R.id.bookmarks){
                    openFragment(new bookmarksFragment());
                     return true;
                }
                else if(itemID==R.id.account){
                openFragment(new AccountFragment());
                    return true;
                }
                else if(itemID==R.id.hotels){
                    openFragment(new HotelFragment());
                    return true;
                }
                return true;
            }
        });

        fragmentManager = getSupportFragmentManager();
        openFragment(new SearchFragment());

    }

    private void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemID= item.getItemId();

        if(itemID==R.id.searchView)
            openFragment(new SearchFragment() );
        else if(itemID==R.id.bookmarks)
            openFragment(new bookmarksFragment());
        else if(itemID==R.id.account)
            openFragment(new AccountFragment());
        else if(itemID==R.id.hotels)
            openFragment(new HotelFragment());
        return true;
    }
}