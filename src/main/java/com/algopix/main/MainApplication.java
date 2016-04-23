package com.algopix.main;

import com.algopix.http.HttpClient;
import com.algopix.http.HttpClientResponse;
import com.algopix.parser.HtmlParser;
import org.apache.http.HttpStatus;

/**
 * Created by Genna on 4/20/2016.
 */
public class MainApplication {

   public static void main( String[] args ){

       HttpClient httpClient = new HttpClient();
       HttpClientResponse httpClientResponse = httpClient.get( "http://challenge.algopix.com.s3-website-us-east-1.amazonaws.com", 200 );

       if( httpClientResponse.getStatusCode() == HttpStatus.SC_OK ){

           HtmlParser htmlParser = new HtmlParser();
           String json = htmlParser.parse( httpClientResponse.getContent(), 4  );
           System.out.println( "Got a following products - " + json );
       }
   }
}
