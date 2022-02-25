package com.firstapp.arthub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.firstapp.arthub.digitalart_fragment.digitalart_first;
import com.firstapp.arthub.digitalart_fragment.digitalart_second;
import com.firstapp.arthub.digitalart_fragment.digitalart_third;
import com.firstapp.arthub.drawing_fragments.drawing_firstfragment;
import com.firstapp.arthub.drawing_fragments.drawing_secondfragment;
import com.firstapp.arthub.drawing_fragments.drawing_thirdfragment;

public class FragmentAdapterDigitalArt extends FragmentStateAdapter {

    public FragmentAdapterDigitalArt(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1 :
                return new digitalart_second();
            case 2 :
                return new digitalart_third();

        }

        return new digitalart_first();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}


