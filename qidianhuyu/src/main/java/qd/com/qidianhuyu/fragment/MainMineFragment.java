package qd.com.qidianhuyu.fragment;

import android.os.Bundle;

import qd.com.library.base.fragment.BaseFragment;
import qd.com.qidianhuyu.R;
import qd.com.qidianhuyu.presenter.MainMinePresenter;

public class MainMineFragment extends BaseFragment<MainMinePresenter> {

    public static MainMineFragment newInstance() {
        Bundle bundle = new Bundle();
        MainMineFragment mainMineFragment = new MainMineFragment();
        mainMineFragment.setArguments(bundle);
        return mainMineFragment;
    }

    @Override
    public void initParameter() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_mine;
    }

    @Override
    public MainMinePresenter getFragmentPresenter() {
        return null;
    }
}
