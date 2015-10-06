package zero.mods.testmod18.common.blocks.RF;

import cofh.api.energy.IEnergyConnection;
import net.minecraft.util.EnumFacing;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntity;

public abstract class TileRF extends ModTileEntity implements IEnergyConnection {

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {

        return true;
    }

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    protected abstract boolean isEnergyConnectedOn(EnumFacing facing);
}
