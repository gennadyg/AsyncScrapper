package com.algopix.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Gennady on 4/23/2016.
 */
public abstract class GenericJson{

    /**
     *
     * @return - string
     */
    public String toString(){

        return new Gson().toJson( this );
    }

    /**
     *
     * @param jsonString - json string
     * @param clazz - class that represents object
     * @param <T> - receives generic object
     *
     * @return - generic object
     */
    public static <T> T fromString( String jsonString, Class<T> clazz ){

        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.create();
        return gson.fromJson( jsonString, clazz );
    }

}