package com.example.truequelibre.ui.trueques;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.truequelibre.R;
import com.example.truequelibre.databinding.FragmentTruequesBinding;
import com.google.android.material.tabs.TabLayout;


public class TruequesFragment extends Fragment {

    private FragmentTruequesBinding binding;
    private ViewPager mViewPager;
    private ViewPagerAdapterTrueque mViewPagerAdapter;
    private TabLayout mTabLayout;
    private   View view;
    private Toolbar toolbar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = view.findViewById(R.id.Tabstruques);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        ViewPagerAdapterTrueque adapter = new ViewPagerAdapterTrueque(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.getTabAt(0).setText(adapter.getPageTitle(0));
        mTabLayout.getTabAt(1).setText(adapter.getPageTitle(1));
        mTabLayout.getTabAt(2).setText(adapter.getPageTitle(2));

    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
     /*   TruequesViewModel truequesViewModel =
                new ViewModelProvider(this).get(TruequesViewModel.class);

        binding = FragmentTruequesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        view = inflater.inflate(R.layout.fragment_trueques, container, false);

        toolbar= view.findViewById(R.id.toolbartruqeues);

       // final TextView textView = binding.textTrueques;
       // truequesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        setViewPager();
        return root;*/
        return inflater.inflate(R.layout.fragment_trueques, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setViewPager() {

      //  mViewPager = (ViewPager) view.findViewById(R.id.pager);
      //  mViewPagerAdapter = new ViewPagerAdapterTrueque(getParentFragmentManager());
      //  mViewPager.setAdapter(mViewPagerAdapter);
      //  mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
      //  mTabLayout.setupWithViewPager(mViewPager);

    }


}