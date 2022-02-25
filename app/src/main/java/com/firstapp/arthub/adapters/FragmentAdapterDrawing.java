package com.firstapp.arthub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.firstapp.arthub.drawing_fragments.drawing_firstfragment;
import com.firstapp.arthub.drawing_fragments.drawing_secondfragment;
import com.firstapp.arthub.drawing_fragments.drawing_thirdfragment;

public class FragmentAdapterDrawing extends FragmentStateAdapter {
    public FragmentAdapterDrawing(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1 :
                return new drawing_secondfragment();
            case 2:
                return new drawing_thirdfragment();
        }

        return new drawing_firstfragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
