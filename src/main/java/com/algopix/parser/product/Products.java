package com.algopix.parser.product;

import com.algopix.gson.GenericJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gennady on 4/23/2016.
 */
public class Products extends GenericJson {

    private List<Product> products = new ArrayList<>();

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
