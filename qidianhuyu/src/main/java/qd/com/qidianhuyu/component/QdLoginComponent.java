package qd.com.qidianhuyu.component;

import dagger.Component;
import qd.com.library.component.AppComponent;
import qd.com.library.scope.PerActivity;
import qd.com.qidianhuyu.module.dm.QdLoginModule;
import qd.com.qidianhuyu.ui.QdLoginActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {QdLoginModule.class})
public interface QdLoginComponent {

    void ingect(QdLoginActivity activity);

}
