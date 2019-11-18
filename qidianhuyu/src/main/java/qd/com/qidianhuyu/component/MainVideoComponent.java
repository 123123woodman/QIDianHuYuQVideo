package qd.com.qidianhuyu.component;

import dagger.Component;
import qd.com.library.component.AppComponent;
import qd.com.library.scope.PerActivity;
import qd.com.qidianhuyu.fragment.MainVideoFragment;
import qd.com.qidianhuyu.module.dm.MainVideoModule;
import qd.com.qidianhuyu.presenter.MainVideoPresenter;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainVideoModule.class})
public interface MainVideoComponent {

    void ingect(MainVideoFragment mainVideoFragment);

    MainVideoPresenter getMainVideoPresenter();

}
