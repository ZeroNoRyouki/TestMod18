package zero.mods.testmod18.common.multiblock.tile;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.zerocore.common.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.common.multiblock.MultiblockValidationException;

public class MightyFurnacePowerTileEntity extends MightyFurnaceTileEntity implements IEnergyReceiver {

    @Override
    public void isGoodForBottom() throws MultiblockValidationException {

        throw new MultiblockValidationException("Power ports can be placed only on a side face");
    }

    @Override
    public void isGoodForFrame() throws MultiblockValidationException {

        throw new MultiblockValidationException("Power ports can be placed only on a side face");
    }

    @Override
    public void isGoodForInterior() throws MultiblockValidationException {

        throw new MultiblockValidationException("Power ports can be placed only on a side face");
    }

    @Override
    public void isGoodForTop() throws MultiblockValidationException {

        throw new MultiblockValidationException("Power ports can be placed only on a side face");
    }



    // IEnergyReceiver begin

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {

        FMLLog.info("TILE POWER : received %d RF", maxReceive);

        if (!this.isFacingGoodForEnergy(facing))
            return 0;

        MultiblockControllerBase controller = this.getMultiblockController();

        return (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).receiveEnergy(facing, maxReceive, simulate) : 0;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {

        if (!this.isFacingGoodForEnergy(facing))
            return 0;

        MultiblockControllerBase controller = this.getMultiblockController();

        return (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getEnergyStored(facing) : 0;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {

        if (!this.isFacingGoodForEnergy(facing))
            return 0;

        MultiblockControllerBase controller = this.getMultiblockController();

        return (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getMaxEnergyStored(facing) : 0;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {

        return true;

        //return this.isFacingGoodForEnergy(facing);
    }


    private boolean isFacingGoodForEnergy(EnumFacing facing) {

        return this.isConnected() && this.getMultiblockController().isAssembled() && this.getOutwardsDir().isSet(facing);
    }

    // IEnergyReceiver end



}
