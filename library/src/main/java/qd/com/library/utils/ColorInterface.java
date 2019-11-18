package qd.com.library.utils;

import qd.com.library.R;
import qd.com.library.base.base.BaseApplication;

/**
 * Created by Administrator on 2018/4/12.
 */

public interface ColorInterface {
    int[] color =
            {BaseApplication.getBaseApplication().getResources().getColor(R.color.green), BaseApplication.getBaseApplication().getResources().getColor(R.color.colorAccent),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.balck), BaseApplication.getBaseApplication().getResources().getColor(R.color.colorPrimary),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.colorPrimaryDark), BaseApplication.getBaseApplication().getResources().getColor(R.color.orange),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.purple), BaseApplication.getBaseApplication().getResources().getColor(R.color.yellow)};

    int[] treeColor =
            {BaseApplication.getBaseApplication().getResources().getColor(R.color.green), BaseApplication.getBaseApplication().getResources().getColor(R.color.colorAccent),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.red),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.deeppink), BaseApplication.getBaseApplication().getResources().getColor(R.color.orange),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.purple), BaseApplication.getBaseApplication().getResources().getColor(R.color.yellow),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.fuchsia), BaseApplication.getBaseApplication().getResources().getColor(R.color.lavender),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.cyan), BaseApplication.getBaseApplication().getResources().getColor(R.color.auqamarin),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.chartreuse), BaseApplication.getBaseApplication().getResources().getColor(R.color.gold),
                    BaseApplication.getBaseApplication().getResources().getColor(R.color.orangeRed)};

}
