package com.hexad.app.response;

import com.hexad.app.entity.Pack;
import com.hexad.app.request.ProductRequest;

import java.util.Arrays;

public class ProductResponse {

    private ProductRequest productRequest;
    private Double totalCost;
    private Pack[] packs;
    private int[][] packetsCombination;

    public ProductResponse(ProductRequest request,Double totalCost,Pack[] packs){
        this.productRequest = request;
        this.totalCost = totalCost;
        this.packs = packs;
    }

    public ProductResponse(ProductRequest request,Double totalCost,Pack[] packs,int[][] packetsCombination){
        this.productRequest = request;
        this.totalCost = totalCost;
        this.packs = packs;
        this.packetsCombination = packetsCombination;
    }


    public Double getTotalCost(){ return this.totalCost; }
    public Pack[] getPacks(){ return this.packs; }

    private Double includeItem(int quantity,String packet) {
        return new Double(quantity * Arrays.stream(this.packs).filter(x -> Integer.parseInt(packet) == x.getSize()).findAny().get().getCost());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.productRequest.getRequestedQuantity());
        sb.append("\t");
        sb.append(this.productRequest.getProductCode());
        sb.append("\t");
        sb.append("$"+this.totalCost.floatValue());
        sb.append("\n");
        for (int col = packetsCombination[0].length-1; col >=0 ; col--) {
            if(packetsCombination[1][col]!=0){
                sb.append("\t"+packetsCombination[1][col] +" X "+packetsCombination[0][col]);
                sb.append("\t");
                sb.append("$"+includeItem(packetsCombination[1][col],String.valueOf(packetsCombination[0][col])).floatValue());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
