package qd.com.library.utils;

import qd.com.library.base.base.BaseApplication;

public class StringFetcher {

    public static String getString(int id) {
        return BaseApplication.getBaseApplication().getString(id);
    }

}