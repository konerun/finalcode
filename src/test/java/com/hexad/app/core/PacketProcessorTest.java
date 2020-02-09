package com.hexad.app.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PacketProcessorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getItemCombinationsTest1() {
        int totalQuantity = 10;
        int[] itemPacks = new int[]{2,3,4};
        List<String> result = PacketProcessor.getItemCombinations(totalQuantity,itemPacks);
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("2 2 2 2 2 ");
        expectedResult.add("2 2 3 3 ");
        expectedResult.add("2 4 4 ");
        assertEquals(expectedResult,result);
    }

    @Test
    public void getItemCombinationsTest2() {
        //No combination
        int totalQuantity = 7;
        int[] itemPacks = new int[]{2,4};
        List<String> result = PacketProcessor.getItemCombinations(totalQuantity,itemPacks);
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("2 2 2 2 2 ");
        expectedResult.add("2 2 3 3 ");
        expectedResult.add("2 4 4 ");
        assertNotEquals(expectedResult,result);
    }

    @Test
    public void getItemCombinationsTest3() {
        //No combination exists
        int totalQuantity = 7;
        int[] itemPacks = new int[]{2,4};
        List<String> result = PacketProcessor.getItemCombinations(totalQuantity,itemPacks);
        List<String> expectedResult = new ArrayList<String>();
        assertEquals(expectedResult,result);
    }
}