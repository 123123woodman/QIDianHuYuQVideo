package qd.com.newproject.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import qd.com.library.apt.ContextView;
import qd.com.library.arouter.ArouterConstant;
import qd.com.library.base.activity.BaseActivity;
import qd.com.newproject.R;
import qd.com.newproject.R2;
import qd.com.newproject.animation.FrameAnimation;
import qd.com.newproject.component.DaggerPreViewComponent;
import qd.com.newproject.component.PreViewComponent;
import qd.com.newproject.module.PreViewModule;
import qd.com.newproject.presnter.PreViewPresenter;

@ContextView(R2.layout.activity_pre_view)
@Route(path = ArouterConstant.ACTIVITY_PREVIEWACTIVITY)
public class PreViewActivity extends BaseActivity {

    @Inject
    PreViewComponent preViewComponent;

    @Inject
    PreViewPresenter preViewPresenter;

    @BindView(R2.id.returnlast)
    Button returnLast;

    @BindView(R2.id.pre_frameanimation)
    FrameAnimation frameAnimation;

    public static void lanuch(BaseActivity activity) {
        Intent intent = new Intent(activity, PreViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void initParameter() {

        preViewComponent = DaggerPreViewComponent.builder()
                .appComponent(getAppComponent())
                .preViewModule(new PreViewModule())
                .build();

        preViewComponent.ingect(this);

        /**
         * 加载帧动画
         */
        preViewPresenter.initFrameAnimation(frameAnimation);

    }

    @Override
    public int getLayOutId() {
        for (Class c = getClass(); c != Context.class; c = c.getSuperclass()) {
            ContextView contextView = (ContextView) c.getAnnotation(ContextView.class);
            if (contextView != null) {
                return contextView.value();
            }
        }
        return 0;
    }

    @OnClick({R2.id.returnlast, R2.id.pre_frameanimation})
    public void OnClick(View view) {
        if (view.getId() == R.id.returnlast) {
            ARouter.getInstance().build(ArouterConstant.ACTIVITY_MAINACTIVITY).navigation();
            frameAnimation.stop();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        frameAnimation.stop();
    }

    @Override
    protected void onStart() {
        super.onStart();

        /**
         * 启动帧动画
         */
        preViewPresenter.startFrameAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        frameAnimation.stop();
    }
}
