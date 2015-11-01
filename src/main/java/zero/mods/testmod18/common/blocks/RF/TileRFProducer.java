package zero.mods.testmod18.common.blocks.RF;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.EnumFacing;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntity;

public class TileRFProducer extends ModTileEntity implements IEnergyProvider, IUpdatePlayerListBox {

    public TileRFProducer() {

        this._rfStorage = new EnergyStorage(10000, 1000, 1000);
    }

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    @Override
    public void update() {

        if (this.calledByLogicalServer())
            this._rfStorage.receiveEnergy(1, false);
    }

    // IEnergyProvider

    @Override
    public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {

        return this.calledByLogicalServer() ? this._rfStorage.extractEnergy(maxExtract, simulate) : 0;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {

        return this.calledByLogicalServer() ?  this._rfStorage.getEnergyStored() : 0;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {

        return this.calledByLogicalServer() ?  this._rfStorage.getMaxEnergyStored() : 0;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {

        return true;
    }

    private EnergyStorage _rfStorage;
}
