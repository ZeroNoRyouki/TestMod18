package zero.mods.testmod18.common.multiblock.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.helpers.CodeHelper;
import zero.mods.zerocore.common.helpers.InventoryHelper;
import zero.mods.zerocore.common.multiblock.MultiblockValidationException;

public class MightyFurnaceIOPortTileEntity extends MightyFurnaceTileEntity implements IInventory {

    public MightyFurnaceIOPortTileEntity(boolean isInput) {

        this._isInput = isInput;
        this._inventory = null;
    }

    public MightyFurnaceIOPortTileEntity() {

        this(false);
    }

    public boolean isInput() {

        return this._isInput;
    }

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    // IInventory

    @Override
    public void clear() {

        this._inventory = null;
    }

    @Override
    public int getSizeInventory() {

        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        return 0 == index ? this._inventory : null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        return 0 == index ? InventoryHelper.decreaseStackSize(this, 0, count) : null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {

        ItemStack itemStack = this.getStackInSlot(index);

        if (itemStack != null)
            this.setInventorySlotContents(index, null);

        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if (0 == index)
            this._inventory = stack;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return CodeHelper.isEntityInRange(player, this.pos, 8.0);
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return true;
    }

    @Override
    public int getField(int id) {

        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public String getName() {

        return "te.mightyFurnaceIO";
    }

    @Override
    public boolean hasCustomName() {

        return false;
    }

    @Override
    public IChatComponent getDisplayName() {

        return InventoryHelper.getDisplayName(this);
    }



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

    protected boolean _isInput;
    protected ItemStack _inventory;
}
