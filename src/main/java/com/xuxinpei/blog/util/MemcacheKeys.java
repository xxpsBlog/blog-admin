package com.xuxinpei.blog.util;

public enum MemcacheKeys {
    ADMIN_SESSION("admin_", "管理员后台登录"),
    USER_SESSION("user_", "用户登录"),
    PIC_CODE("picCode_", "图形验证码"),
    MOBILE_CODE("mobileCode_", "短信验证码"),
    CSRF_TOKEN("csrftoken_", "防止CSRF攻击的token"),
    REST_TOKEN("resttoken_", "REST调用接口"),
    WEIXIN_ADMIN_TOKEN("weixinAdminToken_", "微信设置管理员授权码"),
    WEIXIN_RESOURCES("weixinResources_", "微信素材列表");

    private String key;
    private String profile;

    private MemcacheKeys(String key, String profile) {
        this.key = key;
        this.profile = profile;
    }

    public String getKey() {
        return com.xxp.blog.util.StaticProp.cookieID + "_" + this.key;
    }

    public String getProfile() {
        return this.profile;
    }
}