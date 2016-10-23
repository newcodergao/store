package com.demo.store;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.store.Fragment.BaseFragment;
import com.demo.store.Fragment.FragmentFactory;
import com.demo.store.utils.UIUtils;
import com.demo.store.widget.PagerTab;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private PagerTab mPagerTab;
    private ViewPager mViewPager;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mPagerTab = (PagerTab) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mPagerTab.setViewPager(mViewPager);
        mPagerTab.setOnPageChangeListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setVisibility(View.VISIBLE);
        toolbar.setBackgroundColor(Color.parseColor("#3f51b5"));

        toolbar.setTitle("练习");
        toolbar.setNavigationIcon(R.mipmap.ic_exit);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(MainActivity.this , "点击了111" , Toast.LENGTH_SHORT).show();


                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(MainActivity.this , "点击了222"  , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(MainActivity.this , "点击了333"  , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(MainActivity.this , "点击了444" , Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this , "点击了哈哈" , Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BaseFragment fragment = FragmentFactory.createFragment(position);
        fragment.show();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MainAdapter extends FragmentPagerAdapter{

        private final String[] tabNames;

        public MainAdapter(FragmentManager fm) {
            super(fm);
            tabNames = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return super.isViewFromObject(view, object);
        }

    }
}
