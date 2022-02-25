package com.firstapp.arthub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.firstapp.arthub.drawing_fragments.drawing_firstfragment;
import com.firstapp.arthub.drawing_fragments.drawing_secondfragment;
import com.firstapp.arthub.drawing_fragments.drawing_thirdfragment;
import com.firstapp.arthub.photography_fragments.photography_first;
import com.firstapp.arthub.photography_fragments.photography_second;
import com.firstapp.arthub.photography_fragments.photography_third;

public class FragmentAdapterPhotography extends FragmentStateAdapter {
    public FragmentAdapterPhotography(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1 :
                return new photography_second();
            case 2 :
                return new photography_third();
        }

        return new photography_first();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

