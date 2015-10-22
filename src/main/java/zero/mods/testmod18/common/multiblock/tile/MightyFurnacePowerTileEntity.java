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

        FMLLog.info("RFTEST - powerTE:receiveEnergy called (%s, %d, %s)", facing.toString(), maxReceive, simulate ? "simulation" : "real" );

        if (!this.isFacingGoodForEnergy(facing)) {
            FMLLog.info("RFTEST - powerTE:receiveEnergy facing not good!");
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).receiveEnergy(facing, maxReceive, simulate) : 0;

        FMLLog.info("RFTEST - powerTE:receiveEnergy result = %d", r);
        return r;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {

        FMLLog.info("RFTEST - powerTE:getEnergyStored called (%s)", facing.toString());

        if (!this.isFacingGoodForEnergy(facing)) {
            FMLLog.info("RFTEST - powerTE:getEnergyStored facing not good!");
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getEnergyStored(facing) : 0;

        FMLLog.info("RFTEST - powerTE:getEnergyStored result = %d", r);
        return r;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {

        FMLLog.info("RFTEST - powerTE:getMaxEnergyStored called (%s)", facing.toString());

        if (!this.isFacingGoodForEnergy(facing)) {
            FMLLog.info("RFTEST - powerTE:getMaxEnergyStored facing not good!");
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getMaxEnergyStored(facing) : 0;


        FMLLog.info("RFTEST - powerTE:getMaxEnergyStored result = %d", r);
        return r;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {

        return this.isFacingGoodForEnergy(facing);
    }


    private boolean isFacingGoodForEnergy(EnumFacing facing) {

        return this.isConnected() && this.getMultiblockController().isAssembled() && this.getOutwardsDir().isSet(facing);
    }

    // IEnergyReceiver end



}
