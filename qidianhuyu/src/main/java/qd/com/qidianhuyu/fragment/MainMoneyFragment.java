package qd.com.qidianhuyu.fragment;

import android.os.Bundle;

import qd.com.library.base.fragment.BaseFragment;
import qd.com.qidianhuyu.R;
import qd.com.qidianhuyu.presenter.MainMoneyPresenter;

public class MainMoneyFragment extends BaseFragment<MainMoneyPresenter> {

    public static MainMoneyFragment newInstance() {
        Bundle args = new Bundle();
        MainMoneyFragment mainTaskFragment = new MainMoneyFragment();
        mainTaskFragment.setArguments(args);
        return mainTaskFragment;
    }

    @Override
    public void initParameter() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_navi;
    }

    @Override
    public MainMoneyPresenter getFragmentPresenter() {
        return null;
    }
}
