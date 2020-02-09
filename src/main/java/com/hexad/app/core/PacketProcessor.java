package com.hexad.app.core;

import java.util.ArrayList;

public class PacketProcessor {

    /**
     * This is the very core logic to determine the combinations of packets
     * if required total = 10 and available itemPacks are [2,3,4]
     * there will be three combinations to make up the sum to 10
     * [2 2 2 2 2 ]
     * [2 2 3 3 ]
     * [2 4 4 ]
     * @param total
     * @param itemPacks
     * @return
     */
    public static ArrayList<String> getItemCombinations(int total, int[] itemPacks) {
        int[] temp = new int[total + 1];
        ArrayList<String>[] combinationsArray = new ArrayList[total+1];
        for(int i=0;i<combinationsArray.length; i++) {
            combinationsArray[i] = new ArrayList<>();
        }
        temp[0] = 1;
        for (int eachPack : itemPacks) {
            for (int index = 1; index < temp.length; index++) {
                if(index-eachPack >=0 && temp[index - eachPack] != 0)
                {
                    temp[index] +=1;
                    combinationsArray[index].add(combinationsArray[index-eachPack].size() > 0?
                            (combinationsArray[index-eachPack].get(combinationsArray[index-eachPack].size()-1) + eachPack + " "): Integer.toString(eachPack) + " ");

                }
            }
        }
        return combinationsArray[total];
    }

}
