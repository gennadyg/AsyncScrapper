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
import org.apache.http.HttpStatus;
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
public class HttpClient {

    /**
     *
     * @param url
     * @param printingsTimeout
     * @return content received from HTTP request
     */
    public HttpClientResponse get( String url, int printingsTimeout ){

            HttpClientResponse httpClientResponse = new HttpClientResponse();
            ResponseConsumer responseConsumer = new ResponseConsumer();

            try( CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault() ) {

                httpclient.start();
                Future<Boolean> future = httpclient.execute(
                                                    HttpAsyncMethods.createGet( url ),
                                                    responseConsumer,
                                                    null);

                Boolean result = false;
                int numOfTimers = 0;

                while( result == false ){

                    try{
                        result = future.get( printingsTimeout, TimeUnit.MILLISECONDS );

                        httpClientResponse.setStatusCode( responseConsumer.getResponse().getStatusLine().getStatusCode() );
                        if( HttpStatus.SC_OK == responseConsumer.getResponse().getStatusLine().getStatusCode() ) {

                            httpClientResponse.setContent( responseConsumer.getContent() );
                           // System.out.println("Request successfully executed, data - " + httpClientResponse.getContent() );
                        }else {

                            System.out.println("Failed to execute HTTP request, status code - " + responseConsumer.getResponse().getStatusLine().getStatusCode());
                        }

                    }catch( TimeoutException ex ){

                        System.out.println("- " + numOfTimers*printingsTimeout + "ms");
                        numOfTimers++;

                    }catch( Exception ex ) {

                        System.out.println("Got exception while executing HTTP request");
                        ex.printStackTrace();
                    }
                }
                System.out.println("Shutting down");

            }catch( IOException io ) {

                System.out.println("Got IO exception");
                io.printStackTrace();

            }
            return httpClientResponse;
        }
}