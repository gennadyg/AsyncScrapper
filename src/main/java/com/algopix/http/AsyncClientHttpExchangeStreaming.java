package com.algopix.http;

/**
 * Created by Lital on 4/18/2016.
 */
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates an asynchronous HTTP request / response exchange with
 * a full content streaming.
 */
public class AsyncClientHttpExchangeStreaming {

    public static void main(final String[] args) throws Exception {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            MyResponseConsumer myResponseConsumer = new MyResponseConsumer();
            httpclient.start();
            Future<Boolean> future = httpclient.execute(
                    HttpAsyncMethods.createGet("http://challenge.algopix.com.s3-website-us-east-1.amazonaws.com"),
                //    HttpAsyncMethods.createGet("http://fan.lib.ru/a/amnuelx_p_p/text_0150.shtml"),
                    myResponseConsumer, null);

            Boolean result = false;
            int numOfTimers = 0;

            while( result == false ){

                try{
                    result = future.get( 200, TimeUnit.MILLISECONDS );
                    String content = myResponseConsumer.content;
                    System.out.println("Request successfully executed, data - " + content );


                }catch( TimeoutException ex ){

                    System.out.println("- " + numOfTimers*200 + "ms");
                    numOfTimers++;
                }
            }
            System.out.println("Shutting down");

        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }

    static class MyResponseConsumer extends AsyncCharConsumer<Boolean> {

        private String content = "";

        @Override
        protected void onResponseReceived(final HttpResponse response) {
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

    }

}