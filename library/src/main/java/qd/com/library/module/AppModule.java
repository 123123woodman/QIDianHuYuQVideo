package qd.com.library.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import qd.com.library.base.base.BaseApplication;
import qd.com.library.db.DaoUtils;
import qd.com.library.utils.GridDividerItemDecoration;
import qd.com.library.utils.StringUtils;
import qd.com.library.utils.ToastUtils;

/**
 * Created by Administrator on 2018/3/21.
 */
@Module
public class AppModule {

    BaseApplication mApp;

    public AppModule(BaseApplication application) {
        this.mApp = application;
    }

    @Provides @Singleton
    public ToastUtils provideToastUtils() {
        return new ToastUtils(mApp);
    }

    @Provides @Singleton
    public GridDividerItemDecoration provideGridDividerItemDecoration(){return new GridDividerItemDecoration();}

    @Provides @Singleton
    public DaoUtils provideDaoUtils() {return new DaoUtils();}

    @Provides@Singleton
    public StringUtils provideStringUtils() {return new StringUtils();}

}
