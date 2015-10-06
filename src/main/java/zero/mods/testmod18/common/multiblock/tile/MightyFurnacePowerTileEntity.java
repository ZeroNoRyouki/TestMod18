package zero.mods.testmod18.common.multiblock.tile;

import zero.mods.zerocore.common.multiblock.MultiblockValidationException;

public class MightyFurnacePowerTileEntity extends MightyFurnaceTileEntity {

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
}
