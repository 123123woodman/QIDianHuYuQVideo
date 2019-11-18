package qd.com.library.base.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import javax.inject.Inject;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import qd.com.library.component.ActivityComponent;
import qd.com.library.component.AppComponent;
import qd.com.library.dao.DaoMaster;
import qd.com.library.dao.DaoSession;
import qd.com.library.utils.ToastUtils;

public class BaseApplication extends MultiDexApplication {

    public static DaoMaster daoMaster;
    public static DaoSession daoSession;

    private static BaseApplication baseApplication;

    @Inject
    ToastUtils toastUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        baseApplication = this;

        AppConfig.INSTANCE.initConfig(this);

        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@NonNull Exception e) {

                    }
                }).install();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    private static DaoMaster getDaoMaster() {

        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getBaseApplication().getBaseContext(), "irisLock", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }

        return daoMaster;

    }

    public AppComponent getAppComponent() {
        return AppConfig.INSTANCE.getAppComponent();
    }

    public ActivityComponent getActivityComponent() {
        return AppConfig.INSTANCE.getActivityComponent();
    }

    public ToastUtils getToastUtils() {
        return toastUtils;
    }

    public void releaseComponent() {
        AppConfig.INSTANCE.releaseAppComponent();
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
