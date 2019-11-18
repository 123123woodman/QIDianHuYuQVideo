package qd.com.qidianhuyu.http;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import qd.com.library.encrypt.MD5Coder;
import qd.com.library.listern.SimpleListener;
import qd.com.library.utils.file.CacheUtils;

public class QDCache {

    private static final int MAX_SIZE = 10 * 1024 * 1024;
    public static final String DIR_NAME = "rx-cache";

    private static QDCache INSTANCE;

    private DiskLruCache mDiskLruCache = null;
    private Gson mGson = new Gson();

    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = new QDCache();
        }
    }

    public static void Test() {}

    public static QDCache getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("QDCache未初始化");
        }
        return INSTANCE;
    }

    private QDCache() {
        openDiskLruCache();
    }

    private DiskLruCache getDiskLruCache() {
        if (mDiskLruCache == null) {
            throw new RuntimeException("QDCache未初始化或初始化失败");
        }
        return mDiskLruCache;
    }

    public void openDiskLruCache() {
        if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
            try {
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(CacheUtils.getCacheDir(), DIR_NAME);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(file, 1, 1, MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSame(Object cache, Object net) {
        String cacheJson = mGson.toJson(cache);
        String netJson = mGson.toJson(net);
        return TextUtils.equals(cacheJson, netJson);
    }

    public void save(final String key, final Object bean) {
        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> emitter) throws Exception {
                saveSync(key, bean);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    public Disposable remove(String key, final SimpleListener listener) {
        return Observable.just(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        removeSync(s);
                        listener.onResult();
                    }
                });
    }

    public <T> Disposable getBean(final String key, final Class<T> clazz, final CacheListener<T> listener) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                T bean = getBeanSync(key, clazz);
                if (bean == null) {
                    throw new NullCacheException();
                }
                emitter.onNext(bean);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() {
            @Override
            public void accept(T bean) throws Exception {
                listener.onSuccess(QDApi.ApiCode.SUCCESS, bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed();
            }
        });
    }

    public <T> Disposable getList(final String key, final Class<T> clazz, final CacheListener<List<T>> listener) {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> emitter) throws Exception {
                List<T> bean = getListSync(key, clazz);
                if (bean == null) {
                    throw new NullCacheException();
                }
                emitter.onNext(bean);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<T>>() {
            @Override
            public void accept(List<T> bean) throws Exception {
                listener.onSuccess(QDApi.ApiCode.SUCCESS, bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed();
            }
        });
    }

    private void removeSync(String key) throws IOException {
        synchronized (getDiskLruCache()) {
            getDiskLruCache().remove(MD5Coder.encode(key));
        }
    }

    private void saveSync(String key, Object bean) throws IOException {
        synchronized (getDiskLruCache()) {
            DiskLruCache.Editor editor = getDiskLruCache().edit(MD5Coder.encode(key));
            editor.set(0, mGson.toJson(bean));
            editor.commit();
            getDiskLruCache().flush();
        }
    }

    private <T> T getBeanSync(String key, Class<T> clazz) throws IOException {
        synchronized (getDiskLruCache()) {
            DiskLruCache.Snapshot snapshot = getDiskLruCache().get(MD5Coder.encode(key));
            String json = snapshot.getString(0);
            T bean = mGson.fromJson(json, clazz);
            snapshot.close();
            return bean;
        }
    }

    private <T> List<T> getListSync(String key, Class<T> clazz) throws IOException {
        synchronized (getDiskLruCache()) {
            DiskLruCache.Snapshot snapshot = getDiskLruCache().get(MD5Coder.encode(key));
            String json = snapshot.getString(0);
            List<T> list = jsonToBeanList(json, clazz);
            snapshot.close();
            return list;
        }
    }

    private <T> List<T> jsonToBeanList(String json, Class<T> t) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        List<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonarray = parser.parse(json).getAsJsonArray();
        for (JsonElement element : jsonarray) {
            list.add(mGson.fromJson(element, t));
        }
        return list;
    }

    @SuppressLint("DefaultLocale")
    public static class CacheKey {
        private static final String QIDIANHUYU_LIST = "index/Video/getlist/%d/json";//page

        public static String QIDIANHUYU_LIST(int page) {
            return String.format(QIDIANHUYU_LIST, page);
        }

    }

}
