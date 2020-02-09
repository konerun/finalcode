package com.hexad.app.implementation;

import com.hexad.app.inventory.BlueberryMuffinProductInventory;
import com.hexad.app.inventory.CroissantProductInventory;
import com.hexad.app.inventory.VegemiteScrollProductInventory;
import com.hexad.app.model.IProductInventory;
import com.hexad.app.model.IProductInventoryFactory;

public class ProductInventoryFactory implements IProductInventoryFactory {

    @Override
    public IProductInventory getProductInventory(String productCode) throws Exception {
        IProductInventory prodInventory = null;
        switch(productCode){
            case "VS5":     prodInventory = new VegemiteScrollProductInventory();
                            break;
            case "MB11":    prodInventory = new BlueberryMuffinProductInventory();
                            break;
            case "CF":      prodInventory = new CroissantProductInventory();
                            break;
            default:        throw new Exception("No such product exists!!");
        }
        return prodInventory;
    }
}
