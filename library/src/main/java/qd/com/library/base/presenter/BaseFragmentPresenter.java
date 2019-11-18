package qd.com.library.base.presenter;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import per.goweii.rxhttp.core.RxLife;
import qd.com.library.base.fragment.BaseFragment;

public abstract class BaseFragmentPresenter<T extends BaseFragment> {




    private RxLife rxLife;

    public void attach() {
        rxLife = RxLife.create();
    }

    public void detach() {
        rxLife.destroy();
        rxLife = null;
    }

    public RxLife getRxLife() {
        return rxLife;
    }

    public void addToRxLife(Disposable disposable) {
        if (rxLife != null) {
            rxLife.add(disposable);
        }
    }

    public abstract T getUI();

}
