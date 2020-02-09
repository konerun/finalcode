package com.hexad.app.inventory;

import com.hexad.app.entity.Pack;
import com.hexad.app.model.IProductInventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VegemiteScrollProductInventoryTest {

    public VegemiteScrollProductInventoryTest() {}

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void vegemiteScrollProductInventoryShouldHaveValid_ProductCode() {
        VegemiteScrollProductInventory instance = new VegemiteScrollProductInventory();
        String expResult = "VS5";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    @Test
    public void vegemiteScrollProductInventoryShouldHaveValidPrepackaged_Units() {
        VegemiteScrollProductInventory instance = new VegemiteScrollProductInventory();
        Pack[] result = instance.getPrePackedPackets();
        assertEquals(2,result.length);
        assertEquals(3,result[0].getSize());
        assertEquals(new Double(6.99),new Double(result[0].getCost()));
        assertEquals(5,result[1].getSize());
        assertEquals(new Double(8.99),new Double(result[1].getCost()));
    }

    @Test
    public void testVegemiteScrollProductInventoryConfigurablePrepackages() {
        Pack[] prepackagedUnits = new Pack[]{
                new Pack(11,111.99),
                new Pack(22,222.99),
                new Pack(33,322.99),
        };
        VegemiteScrollProductInventory instance = new VegemiteScrollProductInventory(prepackagedUnits);
        Pack[] result = instance.getPrePackedPackets();

        assertEquals(3,result.length);
        assertEquals(11,result[0].getSize());
        assertEquals(new Double(111.99),new Double(result[0].getCost()));
        assertEquals(22,result[1].getSize());
        assertEquals(new Double(222.99),new Double(result[1].getCost()));
        assertEquals(33,result[2].getSize());
        assertEquals(new Double(322.99),new Double(result[2].getCost()));
    }
}
