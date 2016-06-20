package com.tech.dota.pearl2016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class UniversalContainerActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_container);

        toolbar= (Toolbar) findViewById(R.id.container_toolbar);

        int i=getIntent().getIntExtra("frag",-1);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        switch(i){
            case 0:
                Fragment reachCampus=new ReachCampus();
                ft.add(R.id.guide_container,reachCampus).commit();
                toolbar.setTitle("Reach Campus");
                break;
            case 2:
                Fragment campusMapFragment=new CampusMapFragment();
                ft.add(R.id.guide_container,campusMapFragment).commit();
                toolbar.setVisibility(View.GONE);
                break;
            case 1:
                Fragment contactsFragment=new ContactsFragment();
                ft.add(R.id.guide_container,contactsFragment).commit();
                toolbar.setSubtitle("Swipe left/right for details");
                toolbar.setTitle("Contact Us");
                break;
            case 4:
                Fragment favouriteFragment=new FavouriteFragment();
                ft.add(R.id.guide_container,favouriteFragment).commit();
                toolbar.setTitle("Favourites");
                break;
            case 5:
                Fragment aboutFragment=new AboutFragment();
                ft.add(R.id.guide_container,aboutFragment).commit();
                toolbar.setTitle("About Us");
                break;
            case 6:
                Fragment creditsFragment=new CreditsFragment();
                ft.add(R.id.guide_container,creditsFragment).commit();
                toolbar.setSubtitle("Swipe left/right for details");
                toolbar.setTitle("App Credits");
                break;
        }
    }
}

