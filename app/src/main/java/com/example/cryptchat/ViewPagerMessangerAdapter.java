package com.example.cryptchat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerMessangerAdapter extends FragmentStateAdapter {

    public ViewPagerMessangerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {

        super(fragmentManager, lifecycle);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            Fragment fragment = new OneToOneChat();
            return fragment;
        }else{
            Fragment fragment = new GroupChat();
            return fragment;
        }
}

    @Override
    public int getItemCount() {
        return 2;
    }
}
