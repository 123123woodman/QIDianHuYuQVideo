package qd.com.library.utils.file;


import java.io.File;

import qd.com.library.base.base.BaseApplication;


/**
 * 缓存辅助类
 */
public class CacheUtils {

    /**
     * 获取系统默认缓存文件夹
     * 优先返回SD卡中的缓存文件夹
     */
    public static String getCacheDir() {
        File cacheFile = null;
        if (FileUtils.isSDCardAlive()) {
            cacheFile = BaseApplication.getBaseApplication().getExternalCacheDir();
        }
        if (cacheFile == null) {
            cacheFile = BaseApplication.getBaseApplication().getCacheDir();
        }
        return cacheFile.getAbsolutePath();
    }

    /**
     * 获取系统默认缓存文件夹内的缓存大小
     */
    public static String getTotalCacheSize() {
        long cacheSize = FileUtils.getSize(BaseApplication.getBaseApplication().getCacheDir());
        if (FileUtils.isSDCardAlive()) {
            cacheSize += FileUtils.getSize(BaseApplication.getBaseApplication().getExternalCacheDir());
        }
        return FileUtils.formatSize(cacheSize);
    }

    /**
     * 清除系统默认缓存文件夹内的缓存
     */
    public static void clearAllCache() {
        FileUtils.delete(BaseApplication.getBaseApplication().getCacheDir());
        if (FileUtils.isSDCardAlive()) {
            FileUtils.delete(BaseApplication.getBaseApplication().getExternalCacheDir());
        }
    }

}