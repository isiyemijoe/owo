
package com.joe.owo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import models.Donation;


public class Dashboard extends AppCompatActivity {

    private Toolbar toolbar;

    private Profile profileFragment;
    private Projects projectsFragment;
    private Donations donationsfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        profileFragment = new Profile();
        projectsFragment = new Projects();
        donationsfragment = new Donations();
        tabLayout.setupWithViewPager(viewPager);
        
        ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
 viewpageradapter.addFragment(profileFragment, "Profile");
        viewpageradapter.addFragment(projectsFragment, "Projects");
        viewpageradapter.addFragment(donationsfragment, "Donations");


  viewPager.setAdapter(viewpageradapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String>  title = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String stitle){
            fragments.add(fragment);
            title.add(stitle);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
