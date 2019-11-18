package qd.com.qidianhuyu.http;

import com.google.gson.annotations.SerializedName;

public class QDRequestBody<E> {

    @SerializedName("timestamp")
    int timestamp = 1574046663;
    @SerializedName("type")
    String type = "5";
    @SerializedName("token")
    String token = "eba7a2e258ae7b958beb7f2b82a2cac81573536558";
    @SerializedName("sign")
    String sign;
    @SerializedName("data")
    E data;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QDRequestBody{" +
                "timestamp=" + timestamp +
                ", type=" + type +
                ", token=" + token +
                ", sign=" + sign +
                ", data=" + data +
                '}';
    }
}
