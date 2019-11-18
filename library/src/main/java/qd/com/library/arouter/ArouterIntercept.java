package qd.com.library.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import qd.com.library.sp.SPModule;

@Interceptor(priority = 1, name = "登录拦截器")
public class ArouterIntercept implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (SPModule.getAppisLogin(postcard.getPath())) {
            callback.onContinue(postcard);
        } else {
            callback.onInterrupt(null);
        }

    }

    @Override
    public void init(Context context) {

    }
}
