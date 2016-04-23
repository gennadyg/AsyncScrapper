package com.algopix.parser;

import com.algopix.parser.product.Product;
import com.algopix.parser.product.Products;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gennady on 4/23/2016.
 */
public class HtmlParser {

    public String parse( String htmlContent, int maxNumOfProducts ){

        String jsonResult = "";
        Products products = new Products();
        try{

            Document doc = Jsoup.parse( htmlContent );
            Elements liElements = doc.getElementsByTag("li");

            for( int currentId = 0; ( currentId < liElements.size() ) && ( currentId < maxNumOfProducts ) ; currentId++ ){

                boolean validProduct = true;
                Element currentElement = liElements.get( currentId );
                Elements productNameElements = currentElement.getElementsByClass("product-name");

                Product productFound = new Product();
                if( ( productNameElements != null ) && ( productNameElements.size() > 0 ) && ( productNameElements.get(0) != null )){

                    productFound.setProductName( productNameElements.get(0).text() );
                }else{
                    validProduct = false;
                }

                Elements productPriceElements = currentElement.getElementsByClass("product-price");
                if( ( productPriceElements != null ) && ( productPriceElements.size() > 0 ) && ( productPriceElements.get(0) != null )){

                    productFound.setPrice( productPriceElements.get(0).text() );
                }else{
                    validProduct = false;
                }
                if( validProduct ){

                    products.getProducts().add( productFound );
                }
            }

        }catch( Exception ex ){

            System.out.println("Failed to parse content - \n" + htmlContent );
            ex.printStackTrace();
        }
        return products.toString();
    }
}
