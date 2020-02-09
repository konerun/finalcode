package com.hexad.app.implementation;

import com.hexad.app.model.IProductInventory;
import com.hexad.app.model.IProductInventoryFactory;
import com.hexad.app.model.IStore;
import com.hexad.app.request.ProductRequest;
import com.hexad.app.response.ProductResponse;

public class BakeryStore implements IStore {

    private IProductInventoryFactory productInventoryFactory;

    public BakeryStore(){
        this.productInventoryFactory = new ProductInventoryFactory();
    }

    public BakeryStore(IProductInventoryFactory factory){
        this.productInventoryFactory = factory;
    }

    @Override
    public ProductResponse getCostAndProductPacketsBreakDown(ProductRequest request) throws Exception {
            IProductInventory product = this.productInventoryFactory.getProductInventory(request.getProductCode());
            return product.calculate(request);//Calculations should happen
    }
}
