package com.algopix.http;

import org.apache.http.HttpResponse;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Responsible for receiving data from http
 *
 * Created by Gennady on 4/20/2016.
 */
public class ResponseConsumer extends AsyncCharConsumer<Boolean> {

    private String content = "";
    private HttpResponse response;

    @Override
    protected void onResponseReceived( final HttpResponse response ) {

       this.response = response;
    }

    @Override
    protected void onCharReceived(final CharBuffer buf, final IOControl ioctrl) throws IOException {
        content += buf.toString();
        //  while (buf.hasRemaining()) {
        //      System.out.print( content );

        //   }
    }

    @Override
    protected void releaseResources() {
    }

    @Override
    protected Boolean buildResult(final HttpContext context) {

        return Boolean.TRUE;
    }

    public String getContent() {

        return content;
    }

    public HttpResponse getResponse() {

        return response;
    }
}
