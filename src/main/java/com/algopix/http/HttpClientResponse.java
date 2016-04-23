package com.algopix.http;

/**
 * Created by Gennady on 4/20/2016.
 */
public class HttpClientResponse {

    private String content;
    private int statusCode;

    public String getContent() {
        return content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
