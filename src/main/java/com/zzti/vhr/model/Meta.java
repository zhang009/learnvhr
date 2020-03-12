package com.zzti.vhr.model;

/**
 * @ClassName Meta
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/3 9:22
 **/
public class Meta {

    private Boolean keepAlive;

    private Boolean requireAuth;

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
