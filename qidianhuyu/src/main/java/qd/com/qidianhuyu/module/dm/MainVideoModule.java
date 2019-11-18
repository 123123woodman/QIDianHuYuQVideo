package qd.com.qidianhuyu.module.dm;

import dagger.Module;
import dagger.Provides;
import qd.com.library.scope.PerActivity;
import qd.com.qidianhuyu.fragment.MainVideoFragment;
import qd.com.qidianhuyu.presenter.MainVideoPresenter;

@Module
public class MainVideoModule {
    private MainVideoFragment mainVideoFragment;

    public MainVideoModule(MainVideoFragment mainVideoFragment) {
        this.mainVideoFragment = mainVideoFragment;
    }

    @Provides
    @PerActivity
    public MainVideoPresenter provideMainVideoPresenter() {
        return new MainVideoPresenter(mainVideoFragment);
    }

}
