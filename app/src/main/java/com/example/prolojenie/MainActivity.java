package com.example.prolojenie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new BlankFragmentAdapter(this));
        viewPager2.setUserInputEnabled(false);
        new TabLayoutMediator((TabLayout) findViewById(R.id.tabLayout), viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String title;
                switch (position) {
                    case 0:
                        title = "Новое";
                        break;
                    case 1:
                        title = "В тренде";
                        break;
                    default:
                        title = "Для меня";
                        break;
                }
                tab.setText(title);
            }
        }).attach();
    }

    private class BlankFragmentAdapter extends FragmentStateAdapter {

        public BlankFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            String title;
            switch (position) {
                case 0:
                    title = "new";
                    break;
                case 1:
                    title = "inTrend";
                    break;
                default:
                    title = "forMe";
                    break;
            }
            return new BlankFragment(title);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}