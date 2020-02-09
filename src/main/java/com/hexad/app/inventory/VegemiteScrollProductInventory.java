package com.hexad.app.inventory;

import com.hexad.app.entity.Pack;
import com.hexad.app.model.IProductInventory;

public class VegemiteScrollProductInventory extends IProductInventory {

    private static final String CODE = "VS5";

    private Pack[] prePackedPackets;

    public VegemiteScrollProductInventory(){
        this.prePackedPackets = new Pack[]{
                new Pack( 3, 6.99),
                new Pack( 5, 8.99)
        };
    }

    public VegemiteScrollProductInventory(Pack[] prepackedUnits){
        this.prePackedPackets =  prepackedUnits;
    }

    @Override
    public String getCode() { return CODE; }

    @Override
    public Pack[] getPrePackedPackets() { return prePackedPackets; }
}
