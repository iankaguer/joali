package com.AZtech_labs.joali;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.AZtech_labs.joali.adapters.ScreenSlidePagerAdapter;
import com.AZtech_labs.joali.fragments.AroundFragment;
import com.AZtech_labs.joali.fragments.GlobeFragment;
import com.AZtech_labs.joali.fragments.PrivilegieFragment;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    ViewPager viewPager;
    BubbleNavigationLinearView equal_navigation_bar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.main_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        viewPager = view.findViewById(R.id.containers);
        equal_navigation_bar = view.findViewById(R.id.equal_navigation_bar);
        initUI();

      /*  try {
            BackgroundHome.run(getActivity());

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }



   /* private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }*/

    //////////////////////Menu Drawer Navigation////////////////////////////////

     @SuppressLint("ClickableViewAccessibility")
     void initUI(){
         ArrayList<Fragment> fragList = new  ArrayList<Fragment>();
         fragList.add(new AroundFragment());
         fragList.add(new PrivilegieFragment());
         fragList.add(new GlobeFragment());
         FragmentManager fragmentManager = getFragmentManager();
         //ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(fragmentManager, fragList);
         FragmentPagerAdapter fpAdapter = new FragmentPagerAdapter(fragmentManager) {
             @Override
             public Fragment getItem(int position) {
                 if (position >= 0 && position < fragList.size()){
                     return fragList.get(position);
                 }

                 return fragList.get(1);
             }

             @Override
             public int getCount() {
                 return fragList.size();
             }
         };
         viewPager.setAdapter(fpAdapter);
         viewPager.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 v.animate();
                 return true;
             }
         });
         equal_navigation_bar.setNavigationChangeListener((view, position) -> viewPager.setCurrentItem(position,true));



         //change the initial activate element
         int newInitialPosition = 1;
         equal_navigation_bar.setCurrentActiveItem(newInitialPosition);
         viewPager.setCurrentItem(newInitialPosition, false);

    }

}
