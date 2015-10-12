package zero.mods.testmod18.common.multiblock.tile;

import zero.mods.testmod18.EntryPoint;
import zero.mods.testmod18.common.multiblock.MightyFurnaceController;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.common.multiblock.MultiblockValidationException;
import zero.mods.zerocore.common.multiblock.rectangular.RectangularMultiblockTileEntityBase;

public class MightyFurnaceTileEntity extends RectangularMultiblockTileEntityBase {

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    @Override
    public void isGoodForBottom() throws MultiblockValidationException {
        // this part is good for any position in the multiblock
    }

    @Override
    public void isGoodForFrame() throws MultiblockValidationException {
        // this part is good for any position in the multiblock
    }

    @Override
    public void isGoodForSides() throws MultiblockValidationException {
        // this part is good for any position in the multiblock
    }

    @Override
    public void isGoodForTop() throws MultiblockValidationException {
        // this part is good for any position in the multiblock
    }

    @Override
    public void isGoodForInterior() throws MultiblockValidationException {
        // this part is good for any position in the multiblock
    }

    @Override
    public void onMachineActivated() {

    }

    @Override
    public void onMachineDeactivated() {

    }

    @Override
    public void onMachineAssembled(MultiblockControllerBase controller) {

        super.onMachineAssembled(controller);
        this.updateBlockState();
    }

    @Override
    public void onMachineBroken() {

        super.onMachineBroken();
        this.updateBlockState();
    }

    @Override
    public MultiblockControllerBase createNewMultiblock() {

        return new MightyFurnaceController(this.worldObj);
    }

    @Override
    public Class<? extends MultiblockControllerBase> getMultiblockControllerType() {

        return MightyFurnaceController.class;
    }

    private void updateBlockState() {

        this.worldObj.markBlockForUpdate(this.pos);
    }
}
