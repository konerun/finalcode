package com.hexad.app.inventory;

import com.hexad.app.entity.Pack;
import com.hexad.app.model.IProductInventory;

public class BlueberryMuffinProductInventory extends IProductInventory {

    private static final String CODE = "MB11";

    private Pack[] prePackedPackets;

    public BlueberryMuffinProductInventory(){
        this.prePackedPackets = new Pack[]{
                new Pack(2,9.95),
                new Pack(5,16.95),
                new Pack(8,24.95)
        };
    }

    public BlueberryMuffinProductInventory(Pack[] prepackedUnits){
        this.prePackedPackets =  prepackedUnits;
    }

    @Override
    public String getCode() { return CODE; }

    @Override
    public Pack[] getPrePackedPackets() { return prePackedPackets; }
}
