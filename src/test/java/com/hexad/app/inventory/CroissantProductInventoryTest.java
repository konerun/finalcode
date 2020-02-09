package com.hexad.app.inventory;

import com.hexad.app.entity.Pack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CroissantProductInventoryTest {

    public CroissantProductInventoryTest() {}

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void croissantProductInventoryShouldHaveValid_ProductCode() {
        CroissantProductInventory instance = new CroissantProductInventory();
        String expResult = "CF";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    @Test
    public void croissantProductInventoryShouldHaveValidPrepackaged_Units() {
        CroissantProductInventory instance = new CroissantProductInventory();
        Pack[] expResult = null;
        Pack[] result = instance.getPrePackedPackets();
        assertEquals(3,result.length);
        assertEquals(3,result[0].getSize());
        assertEquals(new Double(5.95),new Double(result[0].getCost()));
        assertEquals(5,result[1].getSize());
        assertEquals(new Double(9.95),new Double(result[1].getCost()));
        assertEquals(9,result[2].getSize());
        assertEquals(new Double(16.99),new Double(result[2].getCost()));
    }

    @Test
    public void testCroissantProductInventoryConfigurablePrepackages() {
        Pack[] prepackagedUnits = new Pack[]{
                new Pack(11,111.99),
                new Pack(22,222.99),
                new Pack(33,322.99),
        };
        CroissantProductInventory instance = new CroissantProductInventory(prepackagedUnits);
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
