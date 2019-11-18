package qd.com.qidianhuyu.presenter;

import java.util.List;

import per.goweii.rxhttp.request.exception.ExceptionHandle;
import qd.com.library.base.presenter.BaseFragmentPresenter;
import qd.com.qidianhuyu.bean.QdVideoListBean;
import qd.com.qidianhuyu.fragment.MainVideoFragment;
import qd.com.qidianhuyu.http.RequestListener;
import qd.com.qidianhuyu.module.model.VideoRequest;

public class MainVideoPresenter extends BaseFragmentPresenter<MainVideoFragment> {
    private String TAG = MainVideoPresenter.class.getSimpleName();
    private MainVideoFragment mainVideoFragment;

    public MainVideoPresenter(MainVideoFragment mainVideoFragment) {
        this.mainVideoFragment = mainVideoFragment;
    }

    public void loadPlayList(int page, boolean refresh) {
        VideoRequest.getVideoList(getRxLife(), false, page, new RequestListener<List<QdVideoListBean>>() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, List<QdVideoListBean> data) {
                if (getUI().isLoadMoreData) {
                    getUI().videoPlayView.addMoreData(data);
                } else {
                    getUI().videoPlayView.refreshVideoList(data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                getUI().videoPlayView.loadFailure();
            }

            @Override
            public void onError(ExceptionHandle handle) {
                getUI().videoPlayView.loadFailure();
            }

            @Override
            public void onFinish() {
            }
        });
    }

//
//    /**
//     * 获取播放列表数据
//     *
//     * @param id 请求第pageNo页数据
//     */
//    private List<IVideoSourceModel> baseList = new ArrayList<>();
//    private void loadPlayList(final int id) {
//        Log.d(TAG, "pageNo:" + id);
//
//        new HttpEngine.HttpResponseResultCallback<LittleHttpResponse.LittleMyVideoListResponse>() {
//            @Override
//            public void onResponse(final boolean result, String message,
//                                   final LittleHttpResponse.LittleMyVideoListResponse data) {
//
//                if (result) {
//                    List<LittleMineVideoInfo.VideoListBean> videoList = data.data.getVideoList();
//                    if (videoList != null && videoList.size() > 0) {
//                        int listSize = videoList.size();
//                        for (int i = 0; i < listSize; i++) {
//                            videoList.get(i).setCensorStatus(LittleMineVideoInfo
//                                    .VideoListBean.STATUS_CENSOR_SUCCESS);
//                            if (i == listSize - 1) {
//                                mLastVideoId = videoList.get(i).getId();
//                            }
//                        }
//                    }
//                    if (!isLoadMoreData) {
//                        videoPlayView.refreshVideoList(data.data.getVideoList());
//                    } else {
//                        videoPlayView.addMoreData(data.data.getVideoList());
//                    }
//                } else {
//                    if (videoPlayView != null) {
//                        videoPlayView.loadFailure();
//                    }
//                    ToastUtils.show(VideoListActivity.this, message );
//                }
//
//            }
//        });
//        //网络请求
//    }


    @Override
    public MainVideoFragment getUI() {
        return mainVideoFragment;
    }
}
