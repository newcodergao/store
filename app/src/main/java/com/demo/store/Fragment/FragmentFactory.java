package com.demo.store.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GSJ
 * Date: 2016/10/22
 * Time: 13:04
 */
public class FragmentFactory {
    private static final int TAB_HOME = 0;
    private static final int TAB_APP = 1;
    private static final int TAB_GAME = 2;
    private static final int TAB_SUBJECT = 3;
    private static final int TAB_RECOMMEND = 4;
    private static final int TAB_CATEGORY = 5;
    private static final int TAB_TOP = 6;
    private static Map<Integer,BaseFragment> mFragmentMap=new HashMap<>();
    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = mFragmentMap.get(index);
        if(fragment==null) {

            switch (index) {
                case TAB_HOME:
                    fragment = new HomeFragment();
                    break;
                case TAB_APP:
                    fragment = new AppFragment();
                    break;
                case TAB_GAME:
                    fragment = new GameFragment();
                    break;
                case TAB_SUBJECT:
                    fragment = new SubjectFragment();
                    break;
                case TAB_RECOMMEND:
                    fragment = new RecommentFragment();
                    break;
                case TAB_CATEGORY:
                    fragment = new CategoryFragment();
                    break;
                case TAB_TOP:
                    fragment = new HotFragment();
                    break;

            }
            mFragmentMap.put(index,fragment);

        }
        return fragment;

    }
}
