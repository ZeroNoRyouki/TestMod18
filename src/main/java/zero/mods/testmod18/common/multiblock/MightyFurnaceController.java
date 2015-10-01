package zero.mods.testmod18.common.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import zero.mods.zerocore.common.multiblock.IMultiblockPart;
import zero.mods.zerocore.common.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.common.multiblock.MultiblockValidationException;
import zero.mods.zerocore.common.multiblock.rectangular.RectangularMultiblockControllerBase;
import zero.mods.zerocore.common.multiblock.rectangular.RectangularMultiblockTileEntityBase;

public class MightyFurnaceController extends RectangularMultiblockControllerBase {


    public MightyFurnaceController(World world) {
        super(world);
    }

    @Override
    public void decodeDescriptionPacket(NBTTagCompound data) {

    }

    @Override
    public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data) {

    }

    @Override
    protected void onBlockAdded(IMultiblockPart newPart) {

    }

    @Override
    protected void onBlockRemoved(IMultiblockPart oldPart) {

    }

    @Override
    protected void onMachineAssembled() {

    }

    @Override
    protected void onMachineRestored() {

    }

    @Override
    protected void onMachinePaused() {

    }

    @Override
    protected void onMachineDisassembled() {

    }

    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine() {
        return 0;
    }

    @Override
    protected int getMaximumXSize() {
        return 0;
    }

    @Override
    protected int getMaximumZSize() {
        return 0;
    }

    @Override
    protected int getMaximumYSize() {
        return 0;
    }

    @Override
    protected void onAssimilate(MultiblockControllerBase assimilated) {

    }

    @Override
    protected void onAssimilated(MultiblockControllerBase assimilator) {

    }

    @Override
    protected boolean updateServer() {
        return false;
    }

    @Override
    protected void updateClient() {

    }

    @Override
    public void writeToNBT(NBTTagCompound data) {

    }

    @Override
    public void readFromNBT(NBTTagCompound data) {

    }

    @Override
    public void formatDescriptionPacket(NBTTagCompound data) {

    }
}
