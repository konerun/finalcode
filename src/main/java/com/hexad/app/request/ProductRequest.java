package com.hexad.app.request;

public class ProductRequest {

    private String productCode;

    private Integer requestedQuantity;

    private ProductRequest(String code, Integer requestedQuantity){
        this.productCode = code;
        this.requestedQuantity = requestedQuantity;
    }

    public String getProductCode(){ return this.productCode; }
    public Integer getRequestedQuantity(){ return this.requestedQuantity; }

    public static ProductRequest parse(String code, Integer quantity){
          return new ProductRequest(code,quantity);
    }
}
