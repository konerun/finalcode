package com.hexad.app.response;

import com.hexad.app.entity.Pack;
import com.hexad.app.request.ProductRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class ProductResponseTest {

    public ProductResponseTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testProductResponse1() {
        Double testAmount = 33.33;
        Pack[] testPacks = new Pack[]{
                new Pack(3,11.11),
                new Pack(2,22.22)
        };
        int[][] packetsCombination = new int[2][];
        packetsCombination[0] = new int[]{2,3};
        packetsCombination[1] = new int[]{1,1};
        ProductRequest testOrderRequest = ProductRequest.parse("TESTProductCode",5);
        ProductResponse instance = new ProductResponse(testOrderRequest,testAmount,testPacks,packetsCombination);
        String expResult = "5\tTESTProductCode\t$33.33\n" +
                "\t1 X 3\t$11.11\n" +
                "\t1 X 2\t$22.22\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
