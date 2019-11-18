package qd.com.qidianhuyu.app;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import per.goweii.rxhttp.core.RxHttp;
import qd.com.library.base.base.BaseApplication;
import qd.com.qidianhuyu.http.QDCache;
import qd.com.qidianhuyu.http.RxHttpRequestSetting;

public class QiDianHuYuApplication extends BaseApplication {

    private static PersistentCookieJar mCookieJar = null;

    @Override
    public void onCreate() {
        super.onCreate();

        RxHttp.init(this);
        RxHttp.initRequest(new RxHttpRequestSetting(getCookieJar()));
        QDCache.init();

    }

    public static PersistentCookieJar getCookieJar() {
        if (mCookieJar == null) {
            mCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getBaseApplication()));
        }
        return mCookieJar;
    }

}
