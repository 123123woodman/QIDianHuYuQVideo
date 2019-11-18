package qd.com.newproject.component;

import dagger.Component;
import qd.com.library.component.AppComponent;
import qd.com.library.scope.PerActivity;
import qd.com.newproject.activity.PreViewActivity;
import qd.com.newproject.module.PreViewModule;

/**
 * Created by Administrator on 2018/3/29.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {PreViewModule.class})
public interface PreViewComponent {

    void ingect(PreViewActivity preViewActivity);

}
