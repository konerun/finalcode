package com.hexad.app.implementation;

import com.hexad.app.request.ProductRequest;
import com.hexad.app.response.ProductResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderProcessorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testProcessOrdersVegemiteScroll() throws Exception {
        List<ProductRequest> requests = new ArrayList<ProductRequest>();
        requests.add(ProductRequest.parse("VS5",10));

        OrderProcessor instance = new OrderProcessor();
        List<ProductResponse> result = instance.processOrders(requests);
        Double expectedTotalCost = 17.98; // two 5 size items @8.99 each
        assertEquals(1, result.size());
        assertEquals(expectedTotalCost,result.get(0).getTotalCost());
        assertEquals(2,result.get(0).getPacks().length);
        assertEquals(5,result.get(0).getPacks()[0].getSize());
        assertEquals(new Double(8.99),new Double(result.get(0).getPacks()[0].getCost()));
        assertEquals(5,result.get(0).getPacks()[1].getSize());
        assertEquals(new Double(8.99),new Double(result.get(0).getPacks()[1].getCost()));
    }

    @Test
    public void testProcessOrdersCroissant() throws Exception {
        System.out.println("ProcessOrders");
        List<ProductRequest> requests = new ArrayList<ProductRequest>();
        requests.add(ProductRequest.parse("CF",12));

        OrderProcessor instance = new OrderProcessor();
        List<ProductResponse> result = instance.processOrders(requests);
        Double expectedTotalCost = 22.939999999999998; // one 3 size @5.99 and one 9 size @16.99
        assertEquals(1, result.size());
        assertEquals(expectedTotalCost,result.get(0).getTotalCost());
        assertEquals(2,result.get(0).getPacks().length);
        assertEquals(9,result.get(0).getPacks()[0].getSize());
        assertEquals(new Double(16.99),new Double(result.get(0).getPacks()[0].getCost()));
        assertEquals(3,result.get(0).getPacks()[1].getSize());
        assertEquals(new Double(5.95),new Double(result.get(0).getPacks()[1].getCost()));

    }

    @Test
    public void testProcessOrdersBlueberryMuffin() throws Exception {
        System.out.println("ProcessOrders");
        List<ProductRequest> requests = new ArrayList<ProductRequest>();
        requests.add(ProductRequest.parse("MB11",7));

        OrderProcessor instance = new OrderProcessor();
        List<ProductResponse> result = instance.processOrders(requests);
        Double expectedTotalCost = 26.9; // one 2 size @9.95 and one 5 size @16.95
        assertEquals(1, result.size());
        assertEquals(expectedTotalCost,result.get(0).getTotalCost());
        assertEquals(2,result.get(0).getPacks().length);
        assertEquals(5,result.get(0).getPacks()[0].getSize());
        assertEquals(new Double(16.95),new Double(result.get(0).getPacks()[0].getCost()));
        assertEquals(2,result.get(0).getPacks()[1].getSize());
        assertEquals(new Double(9.95),new Double(result.get(0).getPacks()[1].getCost()));
    }

    @Test
    public void testProcessOrdersWithMixedOrderRequests() throws Exception {
        List<ProductRequest> requests = new ArrayList<ProductRequest>();
        requests.add(ProductRequest.parse("MB11",7));
        requests.add(ProductRequest.parse("CF",12));
        requests.add(ProductRequest.parse("VS5",10));

        OrderProcessor instance = new OrderProcessor();
        List<ProductResponse> result = instance.processOrders(requests);

        Double expectedMB11TotalCost = 26.9; // 26.9 for MB11, Crossiant for 22.939999999999998, 17.98 for VS5
        Double expectedCrossianTotalCost = 22.939999999999998;
        Double expectedVS5TotalCost = 17.98;

        assertEquals(3, result.size());

        assertEquals(expectedMB11TotalCost,result.get(0).getTotalCost());
        assertEquals(2,result.get(0).getPacks().length);
        assertEquals(5,result.get(0).getPacks()[0].getSize());
        assertEquals(new Double(16.95),new Double(result.get(0).getPacks()[0].getCost()));
        assertEquals(2,result.get(0).getPacks()[1].getSize());
        assertEquals(new Double(9.95),new Double(result.get(0).getPacks()[1].getCost()));
        //CF
        assertEquals(expectedCrossianTotalCost,result.get(1).getTotalCost());
        assertEquals(2,result.get(1).getPacks().length);
        assertEquals(9,result.get(1).getPacks()[0].getSize());
        assertEquals(new Double(16.99),new Double(result.get(1).getPacks()[0].getCost()));
        assertEquals(3,result.get(1).getPacks()[1].getSize());
        assertEquals(new Double(5.95),new Double(result.get(1).getPacks()[1].getCost()));
        //VS5
        assertEquals(expectedVS5TotalCost,result.get(2).getTotalCost());
        assertEquals(2,result.get(2).getPacks().length);
        assertEquals(5,result.get(2).getPacks()[0].getSize());
        assertEquals(new Double(8.99),new Double(result.get(2).getPacks()[0].getCost()));
        assertEquals(5,result.get(2).getPacks()[1].getSize());
        assertEquals(new Double(8.99),new Double(result.get(2).getPacks()[1].getCost()));
    }
}