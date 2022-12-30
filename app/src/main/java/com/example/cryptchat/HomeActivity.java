package com.example.cryptchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerMessangerAdapter viewPagerMessangerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);

        viewPagerMessangerAdapter = new ViewPagerMessangerAdapter(getSupportFragmentManager(), getLifecycle());


        viewPager2.setAdapter(viewPagerMessangerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position == 0){
                tab.setText("One to One Chats");
            }else {
                tab.setText("Group Chats");
            }

        }).attach();


        System.out.println("************************************************");
        System.out.println("On the Activity 2");
        System.out.println("************************************************");

    }
}