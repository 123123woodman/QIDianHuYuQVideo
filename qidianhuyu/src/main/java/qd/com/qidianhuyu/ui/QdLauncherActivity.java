package qd.com.qidianhuyu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;

import qd.com.library.arouter.ArouterConstant;
import qd.com.library.arouter.LoginNavigationCallbackImpl;
import qd.com.library.sp.SPModule;
import qd.com.qidianhuyu.R;

public class QdLauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qd_launcher);
        SPModule.setAppislogin(false);
        ARouter.getInstance()
                .build(ArouterConstant.ACTIVITY_QDMAINACTIVITY)
                .navigation(this, new LoginNavigationCallbackImpl());
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
