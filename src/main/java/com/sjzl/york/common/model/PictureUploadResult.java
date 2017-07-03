package com.sjzl.york.common.model;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/3 16:33
 */
public class PictureUploadResult {
    private String url;
    private String md5;
    private Integer height;
    private Integer width;

    public PictureUploadResult(String url, String md5) {
        this.url = url;
        this.md5 = md5;
    }
    public PictureUploadResult(String url, String md5, Integer width, Integer height) {
        this.url = url;
        this.md5 = md5;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
