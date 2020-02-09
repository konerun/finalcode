package com.hexad.app.inventory;

import com.hexad.app.entity.Pack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlueberryMuffinProductInventoryTest {

    public BlueberryMuffinProductInventoryTest() {}

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void blueberryMuffinProductInventoryShouldHaveValidProductCode() {
        BlueberryMuffinProductInventory instance = new BlueberryMuffinProductInventory();
        String expResult = "MB11";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    @Test
    public void blueberryMuffinProductInventoryShouldHaveValidPrepackagedUnits(){
        BlueberryMuffinProductInventory instance = new BlueberryMuffinProductInventory();
        Pack[] result = instance.getPrePackedPackets();

        assertEquals(3,result.length);
        assertEquals(2,result[0].getSize());
        assertEquals(new Double(9.95),new Double(result[0].getCost()));
        assertEquals(5,result[1].getSize());
        assertEquals(new Double(16.95),new Double(result[1].getCost()));
        assertEquals(8,result[2].getSize());
        assertEquals(new Double(24.95),new Double(result[2].getCost()));
    }

    @Test
    public void testBlueberryMuffinProductInventoryConfigurablePrepackages() {
        Pack[] prepackagedUnits = new Pack[]{
                new Pack(11,111.99),
                new Pack(22,222.99),
                new Pack(33,322.99),
        };
        BlueberryMuffinProductInventory instance = new BlueberryMuffinProductInventory(prepackagedUnits);
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
