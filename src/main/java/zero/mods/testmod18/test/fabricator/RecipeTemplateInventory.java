package zero.mods.testmod18.test.fabricator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

/*

public class RecipeTemplateInventory implements IInventory {


    public RecipeTemplateInventory() {

        this._recipeItemStacks = new ItemStack[9];
    }


    @Override
    public int getSizeInventory() {

        return this._recipeItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        return this.isValidSlotIndex(index) ? this._recipeItemStacks[index] : null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        if ((count <= 0) || !this.isValidSlotIndex(index))
            return null;

        ItemStack stack = this._recipeItemStacks[index];

        if (null == stack)
            return null;


    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if (this.isValidSlotIndex(index))
            this._recipeItemStacks[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
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
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }


    private boolean isValidSlotIndex(int index) {

        return (index >= 0) && (index < this._recipeItemStacks.length);
    }

    private final ItemStack[] _recipeItemStacks;
}


*/