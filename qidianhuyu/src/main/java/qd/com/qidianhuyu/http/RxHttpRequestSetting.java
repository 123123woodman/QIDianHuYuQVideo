package qd.com.qidianhuyu.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import per.goweii.rxhttp.request.setting.DefaultRequestSetting;
import per.goweii.rxhttp.request.utils.HttpsCompat;
import qd.com.library.common.Config;

public class RxHttpRequestSetting extends DefaultRequestSetting {

    private final PersistentCookieJar mCookieJar;

    public RxHttpRequestSetting(PersistentCookieJar cookieJar) {
        mCookieJar = cookieJar;
    }

    @Override
    public boolean isDebug() {
        return Config.DEBUG;
    }

    @Override
    public long getTimeout() {
        return Config.HTTP_TIMEOUT;
    }

    @NonNull
    @Override
    public String getBaseUrl() {
        return Config.BASE_URL;
    }

    @Override
    public int getSuccessCode() {
        return 0;
    }

    @Override
    public void setOkHttpClient(OkHttpClient.Builder builder) {
        super.setOkHttpClient(builder);
        HttpsCompat.enableTls12ForOkHttp(builder);
        HttpsCompat.enableTls12ForHttpsURLConnection();
        builder.cookieJar(mCookieJar);
    }

    @Nullable
    @Override
    public Map<Class<?>, String> getServiceBaseUrl() {
        return super.getServiceBaseUrl();
    }

    @Nullable
    @Override
    public Interceptor[] getInterceptors() {
        return super.getInterceptors();
    }
}
