package qd.com.qidianhuyu.http;

import per.goweii.rxhttp.request.exception.ExceptionHandle;

public abstract class RequestCallback<E> implements RequestListener<E> {
    @Override
    public void onStart() {
    }

    @Override
    public void onError(ExceptionHandle handle) {
    }

    @Override
    public void onFinish() {
    }
}