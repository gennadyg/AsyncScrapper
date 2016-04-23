package com.algopix.parser.product;

import com.algopix.gson.GenericJson;

/**
 * Created by Gennady on 4/20/2016.
 */
public class Product extends GenericJson {

    private String productName;

    private String price;

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
