package com.hexad.app.model;

/**
 * Interface for Product Inventory
 * Based on the product code Factory should return correct product inventory
 */
public interface IProductInventoryFactory {

    IProductInventory getProductInventory(String productCode) throws Exception;
}
