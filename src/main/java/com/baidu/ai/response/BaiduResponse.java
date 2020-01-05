package com.baidu.ai.response;

import com.baidu.ai.bean.Location;

public class BaiduResponse {

    private String accessToken;

    private Location location;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BaiduResponse(String accessToken, Location location) {
        this.accessToken = accessToken;
        this.location = location;
    }

    public BaiduResponse() {
    }

    @Override
    public String toString() {
        return "BaiduResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", location=" + location +
                '}';
    }
}

