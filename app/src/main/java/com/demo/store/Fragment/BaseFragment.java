package com.demo.store.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.store.widget.LoadingPager;

/**
 * Created by GSJ
 * Date: 2016/10/22
 * Time: 13:09
 */
public abstract class BaseFragment extends Fragment{

    private LoadingPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pager = new LoadingPager(getActivity()) {
            @Override
            public View createSuccessView() {

                return BaseFragment.this.createSuccessView();
            }

            @Override
            protected LoadResult load() {
                return BaseFragment.this.load();
            }
        };
        return pager;
    }

    protected abstract LoadingPager.LoadResult load();

    protected abstract View createSuccessView();

    public void show(){
        if(pager!=null){
             pager.show();
        }
    }
}
