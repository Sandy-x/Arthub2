package com.firstapp.arthub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.firstapp.arthub.colorpencil_fragments.colorpencil_first;
import com.firstapp.arthub.colorpencil_fragments.colorpencil_second;
import com.firstapp.arthub.colorpencil_fragments.colorpencil_third;
import com.firstapp.arthub.drawing_fragments.drawing_firstfragment;
import com.firstapp.arthub.drawing_fragments.drawing_secondfragment;
import com.firstapp.arthub.drawing_fragments.drawing_thirdfragment;

public class FragmentAdapterColorpencil extends FragmentStateAdapter {
    public FragmentAdapterColorpencil(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1 :
                return new colorpencil_second();
            case 2 :
                return new colorpencil_third();
        }

        return new colorpencil_first();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
