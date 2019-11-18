package qd.com.qidianhuyu.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import butterknife.BindView;
import qd.com.library.base.fragment.BaseFragment;
import qd.com.qidianhuyu.R;
import qd.com.qidianhuyu.R2;
import qd.com.qidianhuyu.ali.utils.Common;
import qd.com.qidianhuyu.ali.utils.ScreenUtils;
import qd.com.qidianhuyu.ali.view.video.AlivcVideoPlayView;
import qd.com.qidianhuyu.ali.view.video.videolist.AlivcVideoListView;
import qd.com.qidianhuyu.component.DaggerMainVideoComponent;
import qd.com.qidianhuyu.component.MainVideoComponent;
import qd.com.qidianhuyu.module.dm.MainVideoModule;
import qd.com.qidianhuyu.presenter.MainVideoPresenter;

public class MainVideoFragment extends BaseFragment<MainVideoPresenter> {

    private static final String TAG = "MainVideoFragment";
    private static final int PERMISSION_REQUEST_CODE = 1001;

    /**
     * assets目录文件拷贝工具类
     */
    private Common commonUtils;
    /**
     * 页码
     */
    public int PAGE_START = 0;
    /**
     * 数据请求是否为加载更多数据
     */
    public boolean isLoadMoreData = false;
    /**
     * 判断视频是否正在显示视频列表
     */
    private boolean isHome = true;

    @Inject
    MainVideoPresenter mainVideoPresenter;

    MainVideoComponent mainVideoComponent;


    @BindView(R2.id.video_play)
    public AlivcVideoPlayView videoPlayView;

    public static MainVideoFragment newInstance() {

        Bundle args = new Bundle();

        MainVideoFragment mainVideoFragment = new MainVideoFragment();
        mainVideoFragment.setArguments(args);
        return mainVideoFragment;
    }

    @Override
    public void initParameter() {

        mainVideoComponent = DaggerMainVideoComponent.builder()
                .appComponent(getAppComponent())
                .mainVideoModule(new MainVideoModule(this))
                .build();

        mainVideoComponent.ingect(this);


    }

    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
        mainVideoPresenter.loadPlayList(PAGE_START, false);
    }

    /**
     * 初始化View
     */
    protected void initView() {
        videoPlayView.setOnRefreshDataListener(new MyRefreshDataListener(this));
        float screenWidth = ScreenUtils.getWidth(_Activity);
        float screenHeight = ScreenUtils.getRealHeight(_Activity);
        if (screenHeight / screenWidth > 2) {
            int viewHeight = (int)(screenHeight - ScreenUtils.getNavigationHeight(_Activity));
            ViewGroup.LayoutParams layoutParams = videoPlayView.getLayoutParams();
            layoutParams.height = viewHeight;
            videoPlayView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    public void onResume() {
        super.onResume();
        if (isHome) {
            videoPlayView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isHome) {
            videoPlayView.onPause();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        if (commonUtils != null) {
            commonUtils.onDestroy();
            commonUtils = null;
        }

        if (videoPlayView != null) {
            videoPlayView.onDestroy();
        }

        super.onDestroy();
    }

    /**
     * 视频播放列表刷新、加载更多事件监听
     */
    private class MyRefreshDataListener implements AlivcVideoListView.OnRefreshDataListener {
        WeakReference<MainVideoFragment> weakReference;

        MyRefreshDataListener(MainVideoFragment mainVideoFragment) {
            weakReference = new WeakReference<>(mainVideoFragment);
        }

        @Override
        public void onRefresh() {

            MainVideoFragment mainVideoFragment = weakReference.get();
            if (mainVideoFragment != null) {
                mainVideoFragment.isLoadMoreData = false;
                mainVideoPresenter.loadPlayList(PAGE_START, false);
            }
        }

        @Override
        public void onLoadMore() {
            MainVideoFragment mainVideoFragment = weakReference.get();
            if (mainVideoFragment != null) {
                mainVideoFragment.isLoadMoreData = true;
                mainVideoPresenter.loadPlayList(PAGE_START, false);
            }
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_video_list;
    }

    @Override
    public MainVideoPresenter getFragmentPresenter() {
        return mainVideoPresenter;
    }
}
