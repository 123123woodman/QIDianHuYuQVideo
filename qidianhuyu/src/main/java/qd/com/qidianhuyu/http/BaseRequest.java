package qd.com.qidianhuyu.http;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import per.goweii.rxhttp.core.RxLife;
import per.goweii.rxhttp.request.RxRequest;
import per.goweii.rxhttp.request.exception.ExceptionHandle;

public class BaseRequest {

    protected static <T> Disposable request(@NonNull Observable<QDResponse<T>> observable, @NonNull final RequestListener<T> listener) {
        return RxRequest.create(observable)
                .listener(new RxRequest.RequestListener() {
                    @Override
                    public void onStart() {
                        listener.onStart();
                    }

                    @Override
                    public void onError(ExceptionHandle handle) {
                        listener.onError(handle);
                        listener.onFailed(QDApi.ApiCode.ERROR, handle.getMsg());
                    }

                    @Override
                    public void onFinish() {
                        listener.onFinish();
                    }
                })
                .request(new RxRequest.ResultCallback<T>() {
                    @Override
                    public void onSuccess(int code, T data) {
                        listener.onSuccess(code, data);
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        listener.onFailed(code, msg);
                    }
                });
    }

    protected static <T> Disposable request(@NonNull Observable<QDResponse<T>> observable, @NonNull final RequestListener<T> listener, final ResponseToCache<T> responseToCache) {
        return RxRequest.create(observable)
                .listener(new RxRequest.RequestListener() {
                    @Override
                    public void onStart() {
                        listener.onStart();
                    }

                    @Override
                    public void onError(ExceptionHandle handle) {
                        listener.onError(handle);
                        listener.onFailed(QDApi.ApiCode.ERROR, handle.getMsg());
                    }

                    @Override
                    public void onFinish() {
                        listener.onFinish();
                    }
                })
                .request(new RxRequest.ResultCallback<T>() {
                    @Override
                    public void onSuccess(int code, T data) {
                        if (responseToCache.onResponse(data)) {
                            listener.onSuccess(code, data);
                        }
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        listener.onFailed(code, msg);
                    }
                });
    }

    protected static <T> void cacheBean(String key,
                                        Class<T> clazz,
                                        final RequestListener<T> listener) {
        QDCache.getInstance().getBean(key, clazz, new CacheListener<T>() {
            @Override
            public void onSuccess(int code, T data) {
                listener.onSuccess(code, data);
            }

            @Override
            public void onFailed() {
                listener.onFailed(QDApi.ApiCode.FAILED_NO_CACHE, "");
            }
        });
    }

    protected static <T> void cacheList(String key,
                                        Class<T> clazz,
                                        final RequestListener<List<T>> listener) {
        QDCache.getInstance().getList(key, clazz, new CacheListener<List<T>>() {
            @Override
            public void onSuccess(int code, final List<T> data) {
                listener.onSuccess(code, data);
            }

            @Override
            public void onFailed() {
                listener.onFailed(QDApi.ApiCode.FAILED_NO_CACHE, "");
            }
        });
    }

    protected static <T> void cacheAndNetList(RxLife rxLife,
                                              Observable<QDResponse<List<T>>> observable,
                                              String key,
                                              Class<T> clazz,
                                              RequestListener<List<T>> listener) {
        cacheAndNetList(rxLife, observable, false, key, clazz, listener);
    }

    protected static <T> void cacheAndNetList(final RxLife rxLife,
                                              final Observable<QDResponse<List<T>>> observable,
                                              boolean refresh,
                                              final String key,
                                              Class<T> clazz,
                                              final RequestListener<List<T>> listener) {
        if (refresh) {
            rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                @Override
                public boolean onResponse(List<T> resp) {
                    QDCache.getInstance().save(key, resp);
                    return true;
                }
            }));
            return;
        }
        rxLife.add(QDCache.getInstance().getList(key, clazz, new CacheListener<List<T>>() {
            @Override
            public void onSuccess(int code, final List<T> data) {
                listener.onSuccess(code, data);
                rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                    @Override
                    public boolean onResponse(List<T> resp) {
                        if (QDCache.getInstance().isSame(data, resp)) {
                            return false;
                        }
                        QDCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }

            @Override
            public void onFailed() {
                rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                    @Override
                    public boolean onResponse(List<T> resp) {
                        QDCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }
        }));
    }

    protected static <T> void cacheAndNetBean(RxLife rxLife,
                                              Observable<QDResponse<T>> observable,
                                              String key,
                                              Class<T> clazz,
                                              RequestListener<T> listener) {
        cacheAndNetBean(rxLife, observable, false, key, clazz, listener);
    }

    protected static <T> void cacheAndNetBean(final RxLife rxLife,
                                              final Observable<QDResponse<T>> observable,
                                              boolean refresh,
                                              final String key,
                                              Class<T> clazz,
                                              final RequestListener<T> listener) {
        if (refresh) {
            rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                @Override
                public boolean onResponse(T resp) {
                    QDCache.getInstance().save(key, resp);
                    return true;
                }
            }));
            return;
        }
        rxLife.add(QDCache.getInstance().getBean(key, clazz, new CacheListener<T>() {
            @Override
            public void onSuccess(int code, final T data) {
                listener.onSuccess(code, data);
                rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                    @Override
                    public boolean onResponse(T resp) {
                        if (QDCache.getInstance().isSame(data, resp)) {
                            return false;
                        }
                        QDCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }

            @Override
            public void onFailed() {
                rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                    @Override
                    public boolean onResponse(T resp) {
                        QDCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }
        }));
    }

    public interface ResponseToCache<T> {
        boolean onResponse(T resp);
    }

}
