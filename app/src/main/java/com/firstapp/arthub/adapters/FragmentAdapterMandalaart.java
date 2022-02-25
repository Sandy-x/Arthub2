package com.firstapp.arthub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.firstapp.arthub.Mandala_fragments.mandala_first;
import com.firstapp.arthub.Mandala_fragments.mandala_second;
import com.firstapp.arthub.Mandala_fragments.mandala_third;

public class FragmentAdapterMandalaart extends FragmentStateAdapter {
    public FragmentAdapterMandalaart(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1 :
                return new mandala_second();
            case 2 :
                return new mandala_third();

        }

        return new mandala_first();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
