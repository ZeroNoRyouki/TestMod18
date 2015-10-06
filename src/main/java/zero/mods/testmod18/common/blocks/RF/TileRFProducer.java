package zero.mods.testmod18.common.blocks.RF;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntity;

public class TileRFProducer extends ModTileEntity implements IEnergyProvider {

    public TileRFProducer() {

        //this._rfStorage = new EnergyStorage();
    }

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    private EnergyStorage _rfStorage;
}
