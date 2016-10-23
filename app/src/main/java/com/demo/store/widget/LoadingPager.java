package com.demo.store.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.demo.store.R;
import com.demo.store.manager.ThreadPoolManager;
import com.demo.store.utils.UIUtils;

/**
 * Created by GSJ
 * Date: 2016/10/22
 * Time: 15:38
 */
public abstract class LoadingPager extends FrameLayout {
    /**
     * 根据状态进行编程
     */
    // 默认的状态
    private static final int STATE_DEFAULTE = 1;
    // 加载状态
    private static final int STATE_LOADING = 2;
    // 错误状态
    private static final int STATE_ERROR = 3;
    // 加载成功。但是服务器没有任何数据。空的状态
    private static final int STATE_EMPTY = 4;
    // 加载成功的状态
    private static final int STATE_SUCCEED = 5;

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSucceedView;

    // 初始化默认的状态
    private int mState;
    public LoadingPager(Context context) {
        super(context);
        init();
    }


    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mState=STATE_DEFAULTE;
        mLoadingView = createLoadingView();
        if (null != mLoadingView) {
            addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }

        mErrorView = createErrorView();
        if (null != mErrorView) {
            addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }

        mEmptyView = createEmptyView();
        if (null != mEmptyView) {
            addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }
        // 显示对应的View
        showPageSafe();
    }

    private void showPageSafe() {
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }


        });
    }

    private void showPage() {
        if (null != mLoadingView) {
            mLoadingView.setVisibility(mState == STATE_DEFAULTE
                    || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != mErrorView) {
            mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (null != mEmptyView) {
            mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE
                    : View.INVISIBLE);
        }

        if (mState == STATE_SUCCEED && mSucceedView == null) {
            mSucceedView = createSuccessView();
            addView(mSucceedView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }

        if (null != mSucceedView) {
            mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE
                    : View.INVISIBLE);
        }
    }

    protected View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page_loading);
    }

    protected View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    protected View createErrorView() {
        View view = UIUtils.inflate(R.layout.loading_page_error);
        view.findViewById(R.id.page_bt).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        return view;
    }
    public abstract View createSuccessView();
   public void show(){
       if (mState == STATE_ERROR || mState == STATE_EMPTY) {
           mState = STATE_DEFAULTE;

       }

       if (mState == STATE_LOADING || mState == STATE_DEFAULTE) {
           mState = STATE_LOADING;
             Task task=new Task();
           ThreadPoolManager.getShortPool().execute(task);
       }

      showPageSafe();

   }
    public class Task implements Runnable{

        @Override
        public void run() {
            LoadResult result=  load();
            mState = result.getValue();
            UIUtils.runInMainThread(new Runnable() {
                @Override
                public void run() {
                    showPage();
                }
            });

        }
    }

    protected abstract LoadResult load();

    public enum LoadResult{
        STATE_ERROR(3), STATE_EMPTY(4), STATE_SUCEESS(5);

        int value;
        LoadResult(int value) {
            this.value = value;
        }


        public int getValue(){
          return  value;
        }
    }
}
