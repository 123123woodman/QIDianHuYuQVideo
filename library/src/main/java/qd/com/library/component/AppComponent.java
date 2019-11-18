package qd.com.library.component;

import javax.inject.Singleton;

import dagger.Component;
import qd.com.library.base.base.BaseApplication;
import qd.com.library.db.DaoUtils;
import qd.com.library.module.AppModule;
import qd.com.library.utils.GridDividerItemDecoration;
import qd.com.library.utils.StringUtils;
import qd.com.library.utils.ToastUtils;

/**
 * Created by Administrator on 2018/3/21.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void ingect(BaseApplication application);

    //全局toast
    ToastUtils getToastUtils();

    GridDividerItemDecoration getGridDividerItemDecoration();

    DaoUtils getDaoUtils();

    StringUtils getStringUtils();

}
