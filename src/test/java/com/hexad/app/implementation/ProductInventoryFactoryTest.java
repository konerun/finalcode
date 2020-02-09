package com.hexad.app.implementation;

import static org.hamcrest.CoreMatchers.instanceOf;
import com.hexad.app.inventory.CroissantProductInventory;
import com.hexad.app.model.IProductInventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ProductInventoryFactoryTest {

    public ProductInventoryFactoryTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = Exception.class)
    public void testGetProductInventoryShouldThrowExceptionForInvalidProduct() throws Exception {
        ProductInventoryFactory instance = new ProductInventoryFactory();
       instance.getProductInventory("INVALID_PRODUCT_CODE");
    }

    @Test
    public void testGetProductInventoryShouldReturnRightProductInventory() throws Exception {
        System.out.println("GetProductInventory");
        ProductInventoryFactory instance = new ProductInventoryFactory();
        IProductInventory productInventory = instance.getProductInventory("CF");
        assertThat(productInventory, instanceOf(CroissantProductInventory.class));
    }
}
