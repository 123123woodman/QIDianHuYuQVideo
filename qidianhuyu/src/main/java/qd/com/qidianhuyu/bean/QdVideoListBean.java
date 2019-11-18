package qd.com.qidianhuyu.bean;

import com.aliyun.player.source.UrlSource;
import com.aliyun.player.source.VidSts;
import com.google.gson.annotations.SerializedName;

import qd.com.qidianhuyu.ali.view.video.videolist.IVideoSourceModel;
import qd.com.qidianhuyu.ali.view.video.videolist.VideoSourceType;

public class QdVideoListBean implements IVideoSourceModel {

    /**
     * "type": 1,// 1为视屏 2为广告
     *             "video_id": "5253448982973219915",// 视频ID
     *             "label": "",// 视频标签
     *             "author": "人民网",// 视频作者
     *             "caption": "心痛！陕西西安胡家庙",// 视频简介(说明)
     *             "image_url": "",// 封面图片URL
     *             "video_url": "http://txmov2.a.yximgs.com/u1/.mp4",// 视频URL
     *             "aly_video_url": "https://outin12.mp4",// aliyun视频点播URL
     *             "long_video_url": "",// 长视频URL
     *             "is_vip": 0,// 是否是VIP视频 0 否 1是
     *             "is_long_video": 0// 是否对应长视频 0否 1是
     *             "author": "起点互娱",// 广告主名称
     *             "introducte": "",// 简介
     *             "url": "",// 广告链接
     *             "open_type": 1,// 打开类型 1H5打开 2直接下载
     *             "open_url": "",// 打开地址
     *             "down_url": ""// 下载地址
     */

    @SerializedName("type")
    private int type;
    @SerializedName("video_id")
    private String video_id;
    @SerializedName("label")
    private String label;
    @SerializedName("author")
    private String author;
    @SerializedName("caption")
    private String caption;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("video_url")
    private String video_url;
    @SerializedName("aly_video_url")
    private String aly_video_url;
    @SerializedName("long_video_url")
    private String long_video_url;
    @SerializedName("is_vip")
    private String is_vip;
    @SerializedName("is_long_video")
    private String is_long_video;
    @SerializedName("introducte")
    private String introducte;
    @SerializedName("url")
    private String url;
    @SerializedName("open_type")
    private String open_type;
    @SerializedName("open_url")
    private String open_url;
    @SerializedName("down_url")
    private String down_url;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getAly_video_url() {
        return aly_video_url;
    }

    public void setAly_video_url(String aly_video_url) {
        this.aly_video_url = aly_video_url;
    }

    public String getLong_video_url() {
        return long_video_url;
    }

    public void setLong_video_url(String long_video_url) {
        this.long_video_url = long_video_url;
    }

    public String getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }

    public String getIs_long_video() {
        return is_long_video;
    }

    public void setIs_long_video(String is_long_video) {
        this.is_long_video = is_long_video;
    }

    public String getIntroducte() {
        return introducte;
    }

    public void setIntroducte(String introducte) {
        this.introducte = introducte;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpen_type() {
        return open_type;
    }

    public void setOpen_type(String open_type) {
        this.open_type = open_type;
    }

    public String getOpen_url() {
        return open_url;
    }

    public void setOpen_url(String open_url) {
        this.open_url = open_url;
    }

    public String getDown_url() {
        return down_url;
    }

    public void setDown_url(String down_url) {
        this.down_url = down_url;
    }

    @Override
    public VideoSourceType getSourceType() {
        return VideoSourceType.TYPE_URL;
    }

    @Override
    public UrlSource getUrlSource() {
        return null;
    }

    @Override
    public VidSts getVidStsSource() {
        return null;
    }

    @Override
    public String getFirstFrame() {
        return image_url;
    }

    @Override
    public String getUUID() {
        return video_id;
    }

    @Override
    public String getUri() {
        return video_url;
    }

    @Override
    public int getLittleViewType() {
        return type;
    }

    @Override
    public String toString() {
        return "QdVideoListBean{" +
                "type=" + type +
                ", video_id='" + video_id + '\'' +
                ", label='" + label + '\'' +
                ", author='" + author + '\'' +
                ", caption='" + caption + '\'' +
                ", image_url='" + image_url + '\'' +
                ", video_url='" + video_url + '\'' +
                ", aly_video_url='" + aly_video_url + '\'' +
                ", long_video_url='" + long_video_url + '\'' +
                ", is_vip='" + is_vip + '\'' +
                ", is_long_video='" + is_long_video + '\'' +
                ", introducte='" + introducte + '\'' +
                ", url='" + url + '\'' +
                ", open_type='" + open_type + '\'' +
                ", open_url='" + open_url + '\'' +
                ", down_url='" + down_url + '\'' +
                '}';
    }
}
