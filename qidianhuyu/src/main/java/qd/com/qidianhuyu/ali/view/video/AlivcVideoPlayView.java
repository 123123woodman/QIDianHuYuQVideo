package qd.com.qidianhuyu.ali.view.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.aliyun.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

import qd.com.qidianhuyu.ali.utils.DensityUtils;
import qd.com.qidianhuyu.ali.view.ShareDialog;
import qd.com.qidianhuyu.ali.view.video.videolist.AlivcVideoListView;
import qd.com.qidianhuyu.ali.view.video.videolist.IVideoSourceModel;
import qd.com.qidianhuyu.ali.widget.CircleProgressBar;
import qd.com.qidianhuyu.bean.QdVideoListBean;


/**
 * 播放界面, 负责initPlayerSDK以及各种view
 */
public class AlivcVideoPlayView extends FrameLayout {


    private static final String TAG = "AlivcVideoPlayView";
    private Context context;
    private AlivcVideoListView videoListView;

    /**
     * 刷新数据listener (下拉刷新和上拉加载)
     */
    private AlivcVideoListView.OnRefreshDataListener onRefreshDataListener;
    /**
     * 视频缓冲加载view
     */
    private LoadingView mLoadingView;


    /**
     * 分享选择提示窗
     *
     * @param context
     */

    private ShareDialog mShareDialog;

    //private LittleVideoListAdapter mVideoAdapter;
    private LittleVideoListAdapter littleVideoListAdapter;

    public AlivcVideoPlayView(@NonNull Context context) {
        this(context, null);
    }

    public AlivcVideoPlayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        initPlayListView();
        initLoadingView();
    }
    private CircleProgressBar mProgressBar;

    private int mClickPosition;

    /**
     * 初始化视频列表
     */
    private void initPlayListView() {
        videoListView = new AlivcVideoListView(context);
        //创建adapter，需要继承BaseVideoListAdapter
        littleVideoListAdapter = new LittleVideoListAdapter(context);
//        mApapter.setItemBtnClick(new LittleVideoListAdapter.OnItemBtnClick() {
//            @Override
//            public void onDownloadClick(int position) {
//                mClickPosition = position;
//
//                if (mShareDialog == null) {
//                    mShareDialog = new ShareDialog();
//                    mShareDialog.setItemSelectedListenr(new ShareDialog.OnItemSelectedListener() {
//                        @Override
//                        public void onDownloadClick() {
//
//                            /**
//                             * 准备分享
//                             */
//
//                        }
//
//                        @Override
//                        public void onDeleteClick() {
//                            /**
//                             * 暂不启用
//                             */
//                        }
//
//                    });
//                }
//            }
//        });
        //给AlivcVideoListView实例化对象添加adapter
        videoListView.setAdapter(littleVideoListAdapter);
        videoListView.setVisibility(VISIBLE);
        //设置sdk播放器实例化对象数量
        videoListView.setPlayerCount(1);
        //设置下拉、上拉监听进行加载数据
        videoListView.setOnRefreshDataListener(new AlivcVideoListView.OnRefreshDataListener() {
            @Override
            public void onRefresh() {
                if (onRefreshDataListener != null) {
                    onRefreshDataListener.onRefresh();
                }
            }

            @Override
            public void onLoadMore() {
                if (onRefreshDataListener != null) {
                    onRefreshDataListener.onLoadMore();
                }
            }
        });
        //设置视频缓冲监听
        videoListView.setLoadingListener(new IPlayer.OnLoadingStatusListener() {
            @Override
            public void onLoadingBegin() {
                mLoadingView.start();
            }

            @Override
            public void onLoadingEnd() {
                mLoadingView.cancle();
            }

            @Override
            public void onLoadingProgress(int var1, float var2) {

            }
        });

        videoListView.setOnCompleteListern(new IPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {

            }
        });
        //添加到布局中
        addSubView(videoListView);
    }

    private void initLoadingView() {
        mLoadingView = new LoadingView(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                5);
        params.setMargins(0, 0, 0, DensityUtils.dip2px(getContext(), 4));
        params.gravity = Gravity.BOTTOM;
        addView(mLoadingView, params);
    }

    /**
     * addSubView 添加子view到布局中
     *
     * @param view 子view
     */
    private void addSubView(View view) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        addView(view, params);
    }

    public void refreshVideoList(List<QdVideoListBean> datas) {
        List<IVideoSourceModel> videoList = new ArrayList<>();
        videoList.addAll(datas);
        videoListView.refreshData(videoList);
        //取消加载loading
        mLoadingView.cancle();
    }

    /**
     * 添加更多視頻
     * @param data
     */
    public void addMoreData(List<QdVideoListBean> data) {
        List<IVideoSourceModel> videoList = new ArrayList<>();
        videoList.addAll(data);
        videoListView.addMoreData(videoList);
        //取消加载loading
        mLoadingView.cancle();
    }

    /**
     * 设置下拉刷新数据listener
     *
     * @param listener OnRefreshDataListener
     */
    public void setOnRefreshDataListener(AlivcVideoListView.OnRefreshDataListener listener) {
        this.onRefreshDataListener = listener;
    }

    public void onStart() {

    }

    public void onResume() {
        videoListView.setOnBackground(false);

    }

    public void onStop() {
        mLoadingView.cancle();
    }

    public void onPause() {

        videoListView.setOnBackground(true);

    }

    public void onDestroy() {
        context = null;
    }

    /**
     * 视频列表获取失败
     */
    public void loadFailure() {
        mLoadingView.cancle();
        videoListView.loadFailure();
    }

}
