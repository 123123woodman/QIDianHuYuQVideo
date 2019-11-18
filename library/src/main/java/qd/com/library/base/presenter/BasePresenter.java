package qd.com.library.base.presenter;

import qd.com.library.base.activity.BaseActivity;

public abstract class BasePresenter<T extends BaseActivity> {

     public abstract T getUI();

}
