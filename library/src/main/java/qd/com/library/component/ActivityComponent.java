package qd.com.library.component;

import dagger.Component;
import qd.com.library.module.ActivityModule;
import qd.com.library.scope.SubActivity;

/**
 * Created by Administrator on 2018/3/21.
 */
@SubActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent{

}
