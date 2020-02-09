package com.hexad.app.model;

import com.hexad.app.core.PacketProcessor;
import com.hexad.app.entity.Pack;
import com.hexad.app.request.ProductRequest;
import com.hexad.app.response.ProductResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is an abstract of any product in the store
 * All product should extend this IProductInventory
 */
public abstract class IProductInventory {

    /**
     * Every product should hold its own product code
     * @return
     */
    protected abstract String getCode();

    /**
     * Every product should have some prePacked units as defined in the problem statement
     * @return
     */

    protected abstract Pack[] getPrePackedPackets();

    public ProductResponse calculate(ProductRequest requestOrder){

        int userRequestedQuantity = requestOrder.getRequestedQuantity();

        //Step-1: Calculate packets combo that will be returned to the user.
        int[][] packetsCombination = this.getPacketsBreakDown(userRequestedQuantity);

        //Step-2: Calculate TotalCost to get the user requested quantity of items.
        Double totalCost = this.calculateTotalItemsCost(packetsCombination);

        //Step-3: Build set of packets that will be returned to the user.
        Pack[] resultPacksToCustomer = this.getCalculatedPacks(packetsCombination);

        //Step-4: Build response object.
        return new ProductResponse(requestOrder,totalCost,resultPacksToCustomer,packetsCombination);
    }

    public Pack[] getCalculatedPacks(int[][] packetsCombination){
        List<Pack> packs = new ArrayList<Pack>();
        for (int col = this.getPrePackedPackets().length-1; col >=0 ; col--) {
            if(packetsCombination[1][col]!=0){
                for(int i=1;i<=packetsCombination[1][col];i++){
                    packs.add(new Pack(packetsCombination[0][col],getPacketCost(packetsCombination[0][col])));
                }
            }
        }
        Pack[] array = new Pack[packs.size()];
        return packs.toArray(array);
    }

    public Double calculateTotalItemsCost(int[][] packetsCombination) {
        Double totalCost = new Double(0.00);
        for (int col = this.getPrePackedPackets().length-1; col >=0 ; col--) {
            if(packetsCombination[1][col]!=0){
                totalCost+= calculateTotalCostIncludedPacks(packetsCombination[1][col],String.valueOf(packetsCombination[0][col]));
            }
        }
        return totalCost;
    }

    private Double calculateTotalCostIncludedPacks(int quantity,String packet) {
        return new Double(quantity * Arrays.stream(this.getPrePackedPackets()).filter(x -> Integer.parseInt(packet) == x.getSize()).findAny().get().getCost());
    }

    private Double getPacketCost(int packSize) {
        return Arrays.stream(this.getPrePackedPackets()).filter(x -> packSize == x.getSize()).findAny().get().getCost();
    }

    /**
     * This method returns a two-dimensional array with two rows
     * first row holds the available packet sizes in ascending order provided in input
     * second rwo holds the count of that packets required to meet the totalRequiredQuantity
     *
     *
     * if required totalRequiredQuantity = 10 and available itemPacks are of sizes [2,3,4]
     *
     * the return array will be
     * [2,3,4] --> row 1
     * [1,0,2] --> row 2
     *
     * 1 * 2 size packets
     * 0 * 3 size packets
     * 2 * 4 size packets
     *
     * @param totalRequiredQuantity
     * @return
     */
    private int[][] getPacketsBreakDown(int totalRequiredQuantity) {
        int [][] list = new int[2][getPrePackedPackets().length];
        int[] packetSizesArray = getAvailablePacketsForProduct();
        list[0] = packetSizesArray;
        ArrayList<String> combinations = PacketProcessor.getItemCombinations(totalRequiredQuantity,packetSizesArray);

        if(combinations!=null && combinations.size()!=0){
            /**
             * Here we read the last set in the combination
             * inorder to reach the requirement of minimal number of packs
             * last combination will holds the minimum packets count (as it includes large packets)
             */
            List<String> itemDetailList = new ArrayList<String>(Arrays.asList(combinations.get(combinations.size()-1).split(" ")));
            List<Integer> updatedItemDetailList = itemDetailList.stream().map(Integer::parseInt).collect(Collectors.toList());
            for(int i = 0; i < packetSizesArray.length; i++){
                list[1][i] = Collections.frequency(updatedItemDetailList, packetSizesArray[i]);
            }
        }else{
            System.out.println("Available packet combination of "+getCode() +" doesn't make a total of "+totalRequiredQuantity);
        }
        return list;
    }

    /**
     * Reads the prepPackedPackets array
     * Iterates it and return integer array with all the available packet sizes in ascending order
     *
     * e.g : prePackedPackets have four packets
     *           size , cost
     *  Pack 1 : 2    , 2.20
     *  Pack 2 : 5    , 5.40
     *  Pack 3 : 9    , 9.80
     *  Pack 4 : 7    , 7.60
     *
     * Output is [2,5,7,9]
     *
     * @return
     */
    private int[] getAvailablePacketsForProduct(){
        return Arrays.stream(getPrePackedPackets()).map(x-> x.getSize()).sorted().collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
    }
}
