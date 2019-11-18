package qd.com.newproject.component;

import dagger.Component;
import qd.com.library.component.ActivityComponent;
import qd.com.library.component.AppComponent;
import qd.com.library.scope.PerActivity;
import qd.com.newproject.activity.MainActivity;
import qd.com.newproject.module.MainModule;

/**
 * Created by Administrator on 2018/3/21.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class})
public interface MainComponent extends ActivityComponent {
    void ingect(MainActivity activity);

}
