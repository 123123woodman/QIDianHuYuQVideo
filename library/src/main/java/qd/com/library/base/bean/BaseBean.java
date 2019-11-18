package qd.com.library.base.bean;

import com.google.gson.Gson;

import java.io.Serializable;

import per.goweii.rxhttp.request.utils.JsonFormatUtils;

public class BaseBean implements Serializable {

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }

}
