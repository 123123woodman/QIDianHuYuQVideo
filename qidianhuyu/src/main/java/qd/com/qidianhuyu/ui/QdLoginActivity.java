package qd.com.qidianhuyu.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
//import com.leaf.library.StatusBarUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import qd.com.library.arouter.ArouterConstant;
import qd.com.library.base.activity.BaseActivity;
import qd.com.library.sp.SPModule;
import qd.com.library.utils.StringUtils;
import qd.com.library.utils.ToastUtils;
import qd.com.qidianhuyu.R;
import qd.com.qidianhuyu.R2;
import qd.com.qidianhuyu.component.DaggerQdLoginComponent;
import qd.com.qidianhuyu.component.QdLoginComponent;
import qd.com.qidianhuyu.module.dm.QdLoginModule;
import qd.com.qidianhuyu.widget.AccountInputView;
import qd.com.qidianhuyu.widget.PasswordInputView;
import qd.com.qidianhuyu.widget.SubmitView;

@Route(path = ArouterConstant.ACTIVITY_QDLOGINACTIVITY)
public class QdLoginActivity extends BaseActivity{

    //curl 55 SSL_write() returned SYSCALL, errno = 10053
    @BindView(R2.id.loginactivity_account)
    AccountInputView account;
    @BindView(R2.id.loginactivity_password)
    PasswordInputView passWord;
    @BindView(R2.id.loginactivity_phone_login)
    SubmitView login;

    @Inject
    StringUtils stringUtils;

    @Autowired
    String path;

    @Inject
    ToastUtils toastUtils;

    QdLoginComponent qdLoginComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    public void initParameter() {
        qdLoginComponent = DaggerQdLoginComponent.builder()
                .appComponent(getAppComponent())
                .qdLoginModule(new QdLoginModule())
                .build();
        qdLoginComponent.ingect(this);
        ARouter.getInstance().inject(this);

    }

    @OnClick({R2.id.loginactivity_account, R2.id.loginactivity_password, R2.id.loginactivity_phone_login, R2.id.loginactivity_register})
    public void onClick(View view) {
        if (view.getId() == R.id.loginactivity_phone_login) {
            ARouter.getInstance().build(path).navigation();
            SPModule.setAppislogin(true);
        } else if (view.getId() == R.id.loginactivity_register) {
            SPModule.setAppaccount(account.getText().toString());
            SPModule.setApppassword(passWord.getText().toString());
            SPModule.setAppisregister(true);
            SPModule.setAppislogin(true);
            ARouter.getInstance().build(path).navigation();
        }
    }

    @Override
    public int getLayOutId() {
        return R.layout.qdactivity_login;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
