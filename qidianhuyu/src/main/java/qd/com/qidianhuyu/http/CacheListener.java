package qd.com.qidianhuyu.http;

public interface CacheListener<E> {
    void onSuccess(int code, E data);
    void onFailed();
}
