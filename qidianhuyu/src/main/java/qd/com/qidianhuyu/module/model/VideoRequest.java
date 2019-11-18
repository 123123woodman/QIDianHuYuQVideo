package qd.com.qidianhuyu.module.model;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.RequestBody;
import per.goweii.rxhttp.core.RxLife;
import qd.com.qidianhuyu.bean.QdVideoListBean;
import qd.com.qidianhuyu.bean.QdVideoListRequestBean;
import qd.com.qidianhuyu.http.BaseRequest;
import qd.com.qidianhuyu.http.QDApi;
import qd.com.qidianhuyu.http.QDCache;
import qd.com.qidianhuyu.http.QDRequestBody;
import qd.com.qidianhuyu.http.RequestListener;

public class VideoRequest extends BaseRequest {

    /**
     *
     * @param rxLife
     * @param refresh 是否刷新缓存
     * @param page
     * @param listener
     */
    public static void getVideoList(RxLife rxLife, boolean refresh, @IntRange(from = 0) int page, @NonNull RequestListener<List<QdVideoListBean>> listener) {
        QDRequestBody<QdVideoListRequestBean> info= new QDRequestBody();
        info.setData(new QdVideoListRequestBean());
        Gson gson=new Gson();
        String obj=gson.toJson(info);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
        if (page == 0) {
            cacheAndNetList(rxLife,
                    QDApi.api().getVideoList(body),
                    refresh,
                    QDCache.CacheKey.QIDIANHUYU_LIST(page),
                    QdVideoListBean.class,
                    listener);
        } else {
            rxLife.add(request(QDApi.api().getVideoList(body), listener));
        }
    }

}
