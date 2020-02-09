package com.hexad.app.model;

import com.hexad.app.request.ProductRequest;
import com.hexad.app.response.ProductResponse;

public interface IStore {

    /**
     * This method should return the product response based
     * on the request for any given product request
     * @param request
     * @return
     */
    ProductResponse getCostAndProductPacketsBreakDown(ProductRequest request) throws Exception;
}
