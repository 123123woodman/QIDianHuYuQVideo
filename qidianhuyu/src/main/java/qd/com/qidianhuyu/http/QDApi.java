package qd.com.qidianhuyu.http;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import per.goweii.rxhttp.request.Api;
import per.goweii.rxhttp.request.base.BaseBean;
import qd.com.qidianhuyu.bean.QdVideoListBean;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class QDApi extends Api {

    public static ApiService api() {
        return api(ApiService.class);
    }

    public static class ApiCode {
        public static final int ERROR = 1000;

        public static final int SUCCESS = 0;

        public static final int FAILED_NO_CACHE = -9000;  //没有缓存

    }

    public interface ApiService {

        @GET("https://v2.jinrishici.com/token")
        Observable<QDResponse<String>> getJinrishiciToken();

        /**
         * 登录
         */

        /**
         * 退出
         * 方法： GET
         * 如果本地做了用户账号密码和保存，及时清理。
         */
        @POST()
        Observable<QDResponse<BaseBean>> logout();

        /**
         * 分享
         */

        /**
         * 视频列表
         */
        @POST("index/Video/getlist")
        Observable<QDResponse<List<QdVideoListBean>>> getVideoList(@Body RequestBody body);
    }

}
