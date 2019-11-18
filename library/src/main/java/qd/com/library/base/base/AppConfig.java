package qd.com.library.base.base;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import per.goweii.rxhttp.core.RxHttp;
import qd.com.library.component.ActivityComponent;
import qd.com.library.component.AppComponent;
import qd.com.library.component.DaggerActivityComponent;
import qd.com.library.component.DaggerAppComponent;
import qd.com.library.module.ActivityModule;
import qd.com.library.module.AppModule;

public enum AppConfig {

    INSTANCE;

    private BaseApplication application;
    private AppComponent appComponent;
    private ActivityComponent activityComponent;

    public void initConfig(BaseApplication application) {
        this.application = application;

        initArouter();
        initFresco();
        initComponent();
    }

    /**
     * 初始化dagger2 baseComponent
     */
    private void initComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();
        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        appComponent.ingect(application);
    }

    /**
     * 初始化Arouter
     */
    private void initArouter() {
//        if ("isDebug") {
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(application);
    }

//    /**
//     * 初始化网络
//     */
//    private void initRxHttp(Context context) {
//        RxHttp.init(context);
//        RxHttp.initRequest(new RxHttpRequestSetting(getCookieJar()));
//    }
//
//    /**
//     * 初始化缓存
//     */
//    private void initQdCatch() {
//
//    }

    /**
     * 初始化Fresco
     * @return
     */
    public void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(application)
                .setImageCacheStatsTracker(imageCacheStatsTracker)
                .setDecodeMemoryFileEnabled(true)
                .setDownsampleEnabled(false)
                .build();

        Fresco.initialize(application, config);
    }

    /**
     * Fresco
     */
    ImageCacheStatsTracker imageCacheStatsTracker = new ImageCacheStatsTracker() {
        @Override
        public void onBitmapCachePut() {
        }

        @Override
        public void onBitmapCacheHit() {
        }

        @Override
        public void onBitmapCacheMiss() {
        }

        @Override
        public void onMemoryCachePut() {
        }

        @Override
        public void onMemoryCacheHit() {
        }

        @Override
        public void onMemoryCacheMiss() {
        }

        @Override
        public void onStagingAreaHit() {
        }

        @Override
        public void onStagingAreaMiss() {
        }

        @Override
        public void onDiskCacheHit() {
        }

        @Override
        public void onDiskCacheMiss() {
        }

        @Override
        public void onDiskCacheGetFail() {
        }

        @Override
        public void registerBitmapMemoryCache(CountingMemoryCache<?, ?> bitmapMemoryCache) {
        }

        @Override
        public void registerEncodedMemoryCache(CountingMemoryCache<?, ?> encodedMemoryCache) {
        }
    };


    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void releaseAppComponent() {
        appComponent = null;
    }

}
