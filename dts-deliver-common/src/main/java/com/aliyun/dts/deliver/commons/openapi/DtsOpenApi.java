package com.aliyun.dts.deliver.commons.openapi;

import org.apache.commons.lang3.tuple.Pair;

public class DtsOpenApi {

    private String ak;
    private String secret;
    private String dtsInstance;

    public DtsOpenApi(String ak, String secret, String dtsInstance) {
        this.ak = ak;
        this.secret = secret;
        this.dtsInstance = dtsInstance;
    }

    //todo(yanmen)
    public Pair<String, String> getUserPassword() {
        return null;
    }

    //todo(yanmen)
    public String getDblist() {
        return null;
    }
}
