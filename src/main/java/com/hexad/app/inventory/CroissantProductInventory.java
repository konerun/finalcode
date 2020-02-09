package com.hexad.app.inventory;

import com.hexad.app.entity.Pack;
import com.hexad.app.model.IProductInventory;

public class CroissantProductInventory  extends IProductInventory {

    private static final String CODE = "CF";

    private Pack[] prePackedPackets;

    public CroissantProductInventory(){
        this.prePackedPackets = new Pack[]{
                new Pack(3,5.95),
                new Pack(5,9.95),
                new Pack(9,16.99)
        };
    }

    public CroissantProductInventory(Pack[] prepackedUnits){
        this.prePackedPackets =  prepackedUnits;
    }

    @Override
    public String getCode() { return CODE; }

    @Override
    public Pack[] getPrePackedPackets() { return prePackedPackets; }
}
