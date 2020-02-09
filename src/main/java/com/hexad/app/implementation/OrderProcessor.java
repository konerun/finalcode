package com.hexad.app.implementation;

import com.hexad.app.model.IStore;
import com.hexad.app.request.ProductRequest;
import com.hexad.app.response.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {

    IStore store;

    public OrderProcessor(){
        store  = new BakeryStore();
    }

    public OrderProcessor(IStore bakeryStore){
        this.store  = bakeryStore;
    }

    public List<ProductResponse> processOrders(List<ProductRequest> requests)throws Exception {
        List<ProductResponse> fullFilledOrders = new ArrayList<ProductResponse>();
        for(ProductRequest eachItemRequest:requests){
            ProductResponse result = this.store.getCostAndProductPacketsBreakDown(eachItemRequest);
            fullFilledOrders.add(result);
        }
        return fullFilledOrders;
    }
}
